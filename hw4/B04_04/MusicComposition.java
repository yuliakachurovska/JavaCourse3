package B04_04;

public abstract class MusicComposition {
    protected String title;
    protected String artist;
    protected double duration;
    protected int popularity;

    public MusicComposition(String title, String artist, double duration, int popularity) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.popularity = popularity;
    }

    public double getDuration() {
        return duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getPreference();

    public void print() {
        System.out.printf("%s by %s (%.2f min, popularity %d)%n",
                title, artist, duration, popularity);
    }
}
