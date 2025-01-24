package com.brunoneves.dslist.services;

import com.brunoneves.dslist.dto.GameListDTO;
import com.brunoneves.dslist.dto.GameMinDTO;
import com.brunoneves.dslist.entities.GameList;
import com.brunoneves.dslist.projections.GameMinProjection;
import com.brunoneves.dslist.repositories.GameListRepository;
import com.brunoneves.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameListDTO findById(Long id){
        GameList gameList = gameListRepository.findById(id).get();
        return new GameListDTO(gameList);
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> gameLists = gameListRepository.findAll();

        List<GameListDTO> gamesListDto = new ArrayList<>();
        gameLists.forEach(gamelist -> gamesListDto.add(new GameListDTO(gamelist)));
        return gamesListDto;
    }

    @Transactional
    public void moveList(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.findByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);

        list.add(destinationIndex, obj);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max ; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }
}