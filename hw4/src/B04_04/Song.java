package B04_04;

public class Song extends MusicComposition {
    public Song(String title, String artist, double duration, int popularity) {
        super(title, artist, duration, popularity);
    }

    @Override
    public double getPreference() {
        return 0.6 * popularity + 20;
    }
}
