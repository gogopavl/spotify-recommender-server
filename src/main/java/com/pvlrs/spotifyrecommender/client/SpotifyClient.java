package com.pvlrs.spotifyrecommender.client;

import com.pvlrs.spotifyrecommender.configuration.SpotifyAuthorizationConfiguration;
import com.pvlrs.spotifyrecommender.dto.PlaylistsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spotify", url = "${com.spotify.api.host}", configuration = SpotifyAuthorizationConfiguration.class)
public interface SpotifyClient {

    @GetMapping("/v1/users/{userId}/playlists")
    PlaylistsDto getPlaylistsByUser(@PathVariable("userId") String userId, @RequestParam("limit") Integer limit);
}
