package com.example.richapplication.controller;

import com.example.richapplication.model.User;
import com.example.richapplication.service.ImageService;
import com.example.richapplication.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/img")
@CrossOrigin("*")
public class ImgController {
    private final UserService userService;

    private final ImageService imageService;

    public ImgController(UserService userService, ImageService imageService){
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> upload(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return imageService.updateProfilePicture(id, file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName){
        return imageService.downloadImage(fileName);
    }
}