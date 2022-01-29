package com.pvlrs.spotifyrecommender.service;

import com.pvlrs.spotifyrecommender.client.SpotifyClient;
import com.pvlrs.spotifyrecommender.constants.CacheSpaces;
import com.pvlrs.spotifyrecommender.controller.request.PlaylistRequest;
import com.pvlrs.spotifyrecommender.domain.cosmos.Playlist;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto.PlaylistDto;
import com.pvlrs.spotifyrecommender.enums.BasicEmotion;
import com.pvlrs.spotifyrecommender.repository.PlaylistRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.pvlrs.spotifyrecommender.dto.PlaylistsDto.Image;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SpotifyClient spotifyClient;

    public PlaylistService(PlaylistRepository playlistRepository, SpotifyClient spotifyClient) {
        this.playlistRepository = playlistRepository;
        this.spotifyClient = spotifyClient;
    }

    public void savePlaylist(PlaylistRequest request) {
        playlistRepository.save(Playlist.fromPlaylistRequest(request));
    }

    @Cacheable(CacheSpaces.PLAYLISTS)
    public PlaylistsDto getAllPlaylists() {
        List<PlaylistDto> playlistDtos = new ArrayList<>();
        playlistRepository.findAll()
                .forEach(playlist -> playlistDtos.add(PlaylistDto.fromPlaylist(playlist)));
        return new PlaylistsDto(playlistDtos);
    }

    public PlaylistDto getRandomPlaylistByEmotion(BasicEmotion emotion) {
        int numberOfPlaylistsWithEmotion = playlistRepository.countByEmotion(emotion.name());
        int randomOffset = new Random().nextInt(numberOfPlaylistsWithEmotion);
        return playlistRepository.findRandomPlaylistByEmotion(emotion.name(), randomOffset).stream()
                .findFirst()
                .map(PlaylistDto::fromPlaylist)
                .orElse(new PlaylistDto());
    }

    public PlaylistsDto getPlaylistsByUser(String userId, Integer limit) {
        PlaylistsDto playlistsByUser = spotifyClient.getPlaylistsByUser(userId, limit);
        playlistsByUser.getItems().forEach(playlist -> {
            // Return only largest image (based on width)
            playlist.setImageUrl(playlist.getImages().stream()
                    .max(Comparator.comparing(Image::getWidth))
                    .orElse(new Image()).getUrl());

            // Flatten url structure
            playlist.setSpotifyUrl(playlist.getExternalUrl().getSpotify());
        });
        return playlistsByUser;
    }
}
