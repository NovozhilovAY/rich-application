package com.example.richapplication.service;

import com.example.richapplication.dto.UpdateImageAnswer;
import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    private final UserService userService;
    @Value("${root.image.url}")
    private String rootUrl;
    private final String FILE_BASE_PATH = "src/main/resources/static/";

    public ImageService(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UpdateImageAnswer> updateProfilePicture(Integer id, MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        User user = userService.getUserByID(id);
        if(user.getProfilePicture() != null){
            deleteImage(getFileName(user.getProfilePicture()));
        }
        Path path = Paths.get(FILE_BASE_PATH + getUniqueFileName(fileName, id));
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            user.setProfilePicture(rootUrl+getUniqueFileName(fileName, id));
            userService.updateUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateImageAnswer answer = new UpdateImageAnswer(user.getProfilePicture());
        return ResponseEntity.ok(answer);
    }

    public ResponseEntity<Resource> downloadImage(String fileName){
        Path path = Paths.get(FILE_BASE_PATH + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("File '" + fileName + "' not found");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename())
                .body(resource);
    }

    private void deleteImage(String filename){
        try {
            Files.delete(Paths.get(FILE_BASE_PATH + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileName(String fileUrl){
        return fileUrl.substring(rootUrl.length(), fileUrl.length());
    }

    private String getUniqueFileName(String oldName, Integer userId){
        return userId + "-" + oldName;
    }
}
