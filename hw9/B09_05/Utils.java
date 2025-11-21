package hw9.B09_05;

import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();

    public static long getRandomTime(int min, int max) {
        if (min > max) {
            return min;
        }
        return min + RANDOM.nextInt(max - min + 1);
    }
}