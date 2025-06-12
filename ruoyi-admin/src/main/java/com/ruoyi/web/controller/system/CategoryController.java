package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Category;
import com.ruoyi.system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图书分类管理Controller
 *
 * @author 李晓赞
 */
@RestController
@RequestMapping("/system/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = categoryService.selectCategory(category);
        return getDataTable(list);
    }

    /**
     * 获取所有分类（不分页）
     */
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<Category> list = categoryService.selectAllCategory();
        return AjaxResult.success(list);
    }

    /**
     * 获取分类树结构（供前端级联选择器使用）
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect() {
        List<Category> categories = categoryService.selectAllCategory();
        return AjaxResult.success(buildCategoryTree(categories));
    }

    /**
     * 构建分类树结构
     */
    private List<Category> buildCategoryTree(List<Category> categories) {
        List<Category> returnList = new ArrayList<>();
        Map<Long, Category> categoryMap = new HashMap<>();

        // 将所有分类放入Map中，便于查找父分类
        for (Category category : categories) {
            categoryMap.put(category.getCategoryId(), category);
        }

        // 构建树结构
        for (Category category : categories) {
            // 如果是顶级分类，直接添加到返回列表
            if (category.getParentId() == 0) {
                returnList.add(category);
            } else {
                // 如果是子分类，添加到父分类的children中
                Category parent = categoryMap.get(category.getParentId());
                if (parent != null) {
                    // 如果parent还没有children属性，则初始化
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(category);
                }
            }
        }

        return returnList;
    }

    /**
     * 导出分类列表
     */
    @PreAuthorize("@ss.hasPermi('system:category:export')")
    @Log(title = "图书分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category) {
        List<Category> list = categoryService.selectCategory(category);
        ExcelUtil<Category> util = new ExcelUtil<>(Category.class);
        util.exportExcel(response, list, "分类数据");
    }

    /**
     * 获取分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(categoryService.getCategoryById(categoryId));
    }

    /**
     * 新增分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "图书分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category) {
        if (!categoryService.checkCategoryNameUnique(category)) {
            return AjaxResult.error("新增分类'" + category.getCategoryName() + "'失败，分类名称已存在");
        }
        return toAjax(categoryService.add(category));
    }

    /**
     * 修改分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "图书分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category) {
        if (!categoryService.checkCategoryNameUnique(category)) {
            return AjaxResult.error("修改分类'" + category.getCategoryName() + "'失败，分类名称已存在");
        }
        return toAjax(categoryService.edit(category));
    }

    /**
     * 删除分类
     */
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "图书分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        for (Long categoryId : categoryIds) {
            if (categoryService.hasChildByCategoryId(categoryId)) {
                return AjaxResult.error("存在子分类，不允许删除");
            }
            if (categoryService.checkCategoryExistBook(categoryId)) {
                return AjaxResult.error("分类已被图书使用，不允许删除");
            }
        }
        return toAjax(categoryService.deleteCategory(categoryIds));
    }
} 