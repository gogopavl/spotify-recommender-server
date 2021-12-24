package com.pvlrs.spotifyrecommender.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
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
    private List<Playlist> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Playlist {

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

        @JsonProperty("external_urls")
        private SpotifyExternalUrl externalUrl;

        private List<Image> images;
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
