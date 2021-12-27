package com.pvlrs.spotifyrecommender.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.pvlrs.spotifyrecommender.domain.cosmos.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends CosmosRepository<Playlist, String> {

    Optional<Playlist> findById(String id);

    @Query(value =
            "SELECT VALUE COUNT(1) " +
            "FROM e IN p.emotions " +
            "WHERE e = @emotion")
    int countByEmotion(String emotion);

    @Query(value =
            "SELECT VALUE p " +
            "FROM p JOIN e IN p.emotions " +
            "WHERE e IN (@emotion) " +
            "OFFSET @randomOffset LIMIT 1")
    List<Playlist> findRandomPlaylistByEmotion(String emotion, Integer randomOffset);
}
