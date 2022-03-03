package com.jim.sprjfx.controller;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("/shsw")
public class JsonController {

    public boolean upload = true;
    public boolean download = true;


    public void toffUpload(){
        this.upload = !this.upload;
    }

    public void toffDownload(){
        this.download = !this.download;
    }


    @RequestMapping("/upload")
    public void upload(@RequestParam("file")MultipartFile file) throws Exception{
        if( this.upload == true ) {
            String filePath = "E:\\testFiles\\" + file.getOriginalFilename();
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }else{
            log.info("上传功能已经关闭");
        }
    }
    @RequestMapping("/download")
    public ResponseEntity download() throws Exception{
        if( this.download == true ) {
            FileSystemResource file = new FileSystemResource("abc.jpg");
            System.out.println(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attactment;filename=123.jpg");
            System.out.println(ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream())));
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(file.getInputStream()));
        }else{
            log.info("下载功能已经关闭");
            return null;
        }
    }


    /**
     * 接口：8080/shsw/json
     * 功能：接收json字符串
     * 开发：hsk
     * 时间：2022年2月14日11:46:00
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/json")
    public String JSONFUNCTION(@RequestBody JSONObject jsonObject) {
        log.info("JSON.Info--------------"+jsonObject);
        log.info("JSON.username----------"+jsonObject.get("username"));
        RestOperations restTemplate = new RestTemplate();
//        User forObject = restTemplate.getForObject("http://localhost:8081/shsw/test", User.class);
//        log.info("forObject" + forObject);

        String jsonString = "{'username':123,'nickname':234,'password':345,'salt':456}";
//        JSONObject json = JSONObject.parseObject(jsonString);
        String url = "http://localhost:8081/shsw/json";
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>();
        linkedHashMap.put("testLink","Success");
        String json = JSONObject.toJSONString(linkedHashMap);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, jsonObject,String.class);
//        return responseEntity.getBody();
//        String url = "http://localhost:8081/shsw/test";
//        URI uri = URI.create(url);
//        String s = restTemplate.getForObject(uri, String.class);
        log.info("RequestAnswer--------------------------" + responseEntity.getBody());

        return "Success";
    }



    @GetMapping(value = "/jsonget")
    public String test(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        return "Success";
    }
}
