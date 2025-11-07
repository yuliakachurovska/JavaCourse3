package hw7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Toy implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String name;
    public double price;
    public int minAge;
    public int maxAge;

    public Toy(String name, double price, int minAge, int maxAge) {
        this.name = name;
        this.price = price;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        return name + " — " + price + " грн (вік " + minAge + "-" + maxAge + ")";
    }

    public static void write(String filename, List<Toy> toys) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (var toy : toys)
                out.writeObject(toy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Toy> read(String filename) {
        List<Toy> list = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    list.add((Toy) in.readObject());
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<Toy> getToysForAge(String filename, int age) {
        List<Toy> allToys = read(filename);
        List<Toy> suitable = new ArrayList<>();
        for (Toy t : allToys) {
            if (age >= t.minAge && age <= t.maxAge)
                suitable.add(t);
        }
        return suitable;
    }
}
