package oop.dependency_injection;

public class OracleDB implements Database{
    @Override
    public void persist(String data) {
        System.out.println("Oracle: " + data);
    }
}
