package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;
}
