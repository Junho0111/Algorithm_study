package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ1245 {
    
    static int N; //세로
    static int M; //가로
    static int mountainPeakCount = 0;
    static int[][] map;

    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}; // 위, 아, 왼, 오, 위왼, 위오, 아윈, 아오
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited =  new boolean[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && bfs(i, j)) {
                    mountainPeakCount++;
                }
            }
        }

        System.out.println(mountainPeakCount);
    }

    static class Mountain {
        int x;
        int y;
        int high;

        public Mountain(int x, int y, int high) {
            this.x = x;
            this.y = y;
            this.high = high;
        }
    }

    private static boolean bfs(int x, int y) {
        ArrayDeque<Mountain> queue = new ArrayDeque<>();
        queue.add(new Mountain(x, y, map[x][y]));
        visited[x][y] = true;

        boolean mountainPeakCheck = true;

        while (!queue.isEmpty()) {
            Mountain mountain = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = mountain.x + dx[i];
                int nextY = mountain.y + dy[i];

                if(nextX >= 0 && nextY >=0 && nextX < N && nextY < M) {
                    if (mountain.high < map[nextX][nextY]) {
                        mountainPeakCheck = false;
                    }

                    if(!visited[nextX][nextY] && mountain.high == map[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        queue.add(new Mountain(nextX, nextY, map[nextX][nextY]));
                    }
                }
            }
        }

        return mountainPeakCheck;
    }
}

//농부 민식이가 관리하는 농장은 N×M 격자로 이루어져 있다.
// 민식이는 농장을 관리하기 위해 산봉우리마다 경비원를 배치하려 한다.
// 이를 위해 농장에 산봉우리가 총 몇 개 있는지를 세는 것이 문제다.
//
//산봉우리의 정의는 다음과 같다. 산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다.
// 여기서 "인접하다"의 정의는 X좌표 차이와 Y좌표 차이가 모두 1 이하인 경우이다.
// 또한 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다.
//
//문제는 격자 내에 산봉우리의 개수가 총 몇 개인지 구하는 것이다.

//입력
//첫째 줄에 정수 N(1 < N ≤ 100), M(1 < M ≤ 70)이 주어진다.
// 둘째 줄부터 N+1번째 줄까지 각 줄마다 격자의 높이를 의미하는 M개의 정수가 입력된다.
// 격자의 높이는 500보다 작거나 같은 음이 아닌 정수이다.

//출력
//첫째 줄에 산봉우리의 개수를 출력한다.

//8 7
//4 3 2 2 1 0 1
//3 3 3 2 1 0 1
//2 2 2 2 1 0 0
//2 1 1 1 1 0 0
//1 1 0 0 0 1 0
//0 0 0 1 1 1 0
//0 1 2 2 1 1 0
//0 1 1 1 2 1 0

//3