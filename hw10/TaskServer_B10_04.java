package hw10;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TaskServer_B10_04 {

    public static void main(String[] args) throws IOException {
        int port = 14110;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port + ". Waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected: " + socket.getRemoteSocketAddress());

        BufferedReader netReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
        );
        PrintStream netWriter = new PrintStream(
                socket.getOutputStream(), true, StandardCharsets.UTF_8
        );

        String serverFileOut = "src/server_filtered.txt";
        BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(serverFileOut, StandardCharsets.UTF_8)
        );

        try {
            String filterLine = netReader.readLine();
            if (filterLine == null) return;

            char filterChar = filterLine.charAt(0);
            System.out.println("Received filter char: '" + filterChar + "'");

            while (true) {
                String line = netReader.readLine();

                if (line == null || line.equalsIgnoreCase("END")) {
                    break;
                }

                System.out.println("Received line: " + line);

                if (line.contains(String.valueOf(filterChar))) {
                    fileWriter.write(line);
                    fileWriter.newLine();
                    fileWriter.flush();
                    netWriter.println("Line Saved (contains '" + filterChar + "')");
                } else {
                    netWriter.println("Line Ignored");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileWriter.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server stopped.");
        }
    }
}