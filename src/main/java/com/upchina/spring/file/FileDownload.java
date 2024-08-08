package com.upchina.spring.file;

import cn.hutool.http.server.HttpServerResponse;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by anjunli on  2022/4/7
 **/
@Controller
public class FileDownload {

    /**
     * 文件下载接口
     */
    @RequestMapping("/file/download")
    public void getDownload(HttpServletResponse response) throws IOException {
        String fullPath = "src/main/resources/static/用户明细导入模板.xlsx";
        File downloadFile = new File(fullPath);

        String fileName = downloadFile.getName();

        response.setContentLength((int) downloadFile.length());
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        InputStream myStream = null;
        try {
            myStream = new FileInputStream(fullPath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (myStream != null) {
                myStream.close();
            }
        }

    }

    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(Long id)
            throws IOException {
        String filePath = "src/main/resources/用户明细导入模板.xlsx";
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

}
