import java.util.Scanner;

public class B01_04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int product = 1;
		for (int i = 0; i < 3; i++) {
			int number = scanner.nextInt();
			product *= number;
		}

		scanner.close();

		double res = Math.cbrt(product);
		System.out.printf("%.4f\n", res);
	}
}
