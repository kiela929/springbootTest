package com.example.springbootvuetest.common.file;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${uploadDir:}") String UPLOAD_DIR; // properties 에서 설정한 값 사용 가능, : 뒤에는 default 값
    @Value("${webServer:}") String WEB_SERVER; // properties 에서 설정한 값 사용 가능, : 뒤에는 default 값

    @Override
    public Map<String, String> upload(MultipartFile file, String filePath) throws Exception {
        filePath = "/" + filePath.replaceAll("/", "") + "/";
        String uploadDir = WEB_SERVER + UPLOAD_DIR + filePath;

        String originalFileName = file.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String realFileName = UUID.randomUUID() + "." + ext;

        Path fullFilePath = Paths.get(uploadDir, realFileName);

        Map<String, String> fileMap = null;
        if (!Files.exists(fullFilePath)) {
            // 파일 생성
            Files.createFile(fullFilePath);
            // 저장
            Files.write(fullFilePath, file.getBytes());

            fileMap = new HashMap<>();
            fileMap.put("fileName", originalFileName);
            fileMap.put("realFileName", realFileName);

        } else {
            log.info("File uploaded failed: exists file name");
            throw new Exception();
        }
        return fileMap;
    }

    @Override
    public Map<String, String> chunkUpload(MultipartFile file, String filePath, String chunkFileName, int chunkNumber, int totalChunks) throws Exception {
        filePath = "/" + filePath.replaceAll("/", "") + "/";
        String uploadDir = WEB_SERVER + UPLOAD_DIR + filePath;

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 임시 저장 파일 이름
        String filename = chunkFileName + ".part" + chunkNumber;
        Path fullFilePath = Paths.get(uploadDir, filename);
        String outputFilename = "";
        // 임시 저장
        Files.write(fullFilePath, file.getBytes());
        try {
            // 마지막 조각이 전송 됐을 경우
            if (chunkNumber == totalChunks - 1) {
                String[] split = file.getOriginalFilename().split("\\.");
                outputFilename = UUID.randomUUID() + "." + split[split.length - 1];
                Path outputFile = Paths.get(uploadDir, outputFilename);
                Files.createFile(outputFile);

                // 임시 파일들을 하나로 합침
                for (int i = 0; i < totalChunks; i++) {
                    Path chunkFile = Paths.get(uploadDir, chunkFileName + ".part" + i);
                    Files.write(outputFile, Files.readAllBytes(chunkFile), StandardOpenOption.APPEND);
                    Files.delete(chunkFile);
                }
                log.info("File uploaded successfully");
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("fileName", file.getOriginalFilename());
                fileMap.put("realFileName", outputFilename);
                fileMap.put("isDone", "Y");

                return fileMap;
            } else {
                return null;
            }
        } catch (Exception e) {
            // 임시 파일 삭제
            for (int i = 0; i < totalChunks; i++) {
                Path chunkFile = Paths.get(uploadDir, chunkFileName + ".part" + i);
                if (Files.exists(chunkFile)) {
                    Files.delete(chunkFile);
                }
            }
            Files.delete(Paths.get(uploadDir, outputFilename));
            throw new Exception("파일 업로드 에러");
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName, String filePath) throws Exception {
        try {
            filePath = "/" + filePath.replaceAll("/", "") + "/";
            Path fullFilePath = Paths.get(WEB_SERVER + UPLOAD_DIR + filePath).resolve(fileName).normalize();

            Resource resource = new UrlResource(fullFilePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MalformedURLException();
        }
    }

    @Override
    public Boolean delete(String fileName, String filePath) throws Exception {
        if (filePath == null || "".equals(filePath)) {
            filePath = "/temp_upload/";
        } else {
            filePath = "/" + filePath.replaceAll("/", "") + "/";
        }
        Boolean isDelete = false;
        if (fileName != null && !"".equals(fileName)) {
            String fullFilename = WEB_SERVER + UPLOAD_DIR + filePath + fileName;
            File file = new File(fullFilename);
            if (file.exists()) {
                boolean bolYn = false;
                while (!bolYn) {
                    bolYn = file.delete();
                }
                isDelete = true;
            }
        }
        return isDelete;
    }
}
