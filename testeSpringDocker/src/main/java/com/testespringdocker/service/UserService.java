package com.testespringdocker.service;

import com.testespringdocker.model.User;
import com.testespringdocker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //Lista todos os registros
    public List<User> ListAll(){
        return repository.findAll();
    }

    //Busca pelo id
    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

    //Insere o dado
    public User insert(User u){
        return repository.save(u);
    }

    //Atualiza o dado
    public User update(User u){
        return repository.saveAndFlush(u);
    }

    //deleta o dado pelo id
    public void delete(Long id){
        repository.deleteById(id);
    }


}
