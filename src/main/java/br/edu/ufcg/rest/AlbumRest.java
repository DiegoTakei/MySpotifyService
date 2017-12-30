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

import br.edu.ufcg.model.Album;
import br.edu.ufcg.model.LastMusic;
import br.edu.ufcg.model.Music;
import br.edu.ufcg.service.AlbumService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/album")
public class AlbumRest {
	
	@Autowired
    private AlbumService albumService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public Music addMusic(@RequestBody Music music, @RequestHeader String username){
		return albumService.addMusic(music,username);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void removeMusic(@RequestBody Music music, @RequestHeader String username){
		albumService.removeMusic(music,username);
	}
	
	@RequestMapping(value = "/getAlbum", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Set<Music> getAlbum(@RequestBody Album album, @RequestHeader String username){
		return albumService.getAlbum(album, username);
	}
	
	@GetMapping(value = "/getLastMusics")
	@ResponseStatus(HttpStatus.OK)
	public Set<LastMusic> getLastMusics(@RequestHeader String username){
		return albumService.getLastMusics(username);
	}
	@GetMapping(value = "/getAll")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Album> getAll(@RequestHeader String username){
		return albumService.getAll(username);
	}
	
	@GetMapping(value = "/getAllMusics")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Music> getAllMusics(@RequestHeader String username){
		return albumService.getAllMusics(username);
	}
}
