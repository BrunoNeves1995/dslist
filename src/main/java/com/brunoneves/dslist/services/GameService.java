package com.brunoneves.dslist.services;

import com.brunoneves.dslist.dto.GameDTO;
import com.brunoneves.dslist.dto.GameMinDTO;
import com.brunoneves.dslist.entities.Game;
import com.brunoneves.dslist.projections.GameMinProjection;
import com.brunoneves.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService{

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game game = gameRepository.findById(id).get();
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> games = gameRepository.findAll();

        List<GameMinDTO> gamesDto = new ArrayList<>();
        games.forEach( game -> gamesDto.add(new GameMinDTO(game)));
        return gamesDto;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> gamesProjections = gameRepository.findByList(listId);

        List<GameMinDTO> gamesDto = new ArrayList<>();
        gamesProjections.forEach(gameProjection -> gamesDto.add(new GameMinDTO(gameProjection)));
        return gamesDto;
    }
}