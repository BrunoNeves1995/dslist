package com.brunoneves.dslist.controller;

import com.brunoneves.dslist.dto.GameDTO;
import com.brunoneves.dslist.dto.GameListDTO;
import com.brunoneves.dslist.dto.GameMinDTO;
import com.brunoneves.dslist.dto.ReplacementDTO;
import com.brunoneves.dslist.services.GameListService;
import com.brunoneves.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")

public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @GetMapping(value = "/{id}")
    public GameListDTO findById(@PathVariable Long id){
        return gameListService.findById(id);
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        return gameService.findByList(listId);
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body){
        gameListService.moveList(listId, body.getSourceIndex(), body.getDestionationIndex());

    }


}
