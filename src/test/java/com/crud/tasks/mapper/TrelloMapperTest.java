package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto =new TrelloListDto("1", "Test name list",false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        TrelloBoardDto boardDto = new TrelloBoardDto("1","Test Board",trelloListDtoList);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(boardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals("1",trelloBoards.get(0).getId());
        assertEquals("Test Board",trelloBoards.get(0).getName());
        assertEquals("1",trelloBoards.get(0).getLists().get(0).getId());
        assertEquals("Test name list", trelloBoards.get(0).getLists().get(0).getName());
        assertFalse(trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("2","Another name",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("2","Another Board",trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals("2",trelloBoardDtos.get(0).getId());
        assertEquals("Another Board",trelloBoardDtos.get(0).getName());
        assertEquals("2",trelloBoardDtos.get(0).getLists().get(0).getId());
        assertEquals("Another name", trelloBoardDtos.get(0).getLists().get(0).getName());
        assertTrue(trelloBoardDtos.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("3","Test number 3",false);
        List<TrelloListDto> dtoList = new ArrayList<>();
        dtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(dtoList);

        //Then
        assertEquals("3",trelloLists.get(0).getId());
        assertEquals("Test number 3",trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("4","Test number 4",true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals("4",trelloListDtoList.get(0).getId());
        assertEquals("Test number 4",trelloListDtoList.get(0).getName());
        assertTrue(trelloListDtoList.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card name test","Something","Pos test","1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("Card name test",trelloCard.getName());
        assertEquals("Something",trelloCard.getDescription());
        assertEquals("Pos test", trelloCard.getPos());
        assertEquals("1",trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card name 2","Something new","Pos 2","2");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("Card name 2", trelloCardDto.getName());
        assertEquals("Something new", trelloCardDto.getDescription());
        assertEquals("Pos 2", trelloCardDto.getPos());
        assertEquals("2",trelloCardDto.getListId());
    }
}
