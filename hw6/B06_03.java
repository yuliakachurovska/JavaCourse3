package hw6;

import java.util.regex.Pattern;

public class B06_03 {
    public static void main(String[] args) {
        String[] examples = {
                "+2 - 57*33 + 25/ -4",
                "10 + 20 - 3*5 /2",
                "-5 + 6",
                "10 ++ 20",
                "5 5 + 3",
                "*5 + 3"
        };

        String rgx = "^\\s*([+-]?\\d+(\\s*[+\\-*/]\\s*[+-]?\\d+)*)\\s*$";
        Pattern pattern = Pattern.compile(rgx);

        for (String expr : examples) {
            boolean isValid = pattern.matcher(expr).matches();
            System.out.println(expr + "  " + (isValid ? "Correct" : "Incorrect"));
        }
    }
}
