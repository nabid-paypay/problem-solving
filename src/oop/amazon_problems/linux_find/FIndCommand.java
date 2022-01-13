package oop.amazon_problems.linux_find;
import java.util.*;

public class FIndCommand {
    public List<File> findWithFilter(List<Filter> filters, File dir){
        if(!dir.isDir){
           return null;
        }

        List<File> output = new ArrayList<>();
        helper(filters, dir, output);
        return output;
    }

    private void helper(List<Filter> filters, File dir, List<File> output) {

        if(dir.children == null){
            return;
        }

        for (File file : dir.children){
            if(file.isDir) helper(filters, file, output);
            boolean selectFile = false;

            for (Filter filter : filters){
                if (!filter.apply(file)) {
                    selectFile = false;
                    break;
                }
            }
            if (selectFile) output.add(file);
        }
    }
}
