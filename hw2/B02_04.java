package B02_04;
import java.util.Scanner;

public class B02_04 {
    public static void main(String[] args) {
        int n;
        System.out.print("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        int[] array1 = new int[n];
        int[] array2 = new int[n];

        System.out.println("Enter array1: ");
        for (int i=0; i<n; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.println("Enter array2: ");
        for (int j=0; j<n; j++) {
            array2[j] = scanner.nextInt();
        }

        int product = 0;
        for (int k=0; k<n; k++) {
            product +=  array1[k] * array2[k];
        }

        System.out.println("Scalar product: " + product);
    }
}
