package com.pvlrs.spotifyrecommender.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto;
import com.pvlrs.spotifyrecommender.dto.Views;
import com.pvlrs.spotifyrecommender.service.PlaylistService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") // todo: remove once deployed
@RequestMapping("/v1/users")
@RestController
public class UserController {

    private final PlaylistService playlistService;

    public UserController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/{userId}/playlists")
    public PlaylistsDto getUserPlaylists(@PathVariable(value = "userId", required = true) String userId,
                                         @RequestParam(value = "limit", required = false) Integer limit) {
        return playlistService.getPlaylistsByUser(userId, limit);
    }
}
