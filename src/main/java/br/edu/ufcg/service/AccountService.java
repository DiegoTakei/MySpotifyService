package br.edu.ufcg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufcg.exceptions.AccountExceptions;
import br.edu.ufcg.model.Account;
import br.edu.ufcg.model.User;
import br.edu.ufcg.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public void registerAccount(User user) {

		if (accountRepository.findAccountByUser_Email(user.getEmail()) == null){

			Account account = new Account(user);
			accountRepository.save(account);
		}
	}
	public Account login(String email, String password) {

		Account account = accountRepository.findAccountByUser_Email(email); 
		
		if(account != null){
			if(account.getUser().getPassword().equals(password))
				return account;
		}
		throw new AccountExceptions();
		
		
	}
}
