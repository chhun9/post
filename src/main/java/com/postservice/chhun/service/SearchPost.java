package com.postservice.chhun.service;

import com.postservice.chhun.dto.DeliveryInfo;
import com.postservice.chhun.dto.ResponseVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
public class SearchPost {

    public ResponseVO searchPost(String postNumber) {
        ResponseVO responseVO = new ResponseVO("fail");
        searchPost_kpost(postNumber);
        return responseVO;
    }

    private List<DeliveryInfo> searchPost_kpost(String postNumber) {
        String url = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("sid1", postNumber);

        Connection conn = Jsoup.connect(uriComponentsBuilder.toUriString());

        try {
            ArrayList<DeliveryInfo> deliveryInfos = new ArrayList<>();
            Document document = conn.get();
            Elements processTableEl = document.select("#processTable").select("tbody");

            for (Element elementTableRow : processTableEl.select("tr")) {
                DeliveryInfo deliveryInfo = new DeliveryInfo();
                for (Element elementTableColumn : elementTableRow.select("td")) {
                    if (deliveryInfo.setInfo(elementTableColumn.text()))
                        continue;

                    if (elementTableColumn.select("span").hasClass("evtnm")) {
                        deliveryInfo.setStatus(elementTableColumn.select("span.evtnm").text());
                    }
                    else if(elementTableColumn.select("a span").size()>0){
                        deliveryInfo.setWhere(elementTableColumn.select("a span").text());
                    }
                    else{
                        deliveryInfo.setWhere(elementTableColumn.select("a").text());
                    }
                }
                deliveryInfos.add(deliveryInfo);
            }

            for(DeliveryInfo deliveryInfo : deliveryInfos){
                System.out.println("deliveryInfo = " + deliveryInfo.toString());
            }

            return deliveryInfos;
        } catch (IOException ignored) {
        }

        return null;
    }
}
