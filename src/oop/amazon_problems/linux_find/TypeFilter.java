package oop.amazon_problems.linux_find;

public class TypeFilter extends Filter{
    int type;

    public TypeFilter(int type) {
        this.type = type;
    }

    @Override
    boolean apply(File file) {
        return file.type == type;
    }
}
