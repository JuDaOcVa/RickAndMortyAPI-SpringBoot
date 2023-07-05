package com.example.rickandmorty.services.Impl;

import com.example.rickandmorty.dto.Charactersdto;
import com.example.rickandmorty.dto.Locationdto;
import com.example.rickandmorty.dto.ResponseEpisodedto;
import com.example.rickandmorty.dto.ResponseFinaldto;
import com.google.gson.Gson;
import com.example.rickandmorty.services.RickAndMortyServices;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;


import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Repository
public class RickAndMortyImpl implements RickAndMortyServices {
    @Override
    public String consultarEpisodio(int id) {
        ResponseFinaldto responseFinaldto = new ResponseFinaldto();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            ResponseEpisodedto responseEpisodedto = new ResponseEpisodedto();
            HttpGet getRequest = new HttpGet();
            getRequest.setURI(new URI("https://rickandmortyapi.com/api/episode/" + id));
            Gson gson = new Gson();
            responseEpisodedto = gson.fromJson(EntityUtils.toString(httpClient.execute(getRequest).getEntity(), StandardCharsets.UTF_8), ResponseEpisodedto.class);
            responseFinaldto.setEpisode(responseEpisodedto.getId());
            responseFinaldto.setEpisodename(responseEpisodedto.getName());
            responseFinaldto.setCharacters(consultarCharacters(responseEpisodedto));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return responseFinaldto.toString();
    }

    public ArrayList consultarCharacters(ResponseEpisodedto responseEpisodedto) throws URISyntaxException {
        ArrayList characters = new ArrayList();
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet();
            for (int i = 0; i < responseEpisodedto.getCharacters().size(); i++) {
                Charactersdto charactersdto = new Charactersdto();
                System.out.println("responseEpisodedto.getCharacters().get(i)=======>");
                System.out.println(responseEpisodedto.getCharacters().get(i));
                getRequest.setURI(new URI(responseEpisodedto.getCharacters().get(i)));
                Gson gson = new Gson();
                charactersdto = gson.fromJson(EntityUtils.toString(httpClient.execute(getRequest).getEntity(), StandardCharsets.UTF_8), Charactersdto.class);
                if(charactersdto.getLocation().getUrl().equals("")||charactersdto.getLocation().getUrl().isEmpty()){
                    Locationdto locationdto=new Locationdto();
                    locationdto.setName(charactersdto.getLocation().getName());
                    locationdto.setType("");
                    locationdto.setDimension("");
                    locationdto.setUrl("");
                }else{
                    charactersdto.setLocation(consultarLocation(charactersdto));
                }
                characters.add(charactersdto);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return characters;
    }

    public Locationdto consultarLocation(Charactersdto charactersdto) throws URISyntaxException {
        Locationdto locationdto = new Locationdto();
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet();
            getRequest.setURI(new URI(charactersdto.getLocation().getUrl()));
            Gson gson = new Gson();
            locationdto = gson.fromJson(EntityUtils.toString(httpClient.execute(getRequest).getEntity(), StandardCharsets.UTF_8), Locationdto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return locationdto;
    }
}
