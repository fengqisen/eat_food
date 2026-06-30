package com.eatwhat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eatwhat.entity.FoodHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface FoodHistoryMapper extends BaseMapper<FoodHistory> {

    List<FoodHistory> selectByUserIdAndDateRange(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}