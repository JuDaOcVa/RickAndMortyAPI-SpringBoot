package com.example.rickandmorty.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseFinaldto {
    int episode;
    String episodename;
    ArrayList<Charactersdto> characters;
}
