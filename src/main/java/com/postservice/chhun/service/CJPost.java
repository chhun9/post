package com.postservice.chhun.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postservice.chhun.dto.DeliveryDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CJPost implements SearchInterface {
    @Override
    public List<DeliveryDTO> searchPost(String postNumber) {
        List<DeliveryDTO> deliveryDTOInfos = new ArrayList<>();
        String firstUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking";
        String secondUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking-detail";


        Map<String, String> firstConnection = firstGetConnect(firstUrl, postNumber);

        Document document = secondePostConnect(secondUrl, firstConnection);
        try {
            Map<String, Object> documentBody = new ObjectMapper().readValue(document.select("body").text(), Map.class);
            Map<String,Object> resultMap = new ObjectMapper().convertValue(documentBody.get("parcelDetailResultMap"),Map.class);
            List<Map<String,String>> resultList = (List<Map<String,String>>)(resultMap.get("resultList"));
            
            
            for (Map<String, String> detailStatus : resultList) {
                deliveryDTOInfos.add(new DeliveryDTO.Builder()
                        .setInfo(detailStatus.get("dTime").split(" ")[0])
                        .setInfo(detailStatus.get("dTime").split(" ")[1])
                        .setWhere(detailStatus.get("regBranNm"))
                        .setStatus(detailStatus.get("scanNm"))
                        .build()
                );
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return deliveryDTOInfos;
    }

    private Map<String, String> firstGetConnect(String url, String postNumber) {
        Connection getCjDeliverAuth = Jsoup.connect(url);
        try {
            Document document = getCjDeliverAuth.get();

            Map<String, List<String>> allHeaders = document.connection().response().multiHeaders();
            String getCookieFromHeaders = allHeaders.keySet()
                    .stream().filter(key -> key.equalsIgnoreCase("set-cookie"))
                    .findFirst()
                    .get();

            String cookie = allHeaders.get(getCookieFromHeaders)
                    .stream().map(key -> key.substring(0, key.indexOf(';')))
                    .collect(Collectors.joining("; "));

            String csrf = document.select("input[name='_csrf']").stream()
                    .filter(c -> !c.val().isEmpty())
                    .findFirst().get().val();

            return Map.of("_csrf", csrf, "Cookie", cookie, "paramInvcNo", postNumber);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

    private Document secondePostConnect(String url, Map<String, String> params) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("paramInvcNo", params.get("paramInvcNo"))
                .queryParam("_csrf", params.get("_csrf"));

        Connection statusDelivery = Jsoup.connect(uriComponentsBuilder.toUriString())
                .header("Cookie", params.get("Cookie"))
                .ignoreContentType(true);
        try {
            return statusDelivery.post();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

}
