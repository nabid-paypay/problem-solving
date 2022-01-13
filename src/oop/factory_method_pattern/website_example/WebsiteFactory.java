package oop.factory_method_pattern.website_example;

public class WebsiteFactory {

    public static Website getWebsite(WebsiteType type){
        switch (type){
            case SHOP: return new ShoppingWebsite();
            case BLOG: return new BlogWebsite();
            default: return null;
        }
    }
}
