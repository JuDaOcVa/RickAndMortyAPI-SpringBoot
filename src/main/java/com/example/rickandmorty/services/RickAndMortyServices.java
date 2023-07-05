package com.example.rickandmorty.services;

import org.springframework.stereotype.Service;

@Service
public interface RickAndMortyServices {
    String consultarEpisodio(int episodioId);

}
