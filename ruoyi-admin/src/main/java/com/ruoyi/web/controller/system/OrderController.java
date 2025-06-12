package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderItem;
import com.ruoyi.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理控制器
 *
 * @author 李晓赞
 */
@RestController
@RequestMapping("/system/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/list")
    public TableDataInfo list(Order order) {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }


    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order) {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<>(Order.class);
        util.exportExcel(response, list, "订单数据");
    }


    @GetMapping("/{orderId}")
    public AjaxResult getInfo(@PathVariable Long orderId) {
        Order order = orderService.getOrderDetail(orderId);
        if (order == null) {
            return AjaxResult.error("订单不存在");
        }
        return AjaxResult.success(order);
    }


    @GetMapping("/items/{orderId}")
    public AjaxResult getOrderItems(@PathVariable Long orderId) {
        List<OrderItem> items = orderService.getOrderItems(orderId);
        return AjaxResult.success(items);
    }


    @PostMapping
    public AjaxResult add(@RequestBody Order order) {
        // 创建订单
        List<OrderItem> items = order.getOrderItems() != null ? order.getOrderItems() : new ArrayList<>();
        try {
            Long orderId = orderService.createOrder(order, items);
            return AjaxResult.success("创建订单成功", orderId);
        } catch (Exception e) {
            logger.error("创建订单失败:", e);
            return AjaxResult.error("创建订单失败: " + e.getMessage());
        }
    }


    @PutMapping
    public AjaxResult edit(@RequestBody Order order) {
        try {
            int rows = orderService.updateOrder(order);
            return rows > 0 ? AjaxResult.success("修改成功") : AjaxResult.error("修改失败");
        } catch (Exception e) {
            logger.error("修改订单失败:", e);
            return AjaxResult.error("修改订单失败: " + e.getMessage());
        }
    }


    @DeleteMapping("/{orderId}")
    public AjaxResult remove(@PathVariable Long orderId) {
        try {
            int rows = orderService.deleteOrder(orderId);
            return rows > 0 ? AjaxResult.success("删除成功") : AjaxResult.error("删除失败");
        } catch (Exception e) {
            logger.error("删除订单失败:", e);
            return AjaxResult.error("删除订单失败: " + e.getMessage());
        }
    }


    @DeleteMapping("/batch")
    public AjaxResult batchRemove(@RequestBody Long[] ids) {
        try {
            int rows = orderService.deleteOrder(ids);
            return rows > 0 ? AjaxResult.success("批量删除成功") : AjaxResult.error("批量删除失败");
        } catch (Exception e) {
            logger.error("批量删除订单失败:", e);
            return AjaxResult.error("批量删除订单失败: " + e.getMessage());
        }
    }


    @PutMapping("/status/{orderId}/{status}")
    public AjaxResult updateStatus(@PathVariable Long orderId, @PathVariable Integer status) {
        // 验证状态值是否有效
        if (status < 0 || status > 4) {
            return AjaxResult.error("无效的订单状态");
        }

        try {
            int rows = orderService.updateOrderStatus(orderId, status);
            return rows > 0 ? AjaxResult.success("状态更新成功") : AjaxResult.error("状态更新失败");
        } catch (Exception e) {
            logger.error("更新订单状态失败:", e);
            return AjaxResult.error("更新订单状态失败: " + e.getMessage());
        }
    }
} 