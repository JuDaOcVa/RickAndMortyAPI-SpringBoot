package com.example.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.beans.BeanProperty;

@Data
public class Locationdto {


    String name, type, dimension;
    @ToString.Exclude
    String url;
}
