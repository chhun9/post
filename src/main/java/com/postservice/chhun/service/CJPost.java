package com.postservice.chhun.service;

import com.postservice.chhun.dto.DeliveryDTO;
import com.postservice.chhun.entity.DeliveryInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.*;
import java.util.stream.Collectors;

public class CJPost implements SearchInterface {
    @Override
    public List<DeliveryDTO> searchPost(String postNumber) {
        String firstUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking";
        String secondUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking-detail";


        Map<String, String> firstConnection = firstGetConnect(firstUrl);
        firstConnection.put("paramInvcNo", postNumber);

        Document document = secondePostConnect(secondUrl, firstConnection);

        List<DeliveryDTO> deliveryDTOs = new ArrayList<>();
        return deliveryDTOs;
    }

    private Map<String, String> firstGetConnect(String url) {
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

            return Map.of("_csrf", csrf, "Cookie", cookie);
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
