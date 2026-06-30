package com.eatwhat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eatwhat.entity.FoodItem;

import java.util.List;

public interface FoodService extends IService<FoodItem> {

    FoodItem addFood(FoodItem foodItem);

    boolean updateFood(FoodItem foodItem);

    boolean deleteFood(Long id);

    List<FoodItem> getFoodList(Long userId);

    FoodItem getFoodById(Long id);

    boolean updateStatus(Long id, Integer status);

    boolean updateLastEatTime(Long id);
}