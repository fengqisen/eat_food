package com.eatwhat.controller;

import com.eatwhat.entity.FoodHistory;
import com.eatwhat.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/list")
    public Map<String, Object> getHistoryList(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Map<String, Object>> list = historyService.getHistoryList(userId, startDate, endDate);
            result.put("success", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PostMapping("/confirm")
    public Map<String, Object> confirmChoice(
            @RequestParam Long historyId,
            @RequestParam Integer isChosen,
            @RequestParam(required = false) String note) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = historyService.confirmChoice(historyId, isChosen, note);
            result.put("success", success);
            result.put("message", success ? "确认成功" : "确认失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PostMapping("/record")
    public Map<String, Object> recordRecommendation(
            @RequestParam Long userId, 
            @RequestParam Long foodId,
            @RequestParam(required = false) String mealType) {
        Map<String, Object> result = new HashMap<>();
        try {
            FoodHistory history = historyService.recordRecommendation(userId, foodId, mealType);
            result.put("success", true);
            result.put("data", history);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}