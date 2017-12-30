package br.edu.ufcg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String name;
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Album album;
    private Integer year;
    private String duration;

    public Music(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Music music = (Music) o;

        if (name != null ? !name.equals(music.name) : music.name != null) return false;
        return album != null ? album.equals(music.album) : music.album == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}