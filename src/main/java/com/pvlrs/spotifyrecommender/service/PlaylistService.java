package com.pvlrs.spotifyrecommender.service;

import com.pvlrs.spotifyrecommender.client.SpotifyClient;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PlaylistService {

    private final SpotifyClient spotifyClient;

    public PlaylistService(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    public PlaylistsDto getPlaylistsByUser(String userId, Integer limit) {
        PlaylistsDto playlistsByUser = spotifyClient.getPlaylistsByUser(userId, limit);
        playlistsByUser.getItems().forEach(playlist -> {
            // Return only largest image (based on width)
            playlist.setImageUrl(playlist.getImages().stream()
                    .max(Comparator.comparing(PlaylistsDto.Image::getWidth)).get().getUrl());

            // Flatten url structure
            playlist.setSpotifyUrl(playlist.getExternalUrl().getSpotify());
        });
        return playlistsByUser;
    }
}
