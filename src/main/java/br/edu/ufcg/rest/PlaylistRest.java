package br.edu.ufcg.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.model.Music;
import br.edu.ufcg.model.Playlist;
import br.edu.ufcg.service.PlaylistService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/playlists")
public class PlaylistRest {
	
	@Autowired
	private PlaylistService service;
	
	@RequestMapping(value = "/addMusic", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public Playlist addMusic(@RequestBody Music music, @RequestHeader String name, @RequestHeader String username){
		return service.addMusic(music,name,username);
	}
	
	@RequestMapping(value = "/addPlaylist", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public Playlist add(@RequestBody Playlist playlist, @RequestHeader String username){
		return service.add(playlist,username);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Set<Playlist> remove(@RequestBody Set<Playlist> playlist, @RequestHeader String username){
		return service.remove(playlist,username);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Playlist> getAll(@RequestHeader String username){
		return service.getAll(username);
	}
	
}
