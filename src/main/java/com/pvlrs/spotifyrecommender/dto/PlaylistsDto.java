package com.pvlrs.spotifyrecommender.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.pvlrs.spotifyrecommender.domain.cosmos.Playlist;
import com.pvlrs.spotifyrecommender.enums.BasicEmotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistsDto {

    @JsonView(Views.Public.class)
    private List<PlaylistDto> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaylistDto {

        @JsonView(Views.Public.class)
        private String id;

        @JsonView(Views.Public.class)
        private String name;

        @JsonView(Views.Public.class)
        private String description;

        @JsonView(Views.Public.class)
        private String imageUrl;

        @JsonView(Views.Public.class)
        private String spotifyUrl;

        @JsonView(Views.Public.class)
        private List<BasicEmotion> emotions;

        @JsonProperty("external_urls")
        private SpotifyExternalUrl externalUrl;

        private List<Image> images;

        public static PlaylistDto fromPlaylist(Playlist playlist) {
            return PlaylistDto.builder()
                    .id(playlist.getId())
                    .name(playlist.getName())
                    .description(playlist.getDescription())
                    .imageUrl(playlist.getImageUrl())
                    .spotifyUrl(playlist.getSpotifyUrl())
                    .emotions(playlist.getEmotions()).build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpotifyExternalUrl {

        private String spotify;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {

        private Integer height;
        private Integer width;
        private String url;
    }
}
