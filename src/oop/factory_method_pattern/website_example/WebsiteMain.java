package oop.factory_method_pattern.website_example;

public class WebsiteMain {

    /*
    * the factory method pattern is a creational pattern that uses factory methods to deal with the problem of creating objects
    * without having to specify the exact class of the object that will be created.
    * This is done by creating objects by calling a factory method
    *
    * The Factory Method design pattern is used instead of the regular class constructor for keeping within the
    * SOLID principles of programming,
    * decoupling the construction of objects from the objects themselves.
    *
    * Define an interface for creating an object, but let subclasses decide which class to instantiate.
    * The Factory method lets a class defer instantiation it uses to subclasses
    * */

    /*
    * Doesn't expose instantiation logic
    * Defer to subclass
    * Common Interface
    * Specified by architecture , implemented by user
    * */

    //https://stackoverflow.com/questions/69849/factory-pattern-when-to-use-factory-methods read the answers
    public static void main(String[] args) {
        Website blog = WebsiteFactory.getWebsite(WebsiteType.BLOG);
        Website shop = WebsiteFactory.getWebsite(WebsiteType.SHOP);

        System.out.println(blog.getPages());
        System.out.println(shop.getPages());
    }
}
