package hw11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B11_08 {

    static class DayForecast {
        String timePart;
        String temp;
        String humidity;

        public DayForecast(String timePart, String temp, String humidity) {
            this.timePart = timePart;
            this.temp = temp;
            this.humidity = humidity;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter city (English): ");
        String city = in.nextLine().trim();

        String url = "https://www.meteoprog.com/ua/weather/" + city + "/";
        String html = getHTML(url);

        List<DayForecast> forecast = parseDayPartData(html);

        System.out.println("\nПогода у місті: " + city);
        System.out.println("Дата: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        System.out.println("\n--- Прогноз на частини доби (4 значення) ---");
        System.out.printf("%-10s | %-15s | %-15s%n", "Час доби", "Температура", "Вологість");
        System.out.println("--------------------------------------------");

        if (forecast.isEmpty()) {
            System.out.println("Не вдалося знайти 4 значення. Перевірте Regex.");
        }

        for (DayForecast item : forecast) {
            System.out.printf("%-10s | %-15s | %-15s%n",
                    item.timePart,
                    item.temp,
                    item.humidity
            );
        }
    }


    public static List<DayForecast> parseDayPartData(String html) {
        List<DayForecast> results = new ArrayList<>();

        String regex = "(Вночі|Вранці|Вдень|Увечері).*?([+\\-]\\d+)°.*?\\d+%.*?(\\d+)%";

        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        Matcher m = p.matcher(html);

        int count = 0;
        while (m.find() && count < 4) {
            String timePart = m.group(1);
            String temperature = m.group(2) + "°C";
            String humidity = m.group(3) + "%";

            results.add(new DayForecast(timePart, temperature, humidity));
            count++;
        }
        return results;
    }

    public static String getHTML(String url) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .header("User-Agent", "Mozilla/5.0")
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}