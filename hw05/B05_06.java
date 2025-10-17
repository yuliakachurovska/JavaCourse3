package hw05;

import java.io.*;
import java.util.regex.*;

public class B05_06 {
    public static void main(String[] args) {
        String inputFile = "src/hw05/books.txt";
        String outputYearFile = "src/hw05/books_by_year.txt";
        String outputCountFile = "src/hw05/books_by_author.txt";
        int yearToFind = 1997;

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                PrintWriter pwYear = new PrintWriter(new FileWriter(outputYearFile));
                PrintWriter pwCount = new PrintWriter(new FileWriter(outputCountFile))
        ) {
            // "Автор; Назва; Рік"
            Pattern pattern = Pattern.compile("^(.*?);\\s*(.*?);\\s*(\\d{4})$");

            String line;
            String[] authors = new String[100];
            int[] counts = new int[100];
            int authorCount = 0;

            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.matches()) {
                    String author = m.group(1).trim();
                    int year = Integer.parseInt(m.group(3));

                    // a) Якщо рік збігається — записуємо у файл
                    if (year == yearToFind) {
                        pwYear.println(line);
                    }

                    // b) Підрахунок кількості книжок автора
                    int i;
                    for (i = 0; i < authorCount; i++) {
                        if (authors[i].equals(author)) {
                            counts[i]++;
                            break;
                        }
                    }
                    if (i == authorCount) {
                        authors[authorCount] = author;
                        counts[authorCount] = 1;
                        authorCount++;
                    }
                }
            }

            for (int i = 0; i < authorCount; i++) {
                pwCount.println(authors[i] + " — " + counts[i]);
            }

            System.out.println("Результати записано у файли:");
            System.out.println(" • " + outputYearFile);
            System.out.println(" • " + outputCountFile);

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }
}