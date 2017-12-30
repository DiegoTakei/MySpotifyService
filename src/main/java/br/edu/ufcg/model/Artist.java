package br.edu.ufcg.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_artist")
public class Artist {

	
    private Integer artistId;
    private String name;
    private String linkPhoto;
    private Double rating;
    private Boolean isFavorite;

    public Artist() {}

    public Artist(String name, String linkPhoto) {
        this.name = name;
        this.linkPhoto = linkPhoto;
        this.isFavorite = false; 
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_artist")
    public Integer getArtistaId() {
        return artistId;
    }

    public void setArtistaId(Integer artistaId) {
        this.artistId = artistaId;
    }

    @Column(name = "nm_artist")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "link_photo")
    public String getLinkPhoto() {
        return linkPhoto;
    }
    
	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }  
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        return name.equals(artist.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
}