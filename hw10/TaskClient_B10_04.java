package hw10;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TaskClient_B10_04 {

    public static void main(String[] args) throws IOException {
        int port = 14110;
        String host = "localhost";
        String fileInp = "src/hw10/client_input.txt";
        String searchChar = "a";

        try {
            Socket sock = new Socket(host, port);
            System.out.println("Connected to server: " + sock.getRemoteSocketAddress());

            BufferedReader netReader = new BufferedReader(
                    new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8)
            );
            PrintStream netWriter = new PrintStream(
                    sock.getOutputStream(), true, StandardCharsets.UTF_8
            );

            BufferedReader fileReader = new BufferedReader(
                    new FileReader(fileInp, StandardCharsets.UTF_8)
            );

            System.out.println("Sending filter char: " + searchChar);
            netWriter.println(searchChar);

            while (true) {
                String line = fileReader.readLine();
                if (line == null) {
                    break;
                }

                netWriter.println(line);
                String response = netReader.readLine();
                System.out.println("Server response for [" + line + "]: " + response);
            }

            fileReader.close();
            sock.close();
            System.out.println("Disconnected.");

        } catch (FileNotFoundException e) {
            System.err.println("Помилка: Файл " + fileInp + " не знайдено! Створіть файл перед запуском.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
