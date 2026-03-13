package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ2636 {

    static int[][] map;
    static int N, M;
    static boolean[][] visited;
    static final int[] dx = {-1, 1, 0, 0}; //위, 아, 왼, 오
    static final int[] dy = {0, 0, -1, 1};

    static int maxTime = 0;
    static int lastCheeseCount;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (!cheeseCount()) {
            bfs();
            maxTime++;
        }

        System.out.println(maxTime);
        System.out.println(lastCheeseCount);
    }

    private static boolean cheeseCount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    count ++;
                }
            }
        }

        if (count != 0) {
            lastCheeseCount = count;
            return false;
        } else {
            return true;
        }

    }

    static class Cheese {
        int x, y;

        public Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs() {
        ArrayDeque<Cheese> queue = new ArrayDeque<>();
        visited = new boolean[N][M];

        queue.add(new Cheese(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Cheese cheese = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nextX = cheese.x + dx[k];
                int nextY = cheese.y + dy[k];

                if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;

                    if(map[nextX][nextY] == 0) {
                        queue.add(new Cheese(nextX, nextY));
                    }

                    if(map[nextX][nextY] == 1) {
                        map[nextX][nextY] = 0;
                    }
                }
            }
        }
    }
}

// 입력
//첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다.
// 세로와 가로의 길이는 최대 100이다. 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다.
// 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.

// 출력
//첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고,
// 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.

//13 12
//0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 1 1 0 0 0
//0 1 1 1 0 0 0 1 1 0 0 0
//0 1 1 1 1 1 1 0 0 0 0 0
//0 1 1 1 1 1 0 1 1 0 0 0
//0 1 1 1 1 0 0 1 1 0 0 0
//0 0 1 1 0 0 0 1 1 0 0 0
//0 0 1 1 1 1 1 1 1 0 0 0
//0 0 1 1 1 1 1 1 1 0 0 0
//0 0 1 1 1 1 1 1 1 0 0 0
//0 0 1 1 1 1 1 1 1 0 0 0
//0 0 0 0 0 0 0 0 0 0 0 0

//3
//5
