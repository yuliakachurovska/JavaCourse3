package hw5;

public class B05_01 {
    private static final String SUCCESS_TEST = "Рядок (автплот) з символів в якому (іовпилоіа) дужечки розставлені правильно!";
    private static final String FAILURE_TEST = "Некоректно (івоатов(написаний рядок, який має дати помилку)).";

    public static void main(String[] args) {
        String input = SUCCESS_TEST;
        //String input = FAILURE_TEST;
        System.out.println("Вхідний рядок: " + input);
        if (areParenthesesCorrect(input)) {
            String result = input.replaceAll("\\([^()]*\\)", "");
            result = result.replaceAll("\\s+", " ").trim();
            System.out.println("Результат: " + result);
        } else {
            System.out.println("Дужки розставлено неправильно");
        }
    }

    public static boolean areParenthesesCorrect(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                count++;
                if (count > 1) return false;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}