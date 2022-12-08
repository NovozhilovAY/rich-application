package com.example.richapplication.controller;

import com.example.richapplication.api.ImageService;
import com.example.richapplication.api.UserService;
import com.example.richapplication.dto.UpdateImageAnswer;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/update/{id}")
    public ResponseEntity<UpdateImageAnswer> upload(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return imageService.updateProfilePicture(id, file);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName){
        return imageService.downloadImage(fileName);
    }
}