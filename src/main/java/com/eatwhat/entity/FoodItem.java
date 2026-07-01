package com.eatwhat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("food_item")
public class FoodItem {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String name;

    private String type;

    private String tag;

    private Integer rating;

    private Integer status;

    private LocalDateTime lastEatTime;

    private LocalDateTime createTime;
}