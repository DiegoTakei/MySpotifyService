package br.edu.ufcg.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ufcg.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
