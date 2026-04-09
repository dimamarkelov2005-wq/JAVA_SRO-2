import java.util.*;

public class Main {
    private final int vertices;
    private final List<List<Integer>> adj; // Используем List для гибкости

    public Main(int v) {
        this.vertices = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Добавление ребра (сделаем его двусторонним для красоты)
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // Интерактивный BFS с расчетом дистанции
    public void bfsWithDistances(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices]; // "Моя фишка": считаем шаги от старта

        Arrays.fill(distance, -1); // -1 означает, что вершина недоступна

        visited[startNode] = true;
        distance[startNode] = 0;
        queue.add(startNode);

        System.out.println("\n--- Результат обхода ---");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println("Посещена вершина: " + current + " (Уровень: " + distance[current] + ")");

            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1; // Уровень соседа = уровень текущей + 1
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество вершин в графе: ");
        int v = scanner.nextInt();
        Main graph = new Main(v);

        System.out.print("Введите количество ребер: ");
        int e = scanner.nextInt();

        System.out.println("Введите ребра (например, '0 1' для связи между 0 и 1):");
        for (int i = 0; i < e; i++) {
            int source = scanner.nextInt();
            int dest = scanner.nextInt();
            if (source < v && dest < v) {
                graph.addEdge(source, dest);
            } else {
                System.out.println("Ошибка: вершины должны быть меньше " + v);
                i--; // Повторить ввод
            }
        }

        System.out.print("С какой вершины начать обход? ");
        int start = scanner.nextInt();

        graph.bfsWithDistances(start);

        scanner.close();
    }
}
