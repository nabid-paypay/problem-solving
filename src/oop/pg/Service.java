package oop.pg;

public class Service {
    public String test(Repo repo){
        System.out.println(repo.getName());
        return repo.getName();
    }
    public static void main(String[] args) {

        Service service = new Service();
        service.test(Factory.getMeRepo("oracle"));
        service.test(Factory.getMeRepo("postgres"));
    }


}
