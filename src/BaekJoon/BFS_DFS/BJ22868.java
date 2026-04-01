package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ22868 {
    static int N;
    static int M;
    static int S;
    static int E;
    static ArrayList<Integer>[] graph;
    static int[] parent;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        S = sc.nextInt();
        E = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }
        boolean[] checkPath = new boolean[N + 1];
        result += bfs(S, E, checkPath);

        int preNode = parent[E]; // E를 넣으면 직전 값
        while (preNode != S) { // 사용한 위치 체크
            checkPath[preNode] = true;
            preNode = parent[preNode];
        }

        result += bfs(E, S, checkPath);

        System.out.println(result);
    }

    static class Node {
        int x;
        int distance;

        public Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }

    private static int bfs(int S, int E, boolean[] check) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        parent = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            if (check[i]) {
                visited[i] = true;
            }
        }

        queue.add(new Node(S, 0));
        visited[S] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int currentNode = node.x;
            int currentDistance = node.distance;

            if (currentNode == E) {
                return currentDistance;
            }

            for (int next : graph[currentNode]) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = currentNode; // 어디서 왔는지 기록
                    queue.add(new Node(next, currentDistance + 1));
                }
            }
        }
        return 0;
    }
}

//코로나로 인하여 확찐자가 되버려 오늘부터 산책을 하려고 한다. 이를 위해 산책할 경로를 정하려고 한다.
//
//현재 있는 곳 S에서 출발하여 S와 다른 곳인 E를 찍고 다시 S로 돌아오는 경로로 만들려고 한다.
// 산책을 할 때 이미 갔던 정점을 또 가기 싫어 E에서 S로 올 때 S에서 E로 가는 도중에 방문한 정점을 제외한 다른 정점으로 이동하려고 한다.
// 또한 산책 거리가 긴 것을 싫어하여 S에서 E로 가는 가장 짧은 거리와 E에서 S로 가는 가장 짧은 거리를 원한다.
//정점 S에서 정점 E로 이동할 때, 가장 짧은 거리의 경로가 여러개 나올 수 있다.
// 그 중, 정점 S에서 정점 E로 이동한 경로를 나열했을 때, 사전순으로 가장 먼저 오는 것을 선택한다.
//
//예를 들어, 정점 1에서 정점 2로 이동한다고 했을 때, 가장 짧은 거리의 경로가 1 4 3 2와 1 3 4 2가 있다고 가정을 해보자.
// 두 개의 경로중 사전순으로 먼저 오는 것은 1 3 4 2이므로 정점 1에서 정점 2로 가는 최단 경로 중 두 번째 것을 선택한다.
//
//이와 같이 산책 경로를 정할 때, 산책 전체 경로의 거리(S에서 E로 가는 거리 + E에서 S로 가는 거리)를 구해보자.

//입력
//첫 번째 줄에는 정점의 개수
//N과 두 정점 사이를 잇는 도로의 개수
//M이 공백으로 구분되어 주어진다.
//
//두 번째 줄부터 M + 1 번째 줄까지 정점 A, B가 공백으로 구분되어 주어진다.
// 정점 A와 정점 B 사이의 거리는 항상 1이다. 이때, 정점
//A와 정점 B는 양방향으로 이동해도 된다.
//
//정점 A와 정점 B를 잇는 도로는 두개 이상 주어지지 않는다.
// M + 2번째 줄에는 정점 S와 정점 E가 공백으로 구분되어 주어진다.
//
//산책을 할 수 있는 경로가 있는 데이터만 주어진다.

//출력
//산책의 전체 경로의 길이를 출력한다.

//4 5
//1 2
//1 3
//2 3
//2 4
//3 4
//1 4

//4

//1 -> 2 -> 4 -> 3 -> 1 순서대로 오면 된다.