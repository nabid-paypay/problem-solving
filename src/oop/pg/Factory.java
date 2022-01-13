package oop.pg;

public class Factory {
    public static Repo getMeRepo(String type){
        if(type == "oracle") return new DBManager();
        else return new Postgres();
    }
}
