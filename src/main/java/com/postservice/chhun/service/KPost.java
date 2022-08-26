package com.postservice.chhun.service;

import com.postservice.chhun.config.Dom;
import com.postservice.chhun.config.Url;
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
        String url = Url.KOR_POST_URL.getVal();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam(Dom.KOR_POST_POSTNUMBER.getVal(), postNumber);

        Connection conn = Jsoup.connect(uriComponentsBuilder.toUriString());

        try {
            ArrayList<DeliveryDTO> deliveryDTOInfos = new ArrayList<>();
            Document document = conn.get();
            Elements processTableEl = document.select(Dom.get(" ", Dom.PROCESS_TABLE_ID, Dom.TABLE_BODY));

            for (Element elementTableRow : processTableEl.select(Dom.TABLE_ROW.getVal())) {
                deliveryDTOInfos.add(parseDocument(elementTableRow));
            }
            return deliveryDTOInfos;
        } catch (IOException ignored) {
        }
        return null;
    }

    private DeliveryDTO parseDocument(Element elementTableRow) {
        DeliveryDTO returnDeliveryDTO = new DeliveryDTO();
        for (Element elementTableColumn : elementTableRow.select(Dom.TABLE_COLUMN.getVal())) {
            if (returnDeliveryDTO.setInfo(elementTableColumn.text()))
                continue;

            if (0 < elementTableColumn.select(Dom.EVENT_NAME_CLASS.getVal()).size()) {
                returnDeliveryDTO.setStatus(elementTableColumn.select(Dom.get(Dom.SPAN, Dom.EVENT_NAME_CLASS)).text());
            } else if (0 < elementTableColumn.select(Dom.get(" ", Dom.ANCHOR, Dom.SPAN)).size()) {
                returnDeliveryDTO.setWhere(elementTableColumn.select(Dom.get(" ", Dom.ANCHOR, Dom.SPAN)).text());
            } else {
                returnDeliveryDTO.setWhere(elementTableColumn.select(Dom.ANCHOR.getVal()).text());
            }
        }
        return returnDeliveryDTO;
    }
}
