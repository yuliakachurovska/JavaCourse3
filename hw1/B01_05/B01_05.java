import java.util.Scanner;

public class B01_05 {
    public static void main(String[] args) {
        int N;
        int M;
        if(args.length == 2) {
            N = Integer.parseInt(args[0]);
			M = Integer.parseInt(args[1]);
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter N: ");
            N = scanner.nextInt();
            System.out.print("Enter M: ");
            M = scanner.nextInt();
            scanner.close();
        }

        for(int i=0; i<N; i++) {
            int rand = (int) (Math.random() * M);
			System.out.println(rand);
        }
    }
}
