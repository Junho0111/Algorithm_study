package BaekJoon.BFS_DFS;

import java.util.*;

public class BJ1240 {
    static int N, M;
    static ArrayList<Integer>[] tree;
    static ArrayList<NodeRelationship> list = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        tree = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int startNode = sc.nextInt();
            int endNode = sc.nextInt();
            int d = sc.nextInt();

            tree[startNode].add(endNode);
            tree[endNode].add(startNode);

            list.add(new NodeRelationship(startNode,endNode,d));
        }

        for (int i = 0; i < M; i++) {
            int startNode = sc.nextInt();
            int endNode = sc.nextInt();

            System.out.println(bfs(startNode, endNode));
        }
    }

    static class NodeRelationship {
        int startNode, endNode;
        int distance;

        public NodeRelationship(int startNode, int endNode, int distance) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.distance = distance;
        }

    }

    private static int bfs(int startNode, int endNode) {
        boolean[] visited =  new boolean[N + 1];
        ArrayDeque<int[]> queue =  new ArrayDeque<>();

        queue.add(new int[]{startNode, 0});
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (currentNode == endNode) {
                return currentDistance;
            }

            for (int nextNode : tree[currentNode]) {
                if(!visited[nextNode]){
                    visited[nextNode] = true;

                    int sumDistance = 0;

                    for (NodeRelationship nodeRelationship : list) {
                        if((nodeRelationship.startNode == currentNode &&  nodeRelationship.endNode == nextNode) ||
                           (nodeRelationship.startNode == nextNode &&  nodeRelationship.endNode == currentNode)){
                            sumDistance += nodeRelationship.distance;
                            break;
                        }
                    }
                    queue.add(new int[]{nextNode, currentDistance + sumDistance});
                }
            }
        }
        return 0;
    }
}

//문제
//N개의 노드로 이루어진 트리가 주어지고 M개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

//입력
//첫째 줄에 노드의 개수
//N과 거리를 알고 싶은 노드 쌍의 개수
//M이 입력되고 다음
//N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에는 거리를 알고 싶은
//M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다.

//출력
//M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

//4 2
//2 1 2
//4 3 2
//1 4 3
//1 2
//3 2

//2
//7
