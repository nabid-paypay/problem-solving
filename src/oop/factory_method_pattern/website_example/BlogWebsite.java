package oop.factory_method_pattern.website_example;

public class BlogWebsite extends Website{
    @Override
    protected void createWebsite() {
        pages.add(new ContactPage());
        pages.add(new AboutPage());
    }
}
