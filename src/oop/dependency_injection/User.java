package oop.dependency_injection;

public class User {
    private Database database;

    public User(Database database) {
        this.database = database;
    }

    public void saveData(String data){
        database.persist(data);
    }
}
