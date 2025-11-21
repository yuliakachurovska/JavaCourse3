package hw9.B09_05;

import java.util.ArrayList;
import java.util.List;


public class CallCenterSimulation {

    private static final int N_OPERATORS = 3;  // N: Кількість операторів
    private static final int NUM_CLIENTS = 15; // Кількість клієнтів

    // Час між дзвінками клієнтів (T1-T2)
    private static final int T1_MIN_ARRIVAL = 500;
    private static final int T2_MAX_ARRIVAL = 1500;

    // Час обслуговування (T3-T4)
    private static final int T3_MIN_SERVICE = 2000;
    private static final int T4_MAX_SERVICE = 5000;

    // Час повторного дзвінка (T5-T6)
    private static final int T5_MIN_RETRY = 500;
    private static final int T6_MAX_RETRY = 2000;

    public static void main(String[] args) throws InterruptedException {

        CallCenter center = new CallCenter(
                N_OPERATORS,
                T3_MIN_SERVICE,
                T4_MAX_SERVICE
        );

        List<Thread> clientThreads = new ArrayList<>();

        System.out.printf("--- Call-центр розпочав роботу. Операторів: %d, Клієнтів очікується: %d. ---\n",
                N_OPERATORS, NUM_CLIENTS);

        for (int i = 0; i < NUM_CLIENTS; i++) {

            Client clientTask = new Client(center, T5_MIN_RETRY, T6_MAX_RETRY);
            Thread clientThread = new Thread(clientTask);
            clientThreads.add(clientThread);

            clientThread.start();

            long arrivalDelay = Utils.getRandomTime(T1_MIN_ARRIVAL, T2_MAX_ARRIVAL);
            Thread.sleep(arrivalDelay);
        }

        for (Thread thread : clientThreads) {
            thread.join();
        }

        System.out.println("\n--- Результати Симуляції ---");
        System.out.printf("Загальна кількість обслугованих клієнтів: %d\n", NUM_CLIENTS);
        System.out.printf("Максимальна кількість повторних спроб дзвінка від одного клієнта: %d\n", center.getMaxRetries());
        System.out.println("----------------------------");
    }
}