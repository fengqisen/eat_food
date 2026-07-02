package com.eatwhat.service.impl;

import com.eatwhat.entity.FoodItem;
import com.eatwhat.service.FoodService;
import com.eatwhat.service.HistoryService;
import com.eatwhat.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private FoodService foodService;

    @Autowired
    private HistoryService historyService;

    @Override
    public Map<String, Object> recommendToday(Long userId) {
        Map<String, Object> result = new HashMap<>();
        
        String currentMealType = detectCurrentMealType();
        
        List<FoodItem> availableFood = foodService.lambdaQuery()
                .eq(FoodItem::getUserId, userId)
                .eq(FoodItem::getStatus, 1)
                .and(wrapper -> wrapper
                        .eq(FoodItem::getMealType, currentMealType)
                        .or()
                        .eq(FoodItem::getMealType, "ALL")
                        .or()
                        .isNull(FoodItem::getMealType)
                        .or()
                        .eq(FoodItem::getMealType, "")
                )
                .list();

        if (availableFood.isEmpty()) {
            result.put("success", false);
            result.put("message", "还没有可用的" + getMealTypeName(currentMealType) + "食物，请先添加！");
            return result;
        }

        FoodItem selectedFood = weightedRandomSelect(availableFood);

        result.put("success", true);
        result.put("food", selectedFood);
        result.put("mealType", currentMealType);
        result.put("mealTypeName", getMealTypeName(currentMealType));
        result.put("reason", generateReason(selectedFood));
        return result;
    }

    private String detectCurrentMealType() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        int hour = now.getHour();
        int minute = now.getMinute();
        int totalMinutes = hour * 60 + minute;
        
        if (totalMinutes >= 300 && totalMinutes <= 600) {
            return "BREAKFAST";
        } else if (totalMinutes >= 601 && totalMinutes <= 870) {
            return "LUNCH";
        } else if (totalMinutes >= 871 && totalMinutes <= 1320) {
            return "DINNER";
        } else {
            return "DINNER";
        }
    }

    private String getMealTypeName(String mealType) {
        switch (mealType) {
            case "BREAKFAST":
                return "早餐";
            case "LUNCH":
                return "中餐";
            case "DINNER":
                return "晚餐";
            default:
                return "餐";
        }
    }

    @Override
    public Map<String, Object> recommendAnother(Long userId) {
        return recommendToday(userId);
    }

    private FoodItem weightedRandomSelect(List<FoodItem> foodList) {
        List<Double> weights = new ArrayList<>();
        double totalWeight = 0;

        for (FoodItem food : foodList) {
            double weight = calculateWeight(food);
            weights.add(weight);
            totalWeight += weight;
        }

        double random = new Random().nextDouble() * totalWeight;
        double current = 0;

        for (int i = 0; i < foodList.size(); i++) {
            current += weights.get(i);
            if (current >= random) {
                return foodList.get(i);
            }
        }

        return foodList.get(0);
    }

    private double calculateWeight(FoodItem food) {
        int rating = food.getRating() != null ? food.getRating() : 3;
        
        long daysSinceLastEat = 0;
        if (food.getLastEatTime() != null) {
            daysSinceLastEat = ChronoUnit.DAYS.between(food.getLastEatTime(), LocalDateTime.now());
        }
        
        double weight = rating * 2 + daysSinceLastEat;
        return Math.max(weight, 1);
    }

    private String generateReason(FoodItem food) {
        StringBuilder reason = new StringBuilder();
        
        if (food.getLastEatTime() != null) {
            long days = ChronoUnit.DAYS.between(food.getLastEatTime(), LocalDateTime.now());
            if (days > 7) {
                reason.append("你已经").append(days).append("天没吃了");
            } else if (days > 3) {
                reason.append("有点久没吃了");
            }
        }
        
        if (food.getRating() != null && food.getRating() >= 4) {
            if (reason.length() > 0) {
                reason.append(" + ");
            }
            reason.append("评分较高");
        }
        
        if (reason.length() == 0) {
            reason.append("随机推荐");
        }
        
        return reason.toString();
    }
}