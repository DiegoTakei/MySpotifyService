package br.edu.ufcg.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.model.Account;
import br.edu.ufcg.model.Album;
import br.edu.ufcg.model.Artist;
import br.edu.ufcg.model.LastMusic;
import br.edu.ufcg.model.Music;
import br.edu.ufcg.repository.AccountRepository;
import br.edu.ufcg.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private AlbumService albumService;

	public Artist addArtist(Artist artist, String username) {
		
        Account account = accountRepository.findAccountByUser_Email(username);
        if(account != null && !account.getArtistSet().contains(artist)){
            account.getArtistSet().add(artist);
            accountRepository.save(account);
        } else {
            return null;
        }
        return artist;
		
	}

	public Set<Artist> removeArtist(Set<Artist> artists, String username) {
		
		Account account = accountRepository.findAccountByUser_Email(username);
		for (Artist artist : artists) {
			for (Artist artist2 : account.getArtistSet()) {
				if(artist2.equals(artist))
					account.getArtistSet().remove(artist2);
			}
		}
		accountRepository.save(account);
		return account.getArtistSet();
		
	}
	
	public Artist saveArtist(Artist artist, String name, String username){

		Account account = accountRepository.findAccountByUser_Email(username);
		Set<Artist> artists = account.getArtistSet();
		for (Artist artist2 : artists) {
			if(artist2.getName().equals(artist.getName())){
				artist2.setIsFavorite(artist.getIsFavorite());
				artist2.setRating(artist.getRating());
				account.getArtistSet().add(artist2);
				accountRepository.save(account);
				break;
			}
		}
		
		Artist artist2 = artistRepository.findArtistByName(artist.getName());
		
		Set<Music> musics = albumService.getAllMusics(username);
		Music lastMusic = null;
		for (Music music : musics) {
			if(music.getName().equals(name)){
				lastMusic = music;
				break;
			}
		}

		if(lastMusic != null){
			Artist artistAux = artistRepository.findArtistByName(artist.getName());
			Set<LastMusic> lastMusics = account.getLastMusics();
			if(lastMusics.isEmpty()){
				account.getLastMusics().add(new LastMusic(artist2, lastMusic));
				accountRepository.save(account);
			}else{
				for (LastMusic lastMusic2 : lastMusics) {
					if(lastMusic2.getArtist().getName().equals(artist2.getName())){
						lastMusic2.setMusic(lastMusic);
						lastMusic2.setArtist(artistAux);
						account.getLastMusics().add(lastMusic2);
						accountRepository.save(account);
						return artistAux;
					}
				}
				account.getLastMusics().add(new LastMusic(artistAux, lastMusic));
				accountRepository.save(account);
			}
			
		}
		return null;
	}
	
	public Artist favorite(Artist artist, String username){
		Account account = accountRepository.findAccountByUser_Email(username);
		
		Set<Artist> artists = account.getArtistSet();
		for (Artist artist2 : artists) {
			if(artist2.getName().equals(artist.getName())){
				artist2.setIsFavorite(artist.getIsFavorite());
				account.getArtistSet().add(artist2);
				accountRepository.save(account);
				return artist2;
			}
		}
		return null;
	}

	
	public Iterable<Artist> getAll(String username) {	
		Account account = accountRepository.findAccountByUser_Email(username);
		return account.getArtistSet();
		
	}

	public Artist addFavorite(Artist artist, String username) {
		
		Account account = accountRepository.findAccountByUser_Email(username);
		Set<Artist> artists = account.getArtistSet();
		for (Artist artist2 : artists) {
			if(artist2.getName().equals(artist.getName())){
				artist2.setIsFavorite(true);
				accountRepository.save(account);
				return artist2;
			}
		}
		return null;
	}
	
	public Iterable<Artist> getAllFavorite(String username){
		
		Account account = accountRepository.findAccountByUser_Email(username);
		Set <Artist> artists = account.getArtistSet();
		Set <Artist> favorites = new HashSet<Artist>();
		for (Artist artist : artists) {
			if(artist.getIsFavorite()!= null)
				if(artist.getIsFavorite() == true)
					favorites.add(artist);
		}
		
		return favorites;
		
	}

	public Iterable<Music> getAllArtistMusics(Artist artist, String username) {
		Account account = accountRepository.findAccountByUser_Email(username);
		Set<Album> albuns = account.getAlbumSet();
		Set<Music> musics = new HashSet<Music>() ;
		for (Album album : albuns) 
			if(album.getArtist().equals(artist)){
				for (Music music : album.getMusicSet()) {			
					musics.add(music);
				}
			
		}
		
		return musics;
	}
	
}