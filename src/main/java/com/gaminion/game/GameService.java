package com.gaminion.game;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    public Game createGame(GameDTO dto) {
        Game game = new Game();
        game.setName(dto.getName());
        game.setGenre(dto.getGenre());
        game.setPlatform(dto.getPlatform());
        game.setCoverImageUrl(dto.getCoverImageUrl());
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, GameDTO dto) {
        Game game = getGameById(id);
        game.setName(dto.getName());
        game.setGenre(dto.getGenre());
        game.setPlatform(dto.getPlatform());
        game.setCoverImageUrl(dto.getCoverImageUrl());
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> searchGames(String name) {
        return gameRepository.findByNameContainingIgnoreCase(name);
    }
}