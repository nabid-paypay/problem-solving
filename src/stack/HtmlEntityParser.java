package stack;

public class HtmlEntityParser {
    public String entityParser(String text) {
        /*CharSequence charSequence = "&quot;";
         text = "&amp; is an HTML entity but &ambassador; is not."
                .replace(charSequence, "&");
        System.out.println(text);*/
        return "";
    }

    public static void main(String[] args) {
        CharSequence charSequence = "&amp;";
        String text = "&amp; is an HTML entity but &ambassador; is not."
                .replace(charSequence, "&");
        System.out.println(text);
    }


}
