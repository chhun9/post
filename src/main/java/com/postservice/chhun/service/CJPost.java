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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CJPost implements SearchInterface{
    @Override
    public List<DeliveryDTO> searchPost(String postNumber) {
        String firstUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking";
        String secondUrl = "https://www.cjlogistics.com/ko/tool/parcel/tracking-detail";

        Connection conn = Jsoup.connect(firstUrl);

        try {
            Document document = conn.get();
            String headers = document.connection().response().headers().get("set-cookie");
            String csrf = document.select("input[name='_csrf']").stream()
                    .filter(c -> !c.val().isEmpty())
                    .findFirst().get().val();

            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                    .fromUriString(secondUrl)
                    .queryParam("paramInvcNo", "649933938254")
                    .queryParam("_csrf",csrf);

            conn = Jsoup.connect(uriComponentsBuilder.toUriString()).header("set-cookie",headers);
            Document document1 = conn.get();

            List<DeliveryDTO> deliveryDTOs = new ArrayList<>();
            return deliveryDTOs;
        } catch (IOException ignored) {
        }
        return null;
    }

}
