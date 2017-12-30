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

import br.edu.ufcg.model.Artist;
import br.edu.ufcg.model.Music;
import br.edu.ufcg.service.ArtistService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/artist")
public class ArtistRest {
	
	@Autowired
	private ArtistService service;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public Artist addArtist(@RequestBody Artist artist, @RequestHeader String username){
		return service.addArtist(artist,username);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Set<Artist> removeArtist(@RequestBody Set<Artist> artists, @RequestHeader String username){
		return service.removeArtist(artists,username);
	}
	
	@GetMapping(value = "/getAll")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Artist> getAll(@RequestHeader String username){
		return service.getAll(username);
	}
	
	@RequestMapping(value = "/favorite", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Artist favorite(@RequestBody Artist artist, @RequestHeader String username){
		return service.favorite(artist,username);
	}
	
	@RequestMapping(value = "/addFavorite", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Artist addFavorite(@RequestBody Artist artist, @RequestHeader String username){
		
		return service.addFavorite(artist,username);
		
	}
	
	@RequestMapping(value = "/updateArtist", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Artist saveArtist(@RequestBody Artist artist, @RequestHeader String name, @RequestHeader String username){
		return service.saveArtist(artist,name, username);
	}
	
	@GetMapping(value = "/getAllFavorite")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Artist> getAllFavorite(@RequestHeader String username){
		return service.getAllFavorite(username);
	}
	
	@RequestMapping(value = "/getAllArtistMusics", method = RequestMethod.POST, headers = {"content-type=application/json"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Music> getAllArtistMusics(@RequestBody Artist artist ,@RequestHeader String username){
		return service.getAllArtistMusics(artist,username);
	}

}
