package hw7;

import java.util.List;

public class B07_02 {
    public static void main(String[] args) {
        String inp = "src/hw7/input.toys";
        String out = "src/hw7/output.toys";

        Toy[] array = {
                new Toy("М'яч", 150.0, 3, 10),
                new Toy("Лялька", 300.0, 5, 12),
                new Toy("Конструктор", 500.0, 7, 14),
        };
        List<Toy> lst = List.of(array);

        Toy.write(inp, lst);
        System.out.println("Вміст файлу F:");
        System.out.println(Toy.read(inp));

        int childAge = 5;

        var filtered = Toy.getToysForAge(inp, childAge);
        Toy.write(out, filtered);

        System.out.println("\nІграшки для віку " + childAge + ":");
        System.out.println(filtered);
    }
}
