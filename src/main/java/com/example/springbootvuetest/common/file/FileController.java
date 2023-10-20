package com.example.springbootvuetest.common.file;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(
            @RequestParam("path") String filePath,
            @RequestPart("file") MultipartFile file) throws Exception {
        Map<String, String> resultMap = fileService.upload(file, filePath);
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/chunkUpload")
    public ResponseEntity<?> chunkFileUpload(
            @RequestParam("path") String filePath,
            @RequestParam("chunkFileName") String chunkFileName,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("totalChunks") int totalChunks,
            @RequestPart("chunk") MultipartFile file) throws Exception {
        log.info("files.getOriginalFilename() ===> {}", file.getOriginalFilename());
        Map<String, String> resultMap = fileService.chunkUpload(file, filePath, chunkFileName, chunkNumber, totalChunks);
        return (resultMap != null && resultMap.get("isDone").equals("Y")) ?
                ResponseEntity.ok(resultMap) : ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam("fileName") String fileName,
            @RequestParam("filePath") String filePath,
            HttpServletRequest request) throws Exception {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName, filePath);

        // Try to determine file's content type
        String contentType = null;
        int fileSize = 0;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            fileSize = (int) resource.getFile().length();
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Length")
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSize))
                .header(HttpHeaders.TRANSFER_ENCODING, "binary;")
                .header(HttpHeaders.PRAGMA, "no-cache;")
                .header(HttpHeaders.EXPIRES, "0;")
                .body(resource);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFile(
            @RequestParam("fileName") String fileName,
            @RequestParam("filePath") String filePath,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Boolean isDelete = false;
        try {
            isDelete = fileService.delete(fileName, filePath);
        } catch (Exception e) {

        }
        return isDelete ? ResponseEntity.ok("File deleted successfully") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
