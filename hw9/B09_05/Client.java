package hw9.B09_05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Client implements Runnable {
    private static final AtomicInteger CLIENT_COUNT = new AtomicInteger(0);
    private final int clientId = CLIENT_COUNT.incrementAndGet();

    private final CallCenter center;
    private final int T5_MIN;
    private final int T6_MAX;

    public Client(CallCenter center, int T5_MIN, int T6_MAX) {
        this.center = center;
        this.T5_MIN = T5_MIN;
        this.T6_MAX = T6_MAX;
    }

    @Override
    public void run() {
        int retries = 0;
        System.out.printf("[Клієнт %d] Зателефонував в Call-центр.\n", clientId);

        while (true) {
            try {
                if (center.operators.tryAcquire(0, TimeUnit.MILLISECONDS)) {

                    long serviceTime = Utils.getRandomTime(center.T3_MIN, center.T4_MAX);
                    System.out.printf("[Клієнт %d] => Обслуговується. Оператори вільні: %d. Спроб: %d.\n",
                            clientId, center.operators.availablePermits(), retries);
                    Thread.sleep(serviceTime);

                    center.operators.release();
                    System.out.printf("[Клієнт %d] Обслуговування завершено. Лінію звільнено.\n", clientId);

                    center.updateMaxRetries(retries);
                    break;

                } else {
                    retries++;
                    long retryTime = Utils.getRandomTime(T5_MIN, T6_MAX);

                    System.out.printf("[Клієнт %d] <-- ВСІ ОПЕРАТОРИ ЗАЙНЯТІ. Кладе трубку. Спроба #%d. Передзвонить через %d мс.\n",
                            clientId, retries, retryTime);
                    Thread.sleep(retryTime);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.printf("[Клієнт %d] Потік перервано під час очікування.\n", clientId);
                break;
            }
        }
    }
}