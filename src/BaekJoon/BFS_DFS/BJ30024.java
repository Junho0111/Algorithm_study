package BaekJoon.BFS_DFS;

import java.util.*;

public class BJ30024 {

    static int N; //세로
    static int M; //가로
    static int K; // 옥수수 몇개 수확 ?

    static int[][] map;
    static boolean[][] visited;

    static final int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static final int[] dy = {0, 0, -1, 1};

    static ArrayList<Corner> cornerList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                map[n][m] = sc.nextInt();
                //제일 테두리 옥수수들 바로 수확
                if (n == 1 || n == N || m == 1 || m == M) {
                    cornerList.add(new Corner(n, m, map[n][m]));
                    visited[n][m] = true;
                }
            }
        }

        K = sc.nextInt();

        for (int k = 0; k < K; k++) {
            int max = 0;
            for (int i = 1; i < cornerList.size(); i++) {
                if (cornerList.get(i).value > cornerList.get(max).value) {
                    max = i;
                }
            }

            Corner best = cornerList.remove(max);
            System.out.println(best.x + " " + best.y);

            for (int i = 0; i < 4; i++) {
                int nextX = best.x + dx[i];
                int nextY = best.y + dy[i];

                if (nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= M && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    cornerList.add(new Corner(nextX, nextY, map[nextX][nextY]));
                }
            }
        }
    }

    static class Corner {
        int x;
        int y;
        int value;

        public Corner(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}

//민석이는 처음에 옥수수밭 바깥에 위치한다.
// 민석이는 옥수수밭 바깥을 돌아다니면서 옥수수밭 바깥과 인접한 칸의 옥수수를 수확할 수 있다.
// 또는 옥수수밭 안에서 옥수수를 수확한 칸으로만 돌아다니면서 현재 위치한 칸에서 상하좌우로 인접한 칸의 옥수수를 수확할 수 있다.
//
//그런데, 민석이는 옥수수의 생산량 조절을 위해서
//K그루의 옥수수만 수확하려고 한다. 민석이는 현재 수확할 수 있는 옥수수 중에서 가장 가치가 높은 옥수수를 수확하는 과정을
//K번 반복한다. 민석이가 수확하는 옥수수의 위치를 순서대로 구해보자.

//입력
//첫째 줄에 정수
//N, M(1 \le N, M \le 1\,000)이 공백으로 구분되어 주어진다.
//
//둘째 줄부터 N개의 줄에 걸쳐 M개의 정수가 공백으로 구분되어 주어진다.
//N개의 줄 중 i번째 줄의 j번째 정수는 격자에서 i번째 줄의 j번째 칸의 옥수수의 가치를 의미하는 정수 a_{ij}(1 \le a_{ij} \le N \times M)다.
//
//마지막 줄에 정수
//K(1\le K \le \min(N \times M, 100\,000))가 주어진다.

//출력
//K개의 줄에 민석이가 수확하는 옥수수의 위치
//i,j(1\le i\le N;1\le j\le M)를 순서대로 출력한다.
//i,j는 격자의
//i번째 행, j번째 열을 의미한다.


//와일문 하나씩 돌때마다 겉에 한번씩ㄷ는데? 그때 돌면서 만나는 옥수수 위치랑 값을 클래스로 만들어서 리스트에 넣고 다 돌고나서 리스트에[서 가장 큰 옥수수값 뽑아서 그거 위치를 따로 기억하고있음 되잖아 씨빨

//4 5
//1 18 2 3 4
//12 17 15 20 5
//11 14 19 13 6
//10 9 16 8 7
//6

//1 2
//2 2
//4 3
//3 3
//2 3
//2 4