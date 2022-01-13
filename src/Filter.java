import java.util.*;

public class Filter {
    List<String> list = new ArrayList<>();
    public Filter applyUserSavedFilter(){
        list.add("1");
        return this;
    }
    public Filter applyTS(){
        return this;
    }

}
