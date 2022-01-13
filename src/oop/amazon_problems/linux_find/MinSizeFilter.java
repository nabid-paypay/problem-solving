package oop.amazon_problems.linux_find;

public class MinSizeFilter extends Filter{
    int minSize;

    public MinSizeFilter(int minSize) {
        this.minSize = minSize;
    }

    @Override
    boolean apply(File file) {
        return file.size >= minSize;
    }
}
