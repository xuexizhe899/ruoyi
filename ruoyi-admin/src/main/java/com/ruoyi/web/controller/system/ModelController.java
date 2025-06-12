package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Model;
import com.ruoyi.system.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ClassName: ModelController
 * Package: com.ruoyi.web.controller.system
 * Description:
 *
 * @Author 李晓赞
 * @Create 2025/5/15 9:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/model")
public class ModelController extends BaseController {
    @Autowired
    private ModelService modelService;

    //增
    @PostMapping
    public AjaxResult add(@RequestBody Model model) {
        if (StringUtils.isBlank(model.getModelId())) {
            return AjaxResult.error("代号不能为空");
        }
        Long userId = SecurityUtils.getUserId();
        model.setCreator(userId.toString());
        model.setCreateTime(new Date());
        int num = modelService.add(model);
        if (num > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    //删-单个
    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Integer id) {
        int num = modelService.deleteModel(id);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }


    //改
    @PutMapping
    public AjaxResult edit(@RequestBody Model Model) {
        //获取操作人
        Long userId = SecurityUtils.getUserId();
        int num = modelService.edit(Model);
        if (num > 0) {
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    @GetMapping("/list")
    public TableDataInfo list(Model model) {
        startPage();
        List<Model> list = modelService.selectModel(model);
        return getDataTable(list);
    }

    @GetMapping("/getModelById")
    public AjaxResult getStageById(Integer id) {
        return AjaxResult.success(modelService.getModelById(id));
    }
}
