package br.edu.ufcg.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.model.Account;
import br.edu.ufcg.model.Artist;
import br.edu.ufcg.model.Music;
import br.edu.ufcg.model.Playlist;
import br.edu.ufcg.repository.AccountRepository;
import br.edu.ufcg.repository.ArtistRepository;

@Service
public class PlaylistService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ArtistRepository artistRepository;
	
	public Playlist addMusic(Music music, String namePlaylist, String username) {
		
		Account account = accountRepository.findAccountByUser_Email(username);
		Set<Playlist> playlists = account.getPlaylists();
		for (Playlist playlist : playlists) {
			if(playlist.getName().equals(namePlaylist)){
		        Artist artist = artistRepository.findArtistByName(music.getAlbum().getArtist().getName());
		        music.getAlbum().setArtist(artist);
				playlist.getMusicSet().add(music);
				accountRepository.save(account);
				return playlist;
			}
		}
		return null;
	}

	public Playlist add(Playlist playlist, String username) {
		
		Account account = accountRepository.findAccountByUser_Email(username);
		for (Music music : playlist.getMusicSet()) {
	        Artist artist = artistRepository.findArtistByName(music.getAlbum().getArtist().getName());
	        music.getAlbum().setArtist(artist);
		}
		account.getPlaylists().add(playlist);
		accountRepository.save(account);
		return playlist;
	}

	public Set <Playlist> remove(Set <Playlist> playlists, String username) {
		Account account = accountRepository.findAccountByUser_Email(username);
		for (Playlist playlist : playlists) {
			for (Playlist playlist2 : account.getPlaylists()) {
				if(playlist2.equals(playlist))
					account.getPlaylists().remove(playlist2);
			}
		}
		accountRepository.save(account);
		return account.getPlaylists();
	}

	public Iterable<Playlist> getAll(String username) {
		Account account = accountRepository.findAccountByUser_Email(username);
		return account.getPlaylists();
	}

}
