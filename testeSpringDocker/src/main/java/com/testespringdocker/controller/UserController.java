package com.testespringdocker.controller;

import com.testespringdocker.model.User;
import com.testespringdocker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", exposedHeaders = "X - Total-Count")
public class UserController {

    UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    //Lista todos
    @GetMapping
    public List<User> listAll(){
        return service.ListAll();
    }

    //Busca pelo id
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return service
                .findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Insere o dado
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User u){
        return ResponseEntity.status(201).body(service.insert(u));
    }

    //Atualiza do dado
    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@RequestBody User u, @PathVariable Long id){
        return service
                .findById(id)
                .map( cliente -> {
                    service.update(u);
                    return ResponseEntity.ok().body(u);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service
                .findById(id)
                .map( user -> {
                    service.delete(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
