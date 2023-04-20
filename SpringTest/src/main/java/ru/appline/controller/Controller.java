package ru.appline.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController

public class Controller {

    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newID = new AtomicInteger(1);

    @PostMapping(value = "/createPet",consumes = "/application/json",produces ="/application/json" )
    public void createPet(@RequestBody Pet pet){
        petModel.add(pet,newID.getAndIncrement());
    }

    @GetMapping(value = "/getAll",produces ="application/json" )
    public Map<Integer,Pet>getAll(){
        return petModel.getAll();
    }
/*
    {
      "id":2
      }

 */
    @GetMapping(value = "/getPet",consumes = "/application/json",produces ="/application/json")
    public Pet getPet(@RequestBody Map<String,Integer>id){
        return petModel.getFromList(id.get(id));

    }

}


