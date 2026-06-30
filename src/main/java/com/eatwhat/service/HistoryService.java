package com.eatwhat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eatwhat.entity.FoodHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HistoryService extends IService<FoodHistory> {

    FoodHistory recordRecommendation(Long userId, Long foodId);

    boolean confirmChoice(Long historyId, Integer isChosen, String note);

    List<Map<String, Object>> getHistoryList(Long userId, LocalDate startDate, LocalDate endDate);
}