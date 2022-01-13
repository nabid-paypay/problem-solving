package oop.dependency_injection;

public class PostgresDB implements Database{
    @Override
    public void persist(String data) {
        System.out.println("Postgres: " + data);
    }
}
