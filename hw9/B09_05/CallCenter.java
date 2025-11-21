package hw9.B09_05;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;


public class CallCenter {
    public final Semaphore operators;
    private final AtomicInteger maxRetries;

    // Параметри часу для обслуговування, які потрібні клієнту
    public final int T3_MIN;
    public final int T4_MAX;

    public CallCenter(int N_OPERATORS, int T3_MIN, int T4_MAX) {
        this.operators = new Semaphore(N_OPERATORS);
        this.maxRetries = new AtomicInteger(0);
        this.T3_MIN = T3_MIN;
        this.T4_MAX = T4_MAX;
    }

    public void updateMaxRetries(int currentClientRetries) {
        maxRetries.updateAndGet(currentMax -> Math.max(currentMax, currentClientRetries));
    }

    public int getMaxRetries() {
        return maxRetries.get();
    }
}