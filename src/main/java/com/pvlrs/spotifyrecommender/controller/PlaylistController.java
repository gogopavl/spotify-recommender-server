package com.pvlrs.spotifyrecommender.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pvlrs.spotifyrecommender.controller.request.PlaylistRequest;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto.PlaylistDto;
import com.pvlrs.spotifyrecommender.dto.Views;
import com.pvlrs.spotifyrecommender.enums.BasicEmotion;
import com.pvlrs.spotifyrecommender.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/playlists")
@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlaylist(@RequestBody PlaylistRequest request) {
        playlistService.savePlaylist(request);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public PlaylistsDto getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{emotion}")
    @JsonView(Views.Public.class)
    public PlaylistDto getRandomPlaylistByEmotion(@PathVariable("emotion") String emotion) {
        BasicEmotion emotionEnum = BasicEmotion.fromString(emotion);
        return playlistService.getRandomPlaylistByEmotion(emotionEnum);
    }
}
