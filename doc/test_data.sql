-- 添加测试分类数据
INSERT INTO `category` (`category_id`, `category_name`, `parent_id`, `create_time`, `update_time`) VALUES
(1, '文学', 0, NOW(), NOW()),
(2, '小说', 1, NOW(), NOW()),
(3, '散文', 1, NOW(), NOW()),
(4, '科技', 0, NOW(), NOW()),
(5, '计算机', 4, NOW(), NOW()),
(6, '人工智能', 5, NOW(), NOW()),
(7, '编程语言', 5, NOW(), NOW()),
(8, '经济管理', 0, NOW(), NOW()),
(9, '市场营销', 8, NOW(), NOW()),
(10, '金融投资', 8, NOW(), NOW());

-- 添加测试图书数据
INSERT INTO `book` (`book_id`, `book_name`, `author`, `category_id`, `price`, `stock`, `description`, `image_url`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, '平凡的世界', '路遥', 2, 59.80, 100, '《平凡的世界》是中国作家路遥创作的一部全景式地表现中国当代城乡社会生活的百万字长篇小说。', 'https://img3.doubanio.com/view/subject/l/public/s1144911.jpg', 'admin', NOW(), 'admin', NOW()),
(2, '活着', '余华', 2, 39.50, 80, '《活着》是作家余华的代表作之一，讲述了在大时代背景下，普通人福贵的坎坷命运。', 'https://img2.doubanio.com/view/subject/l/public/s29053580.jpg', 'admin', NOW(), 'admin', NOW()),
(3, '朝花夕拾', '鲁迅', 3, 25.00, 60, '《朝花夕拾》是鲁迅的散文集，收录了鲁迅自1926年创作的10篇回忆性散文。', 'https://img1.doubanio.com/view/subject/l/public/s2875823.jpg', 'admin', NOW(), 'admin', NOW()),
(4, '人工智能：一种现代方法', 'Stuart Russell', 6, 89.00, 30, '这是一本全面介绍人工智能领域的经典教材，涵盖了从基础理论到前沿研究的各个方面。', 'https://img9.doubanio.com/view/subject/l/public/s28406187.jpg', 'admin', NOW(), 'admin', NOW()),
(5, 'Java编程思想', 'Bruce Eckel', 7, 108.00, 40, '本书是Java程序员必读的经典著作，全面介绍了Java编程的各个方面。', 'https://img3.doubanio.com/view/subject/l/public/s27243455.jpg', 'admin', NOW(), 'admin', NOW()),
(6, 'Python编程：从入门到实践', 'Eric Matthes', 7, 89.00, 50, '本书是一本针对初学者的Python编程指南，通过大量示例带领读者逐步掌握Python编程技能。', 'https://img3.doubanio.com/view/subject/l/public/s29677623.jpg', 'admin', NOW(), 'admin', NOW()),
(7, '营销管理', '菲利普·科特勒', 9, 99.00, 25, '《营销管理》是营销学领域的经典教材，全面介绍了现代营销理论和实践。', 'https://img2.doubanio.com/view/subject/l/public/s1834393.jpg', 'admin', NOW(), 'admin', NOW()),
(8, '聪明的投资者', '本杰明·格雷厄姆', 10, 88.00, 35, '本书是投资领域的经典著作，被誉为"投资者的圣经"。', 'https://img1.doubanio.com/view/subject/l/public/s2340890.jpg', 'admin', NOW(), 'admin', NOW()),
(9, '人类简史', '尤瓦尔·赫拉利', 4, 68.00, 45, '《人类简史》是以色列历史学家尤瓦尔·赫拉利创作的一部关于人类发展历程的著作。', 'https://img3.doubanio.com/view/subject/l/public/s27814883.jpg', 'admin', NOW(), 'admin', NOW()),
(10, '三体', '刘慈欣', 2, 23.00, 70, '《三体》是刘慈欣创作的长篇科幻小说，是中国科幻文学的代表作品。', 'https://img9.doubanio.com/view/subject/l/public/s2768378.jpg', 'admin', NOW(), 'admin', NOW());

-- 添加测试订单数据
INSERT INTO `book_order` (`order_id`, `user_id`, `order_number`, `order_time`, `total_amount`, `status`, `receiver_name`, `receiver_phone`, `shipping_address`, `create_time`, `update_time`) VALUES
(1, 1, '16236547890001', '2023-06-14 10:25:36', 207.80, 3, '张三', '13800138001', '北京市海淀区中关村南大街5号', NOW(), NOW()),
(2, 1, '16236547890002', '2023-07-20 15:30:42', 89.00, 2, '张三', '13800138001', '北京市海淀区中关村南大街5号', NOW(), NOW()),
(3, 2, '16236547890003', '2023-08-05 09:15:20', 197.00, 1, '李四', '13900139002', '上海市浦东新区张江高科技园区', NOW(), NOW()),
(4, 3, '16236547890004', '2023-09-12 14:20:35', 68.00, 0, '王五', '13700137003', '广州市天河区体育西路123号', NOW(), NOW()),
(5, 2, '16236547890005', '2023-10-18 11:40:28', 88.00, 4, '李四', '13900139002', '上海市浦东新区张江高科技园区', NOW(), NOW());

-- 添加测试订单明细数据
INSERT INTO `book_order_item` (`item_id`, `order_id`, `book_id`, `quantity`, `price`) VALUES
(1, 1, 1, 2, 59.80),
(2, 1, 3, 1, 25.00),
(3, 1, 7, 1, 99.00),
(4, 2, 4, 1, 89.00),
(5, 3, 5, 1, 108.00),
(6, 3, 6, 1, 89.00),
(7, 4, 9, 1, 68.00),
(8, 5, 8, 1, 88.00); 