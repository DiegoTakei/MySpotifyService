package br.edu.ufcg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingArtistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MissingArtistException (){
        super("Não existe artista para essa música no sistema.");
    }
}


