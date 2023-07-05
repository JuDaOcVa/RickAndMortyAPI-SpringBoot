package com.example.rickandmorty.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseEpisodedto {

    int id;
    String name;
    ArrayList<String> characters;
}
