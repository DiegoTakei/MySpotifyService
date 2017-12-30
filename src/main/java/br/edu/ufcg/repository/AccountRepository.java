package br.edu.ufcg.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ufcg.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findAccountByUser_Email(String email);
}