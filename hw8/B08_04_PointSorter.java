package hw8;

import java.util.PriorityQueue;

public class B08_04_PointSorter {

    static class Point implements Comparable<Point> {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistanceSquared() {
            return (this.x * this.x) + (this.y * this.y);
        }

        public double getDistance() {
            return Math.sqrt(getDistanceSquared());
        }

        @Override
        public int compareTo(Point other) {
            return Double.compare(this.getDistanceSquared(), other.getDistanceSquared());
        }

        @Override
        public String toString() {
            return String.format("Point(x=%.1f, y=%.1f) -> Distance = %.2f",
                    this.x, this.y, this.getDistance());
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Point> pointQueue = new PriorityQueue<>();
        System.out.println("--- 1. Додавання точок (n=6) ---");
        pointQueue.add(new Point(3, 4));    // Відстань = 5.0
        pointQueue.add(new Point(1, 1));    // Відстань = 1.41
        pointQueue.add(new Point(0, 0));    // Відстань = 0.0
        pointQueue.add(new Point(-5, 0));   // Відстань = 5.0
        pointQueue.add(new Point(10, 10));  // Відстань = 14.14
        pointQueue.add(new Point(-2, -1));  // Відстань = 2.23

        System.out.println("Точки додано в PriorityQueue.");

        System.out.println("\n--- 2. Виведення точок у порядку зростання відстані ---");
        while (!pointQueue.isEmpty()) {
            Point nearestPoint = pointQueue.poll();
            System.out.println(nearestPoint);
        }
        // Вивід буде відсортований за відстанню:
        // Point(x=0.0, y=0.0) -> Distance = 0.00
        // Point(x=1.0, y=1.0) -> Distance = 1.41
        // Point(x=-2.0, y=-1.0) -> Distance = 2.24
        // Point(x=3.0, y=4.0) -> Distance = 5.00
        // Point(x=-5.0, y=0.0) -> Distance = 5.00
        // Point(x=10.0, y=10.0) -> Distance = 14.14
    }
}