package com.example.springbootvuetest.common.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {

    Map<String, String> upload(MultipartFile file, String filePath) throws Exception;

    Map<String, String> chunkUpload(MultipartFile file, String filePath, String chunkFileName, int chunkNumber, int totalChunks) throws Exception;

    Resource loadFileAsResource(String fileName, String dir) throws Exception;

    Boolean delete(String fileName, String filePath) throws Exception;

}
