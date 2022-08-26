package com.postservice.chhun.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postservice.chhun.config.Dom;
import com.postservice.chhun.config.Url;
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

        Map<String, String> firstConnection = firstGetConnect(Url.KOR_CJ_POST_FIRST_URL.getVal(), postNumber);
        Document document = secondPostConnect(Url.KOR_CJ_POST_SECOND_URL.getVal(), firstConnection);
        try {
            Map<String, Object> documentBody = new ObjectMapper().readValue(document.select(Dom.BODY.getVal()).text(), Map.class);
            Map<String, Object> resultMap = new ObjectMapper().convertValue(documentBody.get(Dom.KOR_CJ_POST_D_RESULT_MAP.getVal()), Map.class);
            List<Map<String, String>> resultList = (List<Map<String, String>>) (resultMap.get(Dom.KOR_CJ_POST_D_RESULT_LIST.getVal()));

            for (Map<String, String> detailStatus : resultList) {
                deliveryDTOInfos.add(new DeliveryDTO.Builder()
                        .setInfo(detailStatus.get(Dom.KOR_CJ_POST_D_TIME.getVal()).split(" ")[0])
                        .setInfo(detailStatus.get(Dom.KOR_CJ_POST_D_TIME.getVal()).split(" ")[1])
                        .setWhere(detailStatus.get(Dom.KOR_CJ_POST_D_REG_NAME.getVal()))
                        .setStatus(detailStatus.get(Dom.KOR_CJ_POST_D_SCAN_NAME.getVal()))
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
                    .stream().filter(key -> key.equalsIgnoreCase(Dom.KOR_CJ_POST_SET_COOKIE.getVal()))
                    .findFirst()
                    .get();

            String cookie = allHeaders.get(getCookieFromHeaders)
                    .stream().map(key -> key.substring(0, key.indexOf(';')))
                    .collect(Collectors.joining("; "));

            String csrf = document.select(Dom.KOR_CJ_POST_CSRF.getVal()).stream()
                    .filter(c -> !c.val().isEmpty())
                    .findFirst().get().val();

            return Map.of(Dom.CSRF.preGet("_"), csrf, Dom.KOR_CJ_POST_COOKIE.getVal(), cookie, Dom.KOR_CJ_POST_POSTNUMBER.getVal(), postNumber);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

    private Document secondPostConnect(String url, Map<String, String> params) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam(Dom.KOR_CJ_POST_POSTNUMBER.getVal(), params.get(Dom.KOR_CJ_POST_POSTNUMBER.getVal()))
                .queryParam(Dom.KOR_CJ_POST_CSRF.getVal(), params.get(Dom.KOR_CJ_POST_CSRF.getVal()));

        Connection statusDelivery = Jsoup.connect(uriComponentsBuilder.toUriString())
                .header(Dom.KOR_CJ_POST_COOKIE.getVal(), params.get(Dom.KOR_CJ_POST_COOKIE.getVal()))
                .ignoreContentType(true);
        try {
            return statusDelivery.post();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return null;
        }
    }

}
