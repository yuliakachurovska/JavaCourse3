package B04_04;

public class Instrumental extends MusicComposition {
    public Instrumental(String title, String artist, double duration, int popularity) {
        super(title, artist, duration, popularity);
    }

    @Override
    public double getPreference() {
        return 0.8 * popularity + 10;
    }
}