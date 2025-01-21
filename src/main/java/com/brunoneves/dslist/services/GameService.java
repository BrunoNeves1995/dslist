package com.brunoneves.dslist.services;

import com.brunoneves.dslist.dto.GameMinDTO;
import com.brunoneves.dslist.entities.Game;
import com.brunoneves.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService{

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll(){
        List<Game> games = gameRepository.findAll();

        List<GameMinDTO> gamesDto = new ArrayList<>();

        games.forEach( game -> gamesDto.add(new GameMinDTO(game)));

        return gamesDto;
    }
}