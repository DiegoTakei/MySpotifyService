package br.edu.ufcg.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.exceptions.DuplicateMusicException;
import br.edu.ufcg.exceptions.MissingArtistException;
import br.edu.ufcg.model.Account;
import br.edu.ufcg.model.Album;
import br.edu.ufcg.model.Artist;
import br.edu.ufcg.model.LastMusic;
import br.edu.ufcg.model.Music;
import br.edu.ufcg.repository.AccountRepository;
import br.edu.ufcg.repository.ArtistRepository;

@Service
public class AlbumService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ArtistRepository artistRepository;
	
	public Music addMusic(Music music, String username) {
		
        Account account = accountRepository.findAccountByUser_Email(username);
        Artist artist = artistRepository.findArtistByName(music.getAlbum().getArtist().getName());
      
        if((account != null)&&(artist!=null)){
            Album album = getAlbumFromAccount(music, account);
            if (album == null) {
                account.getAlbumSet().add(music.getAlbum());
                album = music.getAlbum();
                album.setArtist(artist);
            }
            if(album.getMusicSet().contains(music)){
                throw new DuplicateMusicException();
            }
            music.getAlbum().setArtist(artist);
            album.getMusicSet().add(music);
            account.getAlbumSet().add(album);
            accountRepository.save(account);
        } else {
            throw new MissingArtistException();
        }
        return music;
	}

	public void removeMusic(Music music, String username) {
		Account account = accountRepository.findAccountByUser_Email(username);
		Set<Album> albuns = account.getAlbumSet();
		for (Album album : albuns) {
			for (Music aux : album.getMusicSet()) {
				if(aux.equals(music)){
					album.getMusicSet().remove(aux);
					accountRepository.save(account);
					return;
				}
					
			}
		}
		
	}
	
	public Set<Album> getAll(String username) {	
		Account account = accountRepository.findAccountByUser_Email(username);
		return account.getAlbumSet();
		
	}
	
	public Set<Music> getAlbum(Album nameAlbum, String username){
		Account account = accountRepository.findAccountByUser_Email(username);
		Set <Album> albuns = account.getAlbumSet();
		for (Album album : albuns) {
			if(album.getName().equals(nameAlbum.getName())){
				Set<Music> m = album.getMusicSet();
				for (Music music : m) {
					System.out.println(music.getName());
				}
				return m;
			}
		}
		return null;
	}
	
	public Set<Music> getAllMusics(String username) {	
		Account account = accountRepository.findAccountByUser_Email(username);
		
		Set<Album> albuns = account.getAlbumSet();
		Set<Music> musics = new HashSet<Music>() ;
		for (Album album : albuns) 
			for (Music music : album.getMusicSet()) {			
				musics.add(music);
			}
		return musics;	
	}
	
	public Set<LastMusic> getLastMusics(String username){
		Account account = accountRepository.findAccountByUser_Email(username);
		return account.getLastMusics();
	}
	
    private Album getAlbumFromAccount(Music music, Account account) {
        Album album = null;
        Iterator<Album> iterator = account.getAlbumSet().iterator();

        while (iterator.hasNext()) {
            Album aux = iterator.next();
            if(aux.equals(music.getAlbum())) {
                album = aux;
                break;
            }
        }
        return album;
    }

}
