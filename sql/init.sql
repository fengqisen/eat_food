CREATE DATABASE IF NOT EXISTS eat_what DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE eat_what;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `food_item`;
CREATE TABLE `food_item` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '食物ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `name` VARCHAR(100) NOT NULL COMMENT '食物名称',
    `type` VARCHAR(50) DEFAULT '其他' COMMENT '类型（中餐/日料/快餐/饮料）',
    `tag` VARCHAR(200) DEFAULT '' COMMENT '标签（辣/甜/便宜/健康）',
    `rating` INT DEFAULT 3 COMMENT '用户评分（1-5）',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1可用 0禁用',
    `last_eat_time` DATETIME DEFAULT NULL COMMENT '最近一次吃的时间',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食物表';

DROP TABLE IF EXISTS `food_history`;
CREATE TABLE `food_history` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '记录ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `food_id` BIGINT NOT NULL COMMENT '食物ID',
    `date` DATE NOT NULL COMMENT '日期',
    `is_chosen` TINYINT DEFAULT 0 COMMENT '是否被选中',
    `note` VARCHAR(500) DEFAULT '' COMMENT '备注',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX `idx_user_date` (`user_id`, `date`),
    INDEX `idx_food_id` (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史记录表';