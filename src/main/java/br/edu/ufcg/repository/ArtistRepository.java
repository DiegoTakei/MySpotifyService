package br.edu.ufcg.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.edu.ufcg.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    List<Artist> findArtistsByNameContains(String name);
    Artist findArtistByName(String name);
}