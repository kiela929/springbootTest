package com.example.springbootvuetest.controller.board;

import com.example.springbootvuetest.service.board.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
@AllArgsConstructor
@RequestMapping("/file2")
public class FileController2 {

    private final FileService fileService;

    @PostMapping("/download")
    public ResponseEntity<Resource> download(HttpServletRequest request, @RequestParam String filename) {
        Resource resource = fileService.loadFileAsResource(filename);

        String contentType = null;

        try {
            filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {

        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + filename).body(resource);
    }
}
