package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BJ2665 {

    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int M;
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        N = n;
        M = n;
        map = new int[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String row = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        System.out.println(bfs(0, 0));
    }

    static class Cur {

        int x, y;
        int cnt = 0;

        public Cur(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Cur(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    private static int bfs(int x, int y) {
        ArrayDeque<Cur> queue = new ArrayDeque<>();
        dist[x][y] = 0;
        queue.add(new Cur(x, y));

        while (!queue.isEmpty()) {
            Cur cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.cnt;
            }

            // 이미 더 적은 횟수로 갱신된 상태라면 스킵
            if (cur.cnt > dist[cur.x][cur.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                int nextCnt = cur.cnt;

                // 기존 상태보다 최단 기록이라면 진행
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && nextCnt < (dist[nextX][nextY]) && map[nextX][nextY] == 1) { //흰
                    dist[nextX][nextY] = nextCnt;
                    queue.addFirst(new Cur(nextX, nextY, dist[nextX][nextY]));
                }

                // 검은방 부숴도 기존 상태보다 최단 기록이라면 진행
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && nextCnt + 1 < (dist[nextX][nextY]) && map[nextX][nextY] == 0) { //검
                    dist[nextX][nextY] = nextCnt + 1;
                    queue.addLast(new Cur(nextX, nextY, dist[nextX][nextY]));
                }
            }
        }
        return 0;
    }
}

//미로만들기

//n×n 바둑판 모양으로 총 n2개의 방이 있다. 일부분은 검은 방이고 나머지는 모두 흰 방이다.
//검은 방은 사면이 벽으로 싸여 있어 들어갈 수 없다. 서로 붙어 있는 두 개의 흰 방 사이에는 문이 있어서 지나다닐 수 있다.
//윗줄 맨 왼쪽 방은 시작방으로서 항상 흰 방이고, 아랫줄 맨 오른쪽 방은 끝방으로서 역시 흰 방이다.
//
//시작방에서 출발하여 길을 찾아서 끝방으로 가는 것이 목적인데, 아래 그림의 경우에는 시작방에서 끝 방으로 갈 수가 없다.
//부득이 검은 방 몇 개를 흰 방으로 바꾸어야 하는데 되도록 적은 수의 방의 색을 바꾸고 싶다.
//
//아래 그림은 n=8인 경우의 한 예이다.

//위 그림에서는 두 개의 검은 방(예를 들어 (4,4)의 방과 (7,8)의 방)을 흰 방으로 바꾸면, 시작방에서 끝방으로 갈 수 있지만,
//어느 검은 방 하나만을 흰 방으로 바꾸어서는 불가능하다. 검은 방에서 흰 방으로 바꾸어야 할 최소의 수를 구하는 프로그램을 작성하시오.
//
//단, 검은 방을 하나도 흰방으로 바꾸지 않아도 되는 경우는 0이 답이다.

//입력
//첫 줄에는 한 줄에 들어가는 방의 수 n(1 ≤ n ≤ 50)이 주어지고, 다음 n개의 줄의 각 줄마다 0과 1이 이루어진 길이가 n인 수열이 주어진다.
//0은 검은 방, 1은 흰 방을 나타낸다.

//출력
//첫 줄에 흰 방으로 바꾸어야 할 최소의 검은 방의 수를 출력한다.

//8
//11100110
//11010010
//10011010
//11101100
//01000111
//00110001
//11011000
//11000111

//2