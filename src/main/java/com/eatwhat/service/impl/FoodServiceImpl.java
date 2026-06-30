package com.eatwhat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eatwhat.entity.FoodItem;
import com.eatwhat.mapper.FoodItemMapper;
import com.eatwhat.service.FoodService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodItemMapper, FoodItem> implements FoodService {

    @Override
    public FoodItem addFood(FoodItem foodItem) {
        if (foodItem.getRating() == null) {
            foodItem.setRating(3);
        }
        if (foodItem.getStatus() == null) {
            foodItem.setStatus(1);
        }
        if (foodItem.getType() == null || foodItem.getType().isEmpty()) {
            foodItem.setType("其他");
        }
        foodItem.setCreateTime(LocalDateTime.now());
        save(foodItem);
        return foodItem;
    }

    @Override
    public boolean updateFood(FoodItem foodItem) {
        return updateById(foodItem);
    }

    @Override
    public boolean deleteFood(Long id) {
        return removeById(id);
    }

    @Override
    public List<FoodItem> getFoodList(Long userId) {
        return lambdaQuery().eq(FoodItem::getUserId, userId).list();
    }

    @Override
    public FoodItem getFoodById(Long id) {
        return getById(id);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        FoodItem foodItem = getById(id);
        if (foodItem != null) {
            foodItem.setStatus(status);
            return updateById(foodItem);
        }
        return false;
    }

    @Override
    public boolean updateLastEatTime(Long id) {
        FoodItem foodItem = getById(id);
        if (foodItem != null) {
            foodItem.setLastEatTime(LocalDateTime.now());
            return updateById(foodItem);
        }
        return false;
    }
}