package com.pvlrs.spotifyrecommender.domain.cosmos;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.pvlrs.spotifyrecommender.controller.request.PlaylistRequest;
import com.pvlrs.spotifyrecommender.enums.BasicEmotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "Playlist")
public class Playlist {

    @Id
    @PartitionKey
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String spotifyUrl;
    private List<BasicEmotion> emotions;

    public static Playlist fromPlaylistRequest(PlaylistRequest playlistRequest) {
        return Playlist.builder()
                .id(playlistRequest.getId())
                .name(playlistRequest.getName())
                .description(playlistRequest.getDescription())
                .imageUrl(playlistRequest.getImageUrl())
                .spotifyUrl(playlistRequest.getSpotifyUrl())
                .emotions(playlistRequest.getEmotions()).build();
    }
}
