package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Stage;
import com.ruoyi.system.service.IStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * ClassName: StageController
 * Package: com.ruoyi.web.controller.system
 * Description:
 *
 * @Author 李晓赞
 * @Create 2025/4/24 8:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/stage")
public class StageController extends BaseController {
    @Autowired
    private IStageService stageService;


    //增
    @PostMapping
    public AjaxResult add(@RequestBody Stage stage) {
        if (StringUtils.isBlank(stage.getName())) {
            return AjaxResult.error("阶段名称不能为空");
        }
        if (StringUtils.isBlank(stage.getCode())) {
            return AjaxResult.error("阶段编码不能为空");
        }
        Long userId = SecurityUtils.getUserId();
        stage.setCreateBy(userId.toString());
        stage.setCreateTime(new Date());
        stage.setUpdateBy(userId.toString());
        stage.setUpdateTime(new Date());
        int num = stageService.add(stage);
        if (num > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    //删-单个
    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Integer id) {
        int num = stageService.deleteStage(id);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    //删-多个（修改URL路径）
    @DeleteMapping("/batchDelete")
    public AjaxResult batchRemove(@RequestBody Integer[] ids) {
        int num = stageService.deleteStage(ids);
        if (num > 0) {
            return AjaxResult.success("批量删除成功");
        } else {
            return AjaxResult.error("批量删除失败");
        }
    }

    //改
    @PutMapping
    public AjaxResult edit(@RequestBody Stage stage) {
        //获取操作人
        Long userId = SecurityUtils.getUserId();
        stage.setUpdateBy(userId.toString());
        stage.setUpdateTime(new Date());
        int num = stageService.edit(stage);
        if (num > 0) {
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    @GetMapping("/list")
    public TableDataInfo list(Stage stage) {
        startPage();
        List<Stage> list = stageService.selectStage(stage);
        return getDataTable(list);
    }

    @GetMapping("/getStageById")
    public AjaxResult getStageById(Integer id) {
        return AjaxResult.success(stageService.getStageById(id));
    }
}

