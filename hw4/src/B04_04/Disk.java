package B04_04;

import java.util.*;

public class Disk {
    private final List<MusicComposition> tracks = new ArrayList<>();

    public void add(MusicComposition comp) {
        tracks.add(comp);
    }

    public double totalDuration() {
        double sum = 0;
        for (MusicComposition c : tracks) {
            sum += c.getDuration();
        }
        return sum;
    }

    public void sortByPreference() {
        tracks.sort(new Comparator<MusicComposition>() {
            @Override
            public int compare(MusicComposition a, MusicComposition b) {
                return Double.compare(b.getPreference(), a.getPreference());
            }
        });    }

    public void findByPopularityRange(int minP, int maxP) {
        System.out.println("Compositions with popularity in [" + minP + ", " + maxP + "]:");
        for (MusicComposition c : tracks) {
            if (c.getPopularity() >= minP && c.getPopularity() <= maxP) {
                c.print();
            }
        }
    }

    public void print() {
        for (MusicComposition c : tracks) {
            c.print();
        }
    }
}
