package hw8;

public class B08_02_BracketChecker {

    public static boolean isValid(String input) {
        B08_01_Stack<Character> stack = new B08_01_Stack<>();
        for (char ch : input.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char lastOpenBracket = stack.pop();
                if (ch == ')' && lastOpenBracket != '(') {
                    return false;
                }
                if (ch == ']' && lastOpenBracket != '[') {
                    return false;
                }
                if (ch == '}' && lastOpenBracket != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String[] testStrings = {
                "{()[]}",        // Правильно
                "([{}])",        // Правильно
                "{[()]}",        // Правильно
                "()",            // Правильно
                "",              // Правильно (порожній рядок)
                "(()",           // Неправильно (незакрито)
                "())",           // Неправильно (немає пари)
                "[(])",          // Неправильно (порушено порядок)
                "}",             // Неправильно (немає пари)
                "({[}])"         // Неправильно (порушено порядок)
        };

        System.out.println("--- Запускаємо тест перевірки дужок ---");

        for (String str : testStrings) {
            boolean result = isValid(str);
            System.out.printf("Рядок: %-10s -> %s\n", "'" + str + "'", (result ? "ПРАВИЛЬНО" : "НЕПРАВИЛЬНО"));
        }
    }
}