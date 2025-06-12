package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.ModelStage;
import com.ruoyi.system.service.ModelStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: MSvoController
 * Package: com.ruoyi.web.controller.system
 * Description: 模型阶段关联控制器
 *
 * @Author 李晓赞
 * @Create 2025/5/22 9:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/model/stage")
public class MSvoController extends BaseController {

    @Autowired
    private ModelStageService modelStageService;

    /**
     * 查询模型关联的阶段列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ModelStage modelStage) {
        startPage();
        List<ModelStage> list = modelStageService.select(modelStage);
        return getDataTable(list);
    }

    /**
     * 添加单个模型阶段关联
     */
    @PostMapping
    public AjaxResult add(@RequestBody ModelStage modelStage) {
        int num = modelStageService.add(modelStage);
        if (num > 0) {
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.error("添加失败");
        }
    }

    /**
     * 批量添加模型阶段关联
     */
    @PostMapping("/batch")
    public AjaxResult batchAdd(@RequestParam("modelId") Integer modelId, @RequestBody Integer[] stageIds) {
        int num = modelStageService.batchAdd(modelId, stageIds);
        if (num > 0) {
            return AjaxResult.success("批量添加成功");
        } else {
            return AjaxResult.error("批量添加失败");
        }
    }

    /**
     * 删除单个模型阶段关联
     */
    @DeleteMapping
    public AjaxResult remove(@RequestBody ModelStage modelStage) {
        int num = modelStageService.delete(modelStage);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    /**
     * 根据模型ID删除所有关联
     */
    @DeleteMapping("/model/{modelId}")
    public AjaxResult removeByModelId(@PathVariable Integer modelId) {
        int num = modelStageService.deleteByModelId(modelId);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    /**
     * 根据阶段ID删除所有关联
     */
    @DeleteMapping("/stage/{stageId}")
    public AjaxResult removeByStageId(@PathVariable Integer stageId) {
        int num = modelStageService.deleteByStageId(stageId);
        if (num > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.error("删除失败");
        }
    }

    /**
     * 更新模型关联的阶段
     */
    @PutMapping("/{modelId}")
    public AjaxResult update(@PathVariable Integer modelId, @RequestBody Integer[] stageIds) {
        int num = modelStageService.updateModelStages(modelId, stageIds);
        if (num > 0) {
            return AjaxResult.success("更新成功");
        } else {
            return AjaxResult.error("更新失败");
        }
    }
}

