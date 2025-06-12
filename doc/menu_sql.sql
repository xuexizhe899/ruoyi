-- 添加图书管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书管理', 1, 1, 'book', 'system/book/index', 1, 0, 'C', '0', '0', 'system:book:list', 'book', 'admin', SYSDATE(), 'admin', SYSDATE(), '图书管理菜单');

-- 图书管理按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书查询', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '图书管理') a), 1, '#', '', 1, 0, 'F', '0', '0', 'system:book:query', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书新增', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '图书管理') a), 2, '#', '', 1, 0, 'F', '0', '0', 'system:book:add', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书修改', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '图书管理') a), 3, '#', '', 1, 0, 'F', '0', '0', 'system:book:edit', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书删除', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '图书管理') a), 4, '#', '', 1, 0, 'F', '0', '0', 'system:book:remove', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('图书导出', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '图书管理') a), 5, '#', '', 1, 0, 'F', '0', '0', 'system:book:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 添加分类管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类管理', 1, 2, 'category', 'system/category/index', 1, 0, 'C', '0', '0', 'system:category:list', 'tree', 'admin', SYSDATE(), 'admin', SYSDATE(), '分类管理菜单');

-- 分类管理按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类查询', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '分类管理') a), 1, '#', '', 1, 0, 'F', '0', '0', 'system:category:query', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类新增', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '分类管理') a), 2, '#', '', 1, 0, 'F', '0', '0', 'system:category:add', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类修改', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '分类管理') a), 3, '#', '', 1, 0, 'F', '0', '0', 'system:category:edit', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类删除', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '分类管理') a), 4, '#', '', 1, 0, 'F', '0', '0', 'system:category:remove', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类导出', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '分类管理') a), 5, '#', '', 1, 0, 'F', '0', '0', 'system:category:export', '#', 'admin', SYSDATE(), '', NULL, '');

-- 添加订单管理菜单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单管理', 1, 3, 'order', 'system/order/index', 1, 0, 'C', '0', '0', 'system:order:list', 'shopping', 'admin', SYSDATE(), 'admin', SYSDATE(), '订单管理菜单');

-- 订单管理按钮
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单查询', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '订单管理') a), 1, '#', '', 1, 0, 'F', '0', '0', 'system:order:query', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单新增', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '订单管理') a), 2, '#', '', 1, 0, 'F', '0', '0', 'system:order:add', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单修改', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '订单管理') a), 3, '#', '', 1, 0, 'F', '0', '0', 'system:order:edit', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单删除', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '订单管理') a), 4, '#', '', 1, 0, 'F', '0', '0', 'system:order:remove', '#', 'admin', SYSDATE(), '', NULL, '');

INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('订单导出', (SELECT menu_id FROM (SELECT * FROM sys_menu WHERE menu_name = '订单管理') a), 5, '#', '', 1, 0, 'F', '0', '0', 'system:order:export', '#', 'admin', SYSDATE(), '', NULL, ''); 