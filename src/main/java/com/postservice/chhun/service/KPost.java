package com.postservice.chhun.service;

import com.postservice.chhun.dto.DeliveryDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KPost implements SearchInterface {
    public List<DeliveryDTO> searchPost(String postNumber) {
        String url = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("sid1", postNumber);

        Connection conn = Jsoup.connect(uriComponentsBuilder.toUriString());

        try {
            ArrayList<DeliveryDTO> deliveryDTOInfos = new ArrayList<>();
            Document document = conn.get();
            Elements processTableEl = document.select("#processTable").select("tbody");

            for (Element elementTableRow : processTableEl.select("tr")) {
                DeliveryDTO deliveryDTOInfo = new DeliveryDTO();
                for (Element elementTableColumn : elementTableRow.select("td")) {
                    if (deliveryDTOInfo.setInfo(elementTableColumn.text()))
                        continue;

                    if (elementTableColumn.select("span").hasClass("evtnm")) {
                        deliveryDTOInfo.setStatus(elementTableColumn.select("span.evtnm").text());
                    } else if (elementTableColumn.select("a span").size() > 0) {
                        deliveryDTOInfo.setWhere(elementTableColumn.select("a span").text());
                    } else {
                        deliveryDTOInfo.setWhere(elementTableColumn.select("a").text());
                    }
                }
                deliveryDTOInfos.add(deliveryDTOInfo);
            }
            return deliveryDTOInfos;
        } catch (IOException ignored) {
        }

        return null;
    }
}
