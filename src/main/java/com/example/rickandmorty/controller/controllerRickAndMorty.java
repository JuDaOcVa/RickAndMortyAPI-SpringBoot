package com.example.rickandmorty.controller;

import com.example.rickandmorty.services.RickAndMortyServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerRickAndMorty {

    public final RickAndMortyServices rickAndMortyServices;

    public controllerRickAndMorty(RickAndMortyServices rickAndMortyServices) {
        this.rickAndMortyServices = rickAndMortyServices;
    }

    @GetMapping("/consultar")
    public String consultar(@RequestParam(name = "id") int id) {
        return rickAndMortyServices.consultarEpisodio(id);
    }
}
