package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void givenTrelloData() {
        //Given & When
        URI url = UriComponentsBuilder
                .fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();

        URI url2 = UriComponentsBuilder
                .fromHttpUrl("https://api.trello.com/1" + "/members/" + "piotrk1290" + "/boards")
                .queryParam("key", "36d6c4b1afeace4be934fe959b95f35a")
                .queryParam("token", "ATTA2aac5cc89ea5be93021337f65fd47cc4860dff9457e9c8d297f59631e8e81e194FDA78F9")
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();

        //Then
        assertEquals(url, url2);
    }

}