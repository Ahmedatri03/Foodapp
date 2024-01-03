package app.foodapp.controller;

import com.google.gson.Gson;

public class User {

    private String name;
    private long id;

    public User(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return id + " " + name;
    }

    public static void main(String[] args) {
        String userJson = "[{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]";

        Gson gson = new Gson();

        User[] userArray = gson.fromJson(userJson, User[].class);

        for (User user : userArray) {
            System.out.println(user);
        }
    }
}
