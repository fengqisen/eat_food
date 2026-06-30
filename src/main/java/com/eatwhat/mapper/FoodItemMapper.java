package com.eatwhat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eatwhat.entity.FoodItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FoodItemMapper extends BaseMapper<FoodItem> {

    List<FoodItem> selectAvailableByUserId(@Param("userId") Long userId);
}