package br.edu.ufcg.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LastMusic {
	
	@Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
	
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Artist artist;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Music music;
    
    public LastMusic() {}

    public LastMusic(Artist artist, Music music) {
        this.artist = artist;
        this.music = music;
    }

	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LastMusic other = (LastMusic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
    
    

}
