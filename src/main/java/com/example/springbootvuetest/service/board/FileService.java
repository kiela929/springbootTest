package com.example.springbootvuetest.service.board;

import com.example.springbootvuetest.exception.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    public Resource loadFileAsResource(String fileName) {
        try {
            Path targetLocation = Paths.get("/users/dev/desktop").resolve(fileName);
            Resource resource = new UrlResource(targetLocation.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("file not found " + fileName);
            }
        } catch (Exception e) {
            throw new MyFileNotFoundException("file not found " + fileName, e);
        }
    }
}
