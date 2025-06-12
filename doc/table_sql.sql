-- 创建图书分类表
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父类ID，0表示顶级分类',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='图书分类表';

-- 创建图书表
CREATE TABLE IF NOT EXISTS `book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `book_name` varchar(100) NOT NULL COMMENT '图书名称',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `description` text COMMENT '描述',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图片URL',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`book_id`),
  KEY `idx_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='图书表';

-- 创建订单表
CREATE TABLE IF NOT EXISTS `book_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_number` varchar(64) NOT NULL COMMENT '订单编号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态（0待支付，1已支付，2已发货，3已完成，4已取消）',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `shipping_address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `idx_order_number` (`order_number`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 创建订单明细表
CREATE TABLE IF NOT EXISTS `book_order_item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `book_id` bigint(20) NOT NULL COMMENT '图书ID',
  `quantity` int(11) DEFAULT '1' COMMENT '购买数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '购买单价',
  PRIMARY KEY (`item_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_book_id` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- 添加约束
ALTER TABLE `book`
  ADD CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE SET NULL;

ALTER TABLE `book_order_item`
  ADD CONSTRAINT `fk_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `book_order` (`order_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_order_item_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE; 