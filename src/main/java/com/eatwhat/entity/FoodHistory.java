package com.eatwhat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("food_history")
public class FoodHistory {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long foodId;

    private LocalDate date;

    private Integer isChosen;

    private String note;

    private LocalDateTime createTime;
}