package hw6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B06_02 {
    public static void main(String[] args) {
        String text = """
            Call me at +380501234567.
            Office: 380-44-123-45-67
            Home: +38(067)-123-4567
            Old number: 1234567
            Another format: 050 123 45 67
            """;

        String rgx = "\\+?\\d{1,3}?[- .]?\\(?\\d{2,3}\\)?[- .]?\\d{2,3}[- .]?\\d{2,3}[- .]?\\d{0,4}";

        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(text);

        System.out.println("Phone numbers:");
        boolean found = false;

        while (m.find()) {
            System.out.println(m.group());
            found = true;
        }

        if (!found) {
            System.out.println("Not found.");
        }
    }
}
