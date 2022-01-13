package oop.dependency_injection;

public class DemoDI {
    public static void main(String[] args) {
        User user = new User(new OracleDB());
        user.saveData("data for oracle");
        User user1 = new User(new PostgresDB());
        user1.saveData("data for postgres");
    }
}
