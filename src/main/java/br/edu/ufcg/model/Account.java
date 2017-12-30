package br.edu.ufcg.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_accounts")
public class Account {
	@Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private User user;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Album> albumSet;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Artist> artistSet;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Playlist> playlists;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<LastMusic> lastMusics;

    public Account(){
        this.albumSet = new HashSet<Album>();
        this.artistSet = new HashSet<Artist>();
        this.playlists = new HashSet<Playlist>();
    }

    public Account(User user){
        this();
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;
        return user.equals(account.user);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    public User getUser() {
        return user;
    }

    public Set<Album> getAlbumSet() {
        return albumSet;
    }

    public Set<Artist> getArtistSet() {
        return artistSet;
    }

    public void setAlbumSet(Set<Album> albumSet) {
        this.albumSet = albumSet;
    }

    public void setArtistSet(Set<Artist> artistSet) {
        this.artistSet = artistSet;
    }

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<LastMusic> getLastMusics() {
		return lastMusics;
	}

	public void setLastMusics(Set<LastMusic> lastMusics) {
		this.lastMusics = lastMusics;
	}
	
	
    
    
}