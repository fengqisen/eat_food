package com.eatwhat.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    Map<String, Object> upload(MultipartFile file);
    void delete(String publicId);
}
