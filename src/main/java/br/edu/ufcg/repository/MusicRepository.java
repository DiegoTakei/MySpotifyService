package br.edu.ufcg.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ufcg.model.Music;

public interface MusicRepository extends CrudRepository<Music, Integer> {
	Music findMusicByName(String name);
}