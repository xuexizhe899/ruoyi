package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Book;
import com.ruoyi.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * ClassName: BookController
 * Package: com.ruoyi.web.controller.system
 * Description:
 *
 * @Author 李晓赞
 * @Create 2025/6/9 16:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/book")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;


    //增
    @PostMapping
    public AjaxResult add(@RequestBody Book book) {
        if (StringUtils.isBlank(book.getBookName())) {
            return AjaxResult.error("图书名称不能为空");
        }
        if (StringUtils.isBlank(book.getAuthor())) {
            return AjaxResult.error("作者不能为空");
        }
        if (book.getPrice() == null) {
            return AjaxResult.error("价格不能为空");
        }
        if (book.getStock() == null) {
            return AjaxResult.error("库存不能为空");
        }
        Long userId = SecurityUtils.getUserId();
        book.setCreateBy(userId.toString());
        book.setCreateTime(new Date());
        book.setUpdateBy(userId.toString());
        book.setUpdateTime(new Date());
        int num = bookService.add(book);
        if (num > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    //删-单个
    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Integer id) {
        int num = bookService.deleteBook(id);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    //删-多个
    @DeleteMapping("/batchDelete")
    public AjaxResult batchRemove(@RequestBody Integer[] ids) {
        int num = bookService.deleteBook(ids);
        if (num > 0) {
            return AjaxResult.success("批量删除成功");
        } else {
            return AjaxResult.error("批量删除失败");
        }
    }

    //改
    @PutMapping
    public AjaxResult edit(@RequestBody Book book) {
        Long userId = SecurityUtils.getUserId();
        book.setUpdateBy(userId.toString());
        book.setUpdateTime(new Date());
        int num = bookService.edit(book);
        if (num > 0) {
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    @GetMapping("/list")
    public TableDataInfo list(Book book) {
        startPage();
        List<Book> list = bookService.selectBook(book);
        return getDataTable(list);
    }

    @GetMapping("/getBookById")
    public AjaxResult getBookById(Integer id) {
        return AjaxResult.success(bookService.getBookById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:book:export')")
    @Log(title = "图书管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Book book) {
        List<Book> list = bookService.selectBook(book);
        ExcelUtil<Book> util = new ExcelUtil<>(Book.class);
        util.exportExcel(response, list, "图书数据");
    }
}
