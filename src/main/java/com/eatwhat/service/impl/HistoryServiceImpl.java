package com.eatwhat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eatwhat.entity.FoodHistory;
import com.eatwhat.entity.FoodItem;
import com.eatwhat.mapper.FoodHistoryMapper;
import com.eatwhat.service.FoodService;
import com.eatwhat.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl extends ServiceImpl<FoodHistoryMapper, FoodHistory> implements HistoryService {

    @Autowired
    private FoodService foodService;

    @Override
    public FoodHistory recordRecommendation(Long userId, Long foodId) {
        return recordRecommendation(userId, foodId, null);
    }

    @Override
    public FoodHistory recordRecommendation(Long userId, Long foodId, String mealType) {
        FoodHistory history = new FoodHistory();
        history.setUserId(userId);
        history.setFoodId(foodId);
        history.setMealType(mealType);
        history.setDate(LocalDate.now());
        history.setIsChosen(0);
        history.setCreateTime(LocalDateTime.now());
        save(history);
        return history;
    }

    @Override
    public boolean confirmChoice(Long historyId, Integer isChosen, String note) {
        FoodHistory history = getById(historyId);
        if (history != null) {
            history.setIsChosen(isChosen);
            history.setNote(note);
            if (isChosen == 1) {
                foodService.updateLastEatTime(history.getFoodId());
            }
            return updateById(history);
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> getHistoryList(Long userId, LocalDate startDate, LocalDate endDate) {
        List<FoodHistory> histories = baseMapper.selectByUserIdAndDateRange(userId, startDate, endDate);
        List<Map<String, Object>> result = new ArrayList<>();

        for (FoodHistory history : histories) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", history.getId());
            item.put("date", history.getDate());
            item.put("mealType", history.getMealType());
            item.put("isChosen", history.getIsChosen());
            item.put("note", history.getNote());
            item.put("createTime", history.getCreateTime());

            FoodItem food = foodService.getById(history.getFoodId());
            if (food != null) {
                item.put("foodName", food.getName());
                item.put("foodType", food.getType());
                item.put("foodTag", food.getTag());
            }

            result.add(item);
        }

        return result;
    }
}