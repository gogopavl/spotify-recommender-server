package com.pvlrs.spotifyrecommender.controller.request;

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
public class PlaylistRequest {

    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String spotifyUrl;
    private List<BasicEmotion> emotions;
}
