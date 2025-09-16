public class B01_03 {

	public static void main(String[] args) {
		int p = 1;
		for (int i = 0; i < args.length; i++) {
			try {
				p *= Integer.parseInt(args[i]);
			}
			catch (NumberFormatException e) {
				System.out.println("Аргументи не є цілими числами");
				System.exit(0);
			}
		}
		System.out.println(p);
	}

}
