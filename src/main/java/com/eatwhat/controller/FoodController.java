package com.eatwhat.controller;

import com.eatwhat.entity.FoodItem;
import com.eatwhat.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public Map<String, Object> addFood(@RequestBody FoodItem foodItem) {
        Map<String, Object> result = new HashMap<>();
        try {
            FoodItem saved = foodService.addFood(foodItem);
            result.put("success", true);
            result.put("data", saved);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getFoodList(@RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<FoodItem> list = foodService.getFoodList(userId);
            result.put("success", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getFoodById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            FoodItem food = foodService.getFoodById(id);
            if (food != null) {
                result.put("success", true);
                result.put("data", food);
            } else {
                result.put("success", false);
                result.put("message", "食物不存在");
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updateFood(@RequestBody FoodItem foodItem) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = foodService.updateFood(foodItem);
            result.put("success", success);
            result.put("message", success ? "更新成功" : "更新失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteFood(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = foodService.deleteFood(id);
            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PutMapping("/status/{id}")
    public Map<String, Object> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = foodService.updateStatus(id, status);
            result.put("success", success);
            result.put("message", success ? "状态更新成功" : "状态更新失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}