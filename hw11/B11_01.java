package hw11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_01 {

    public static void main(String[] args) {
        String url = "http://worldtimeapi.org/api/timezone/Europe/Kyiv";

        String json = getHTML(url);
        String exactTime = parseTime(json);

        System.out.println("Точний час (API): " + exactTime);

        LocalTime serverTime = LocalTime.parse(exactTime);
        LocalTime localTime = LocalTime.now().withNano(0);

        System.out.println("Локальний час:     " + localTime);

        long diff = Math.abs(serverTime.toSecondOfDay() - localTime.toSecondOfDay());

        if (diff <= 1) {
            System.out.println("Час на комп’ютері відповідає точному.");
        } else {
            System.out.println("Різниця: " + diff + " секунд.");
        }
    }

    public static String parseTime(String json) {
        Pattern p = Pattern.compile("\"datetime\":\"(\\d{4}-\\d{2}-\\d{2}T(\\d{2}:\\d{2}:\\d{2}))");
        Matcher m = p.matcher(json);

        if (m.find()) {
            return m.group(2); // hh:mm:ss
        }
        return "00:00:00";
    }

    public static String getHTML(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}