package br.edu.ufcg.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ufcg.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Integer>{
	
	Album findAlbumByName(String name);
}
