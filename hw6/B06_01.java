package hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B06_01 {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("src/hw6/input.txt"));

        LocalDate today = LocalDate.now();
        String currDate = String.format("%02d.%02d.%04d",
                today.getDayOfMonth(),
                today.getMonthValue(),
                today.getYear());

        String DATE1 = "(?<d1>\\d{1,2})\\.(?<m1>\\d{1,2})\\.(?<y1>\\d{4})";
        String DATE2 = "_{2}\\._{2}\\._{4}";
        Pattern p = Pattern.compile(DATE1 + "|" + DATE2);
        Matcher m = p.matcher(text);
        String result = m.replaceAll(currDate);
        Files.writeString(Path.of("src/hw6/output.txt"), result);
        System.out.println("File created successfully!");
    }
}