package com.eatwhat.service;

import com.eatwhat.entity.FoodItem;

import java.util.Map;

public interface RecommendService {

    Map<String, Object> recommendToday(Long userId);

    Map<String, Object> recommendAnother(Long userId);
}