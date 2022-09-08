package com.example.richapplication.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/img")
public class ImageController {
    @GetMapping("/{fileName}")
public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
	Path path = Paths.get("src/main/resources/static/" + fileName);
	Resource resource = null;
	try {
		resource = new UrlResource(path.toUri());
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	return ResponseEntity.ok()
			//.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
			.body(resource);
}
private String getUniqueFileName(String oldName, Integer userId){
	return userId + "-" + oldName;
}
}
