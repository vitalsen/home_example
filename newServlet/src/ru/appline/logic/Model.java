package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instanse = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instanse;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Vitaly", "Kazarin", 80000));
        model.put(2, new User("Nikita", "Petrov", 150000));
        model.put(3, new User("Serena", "Putin", 100000));
        model.put(4, new User("Sergei", "Kotov", 30000));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }
    public Map<Integer, User>getFromList(){
        return model;
    }

    public void delete(int id){
        model.remove(id);
    }

    public void put(User user, int id) {
    }
}