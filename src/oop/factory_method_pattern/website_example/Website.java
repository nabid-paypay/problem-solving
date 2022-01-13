package oop.factory_method_pattern.website_example;
import java.util.*;

public abstract class Website {
    protected List<Page> pages = new ArrayList<>();

    public List<Page> getPages() {
        return pages;
    }

    public Website() {
        this.createWebsite();
    }

    protected abstract void createWebsite();
}
