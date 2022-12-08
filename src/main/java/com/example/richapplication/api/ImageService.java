package com.example.richapplication.api;

import com.example.richapplication.dto.UpdateImageAnswer;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ResponseEntity<UpdateImageAnswer> updateProfilePicture(Integer id, MultipartFile file);
    ResponseEntity<Resource> downloadImage(String fileName);
}
