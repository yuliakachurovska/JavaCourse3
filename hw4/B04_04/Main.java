package B04_04;

public class Main {
    public static void main(String[] args) {
        Disk disk = new Disk();

        disk.add(new Song("Swim", "Chase Atlantic", 3.5, 92));
        disk.add(new Song("505", "Arctic Monkeys", 4.1, 95));
        disk.add(new Song("Super Bass", "Nicki Minaj", 3.8, 88));
        disk.add(new Instrumental("Do I Wanna Know (Instrumental)", "Arctic Monkeys", 4.3, 80));
        disk.add(new Remix("Moon (Remix)", "Chase Atlantic", 3.9, 78));

        System.out.println("All compositions:");
        disk.print();

        System.out.printf("%nTotal duration: %.2f minutes%n", disk.totalDuration());

        disk.sortByPreference();
        System.out.println("\nSorted by preference:");
        disk.print();

        System.out.println("\nSearch by popularity range 70–90:");
        disk.findByPopularityRange(70, 90);
    }
}
