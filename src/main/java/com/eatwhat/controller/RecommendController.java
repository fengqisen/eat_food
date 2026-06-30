package com.eatwhat.controller;

import com.eatwhat.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/today")
    public Map<String, Object> recommendToday(@RequestParam Long userId) {
        return recommendService.recommendToday(userId);
    }

    @GetMapping("/another")
    public Map<String, Object> recommendAnother(@RequestParam Long userId) {
        return recommendService.recommendAnother(userId);
    }
}