package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ1707 {

    static final int RED = 1;
    static final int BLUE = 2;

    static ArrayList<Integer>[] list;
    static int[] colors;
    static boolean[] visited;
    static String checkYesOrNo;
    static ArrayList<String> resultSet; // 걍 결과 예쁘게 모아서 출력하려고만든거 무시해도됨

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        resultSet = new ArrayList<>(K);

        for (int k = 0; k < K; k++) {
            int V = sc.nextInt(); // 정점 개수
            int E = sc.nextInt(); // 간선 개수

            list = new ArrayList[V + 1];
            colors = new int[V + 1];
            visited = new boolean[V + 1];

            for (int i = 1; i <= V; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                int i1 = sc.nextInt();
                int i2 = sc.nextInt();

                list[i1].add(i2);
                list[i2].add(i1);
            }

            checkYesOrNo = "YES";
            for (int i = 1; i <= V; i++) {
                if (!visited[i] && bfs(i).equals("NO")) {
                    break;
                }
            }

            resultSet.add(checkYesOrNo);
        }

        for (String r : resultSet) {
            System.out.println(r);
        }
    }

    public static class Node {
        int nodeIdx;
        int color;

        public Node(int nodeIdx, int color) {
            this.nodeIdx = nodeIdx;
            this.color = color;
        }
    }

    private static String bfs(int startNode) {
        ArrayDeque<Node> queue = new ArrayDeque<>();

        colors[startNode] = RED;
        visited[startNode] = true;
        queue.add(new Node(startNode, RED));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (int linkedNode : list[currentNode.nodeIdx]) {
                if (colors[linkedNode] == currentNode.color) {
                    return checkYesOrNo = "NO";
                }

                if (!visited[linkedNode]) {
                    visited[linkedNode] = true;
                    int nextColor = (currentNode.color == RED) ? BLUE : RED;
                    colors[linkedNode] = nextColor;
                    queue.add(new Node(linkedNode, nextColor));
                }
            }
        }
        return checkYesOrNo = "YES";
    }
}

//그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.
//
//그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

// 입력
//입력은 여러 개의 테스트 케이스로 구성되어 있는데,
// 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
// 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
// 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다.

// 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.

// 출력
//K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

//2
//3 2
//1 3
//2 3
//4 4
//1 2
//2 3
//3 4
//4 2

//YES
//NO