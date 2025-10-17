package hw05;

public class B05_02 {
    public static void main(String[] args) {
        String input = "3abc";

        System.out.println("Рядок: " + input);
        System.out.println("a) " + (checkA(input) ? "так" : "ні"));
        System.out.println("b) " + (checkB(input) ? "так" : "ні"));
        System.out.println("c) " + (checkC(input) ? "так" : "ні"));
    }

    // a) Рядок починається з ненульової цифри, після якої тільки літери,
    // і кількість літер дорівнює цій цифрі
    public static boolean checkA(String s) {
        if (s.isEmpty() || !Character.isDigit(s.charAt(0))) return false;
        int firstDigit = s.charAt(0) - '0';
        if (firstDigit == 0) return false; // ненульова цифра
        String letters = s.substring(1);
        if (!letters.matches("[A-Za-z]+")) return false;
        return letters.length() == firstDigit;
    }

    // b) Рядок містить лише одну цифру, її значення дорівнює довжині рядка
    public static boolean checkB(String s) {
        int digitCount = 0;
        int digitValue = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
                digitValue = c - '0';
            }
        }
        return digitCount == 1 && digitValue == s.length();
    }

    // c) Сума всіх цифр у рядку дорівнює довжині рядка
    public static boolean checkC(String s) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sum += c - '0';
            }
        }
        return sum == s.length();
    }
}