package B02_17d;

import java.util.Scanner;

public class B02_17d {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double x = scan.nextDouble();
        if (Math.abs(x) >= 1) {
            throw new IllegalArgumentException("x не відповідає умові!");
        }
        double summ = 0.0;
        double epsilon = 0.000001;
        int k = 0;
        double term;
        do {
            term = Math.pow(x, 2 * k);
            if (k % 2 != 0) {
                term = -term;
            }
            summ += term;
            k++;
        } while (Math.abs(term) > epsilon);
        System.out.println("Результат: " + summ);
    }
}