package hw8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class B08_03_UndirectedGraph<T> {

    private Map<T, Set<T>> adjacencyList;
    public B08_03_UndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void removeVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }
        Set<T> neighbors = adjacencyList.get(vertex);
        for (T neighbor : neighbors) {
            Set<T> neighborList = adjacencyList.get(neighbor);
            if (neighborList != null) {
                neighborList.remove(vertex);
            }
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T vertex1, T vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }

    public void removeEdge(T vertex1, T vertex2) {
        Set<T> neighbors1 = adjacencyList.get(vertex1);
        if (neighbors1 != null) {
            neighbors1.remove(vertex2);
        }

        Set<T> neighbors2 = adjacencyList.get(vertex2);
        if (neighbors2 != null) {
            neighbors2.remove(vertex1);
        }
    }

    public void printGraph() {
        if (adjacencyList.isEmpty()) {
            System.out.println("Граф порожній.");
            return;
        }

        System.out.println("Список суміжності:");
        for (Map.Entry<T, Set<T>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
    }
}