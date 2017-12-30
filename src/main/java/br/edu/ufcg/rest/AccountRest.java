package br.edu.ufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.model.Account;
import br.edu.ufcg.model.User;
import br.edu.ufcg.service.AccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountRest {

    @Autowired
    private AccountService service;


    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@RequestBody User user){
        service.registerAccount(user);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = {"content-type=application/json"})
    @ResponseStatus(HttpStatus.OK)
    public Account login(@RequestBody User user){
    	return service.login(user.getEmail(),user.getPassword());
    }   
}