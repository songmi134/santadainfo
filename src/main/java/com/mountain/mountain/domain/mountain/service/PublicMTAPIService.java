package com.mountain.mountain.domain.mountain.service;


import com.mountain.mountain.controller.mountain.dto.PBMTListDTO;
import com.mountain.mountain.controller.mountain.dto.ResponseMountainDTO;
import com.mountain.mountain.domain.mountain.dao.MountainRepository;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

import static com.google.api.AnnotationsProto.http;

@Service
public class PublicMTAPIService {

    @Autowired
    PBMTListDTO pbmtListDTO;

    @Autowired
    MountainRepository mountainRepository;


    @Transactional
    public ResponseEntity<PBMTListDTO> callAPI() throws UnsupportedEncodingException {

        //final RestTemplate restTemplate;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        String url = "http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice";
        String serviceKey = "coE32wM1cg5RFDi%2BhTZyNC0UFhHx2XRwzXmgVDeVUWjv04pGWNHAZVVdNyIFno7CMFFhgX5SD89fZ%2BVn5uB%2Btw%3D%3D";



        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ServiceKey", serviceKey)
                .build(false); // URIComponentBuilder를 사용할때 queryParam을 자동으로 encoding하는 것 막음


        ResponseEntity<PBMTListDTO> exchange = restTemplate.exchange(uri.toString(),HttpMethod.GET, entity, PBMTListDTO.class);

        return exchange;
    }


}


