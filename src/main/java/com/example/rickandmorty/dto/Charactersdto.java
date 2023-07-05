package com.example.rickandmorty.dto;

import lombok.Data;

@Data
public class Charactersdto {

    String name, species, gender, image;
    Locationdto location;
}
