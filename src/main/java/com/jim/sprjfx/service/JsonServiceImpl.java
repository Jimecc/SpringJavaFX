package com.jim.sprjfx.service;

import com.jim.sprjfx.entity.DataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class JsonServiceImpl implements JsonService {
    @Autowired
    private DataService dataService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean JsonData(JSONObject jsonObject,String url) {
        DataEntity data = new DataEntity();
        try {
            data.setDataNo((Integer) jsonObject.get("dataNo"));
            data.setDatatype((String) jsonObject.get("datatype"));
            dataService.insertData(data);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, jsonObject, String.class);
            log.info("insertData && transpondData---" + responseEntity.getBody());
        } catch (RestClientException e) {
//            e.printStackTrace();
            log.error("解析错误，格式不正确。"+e);
        }

        return true;
    }

}
