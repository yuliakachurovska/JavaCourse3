package B02_12;

import java.util.Scanner;

public class B02_12 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte n, k;
        n = scan.nextByte();
        k = scan.nextByte();
        scan.close();

        int s1 = 1 << k;
        int s2 = ~s1;
        int new_n = n & s2;

        System.out.println((byte)new_n);
    }
}
