package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BJ10026 {
    static ArrayDeque<color> queue = new ArrayDeque<>();
    static int N;
    static String[][] map;
    static boolean[][] visited;
    static final int[] dn = {1, -1, 0, 0}; //오 왼 아 위
    static final int[] dm = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new String[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = String.valueOf(str.charAt(j));
            }
        }

        int normalAreasCount = getTotalAreas(); //일반구역개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].equals("G")) {
                    map[i][j] = "R";
                }
            }
        }
        for (boolean[] v : visited) { //방문 초기화
            Arrays.fill(v, false); // 각행 false로 초기화
        }

        int colorBlindnessAreasCount = getTotalAreas(); //색약구역개수
        System.out.println(normalAreasCount + " " + colorBlindnessAreasCount);
    }

    public static class color {
        int n, m;

        public color(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    private static int getTotalAreas() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int startN, int startM) {
        queue.add(new color(startN, startM));
        visited[startN][startM] = true;
        String currentColor = map[startN][startM];

        while (!queue.isEmpty()) {
            color cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextN = cur.n + dn[i];
                int nextM = cur.m + dm[i];

                if (nextN >= 0 && nextM >= 0 && nextN < N && nextM < N && !visited[nextN][nextM] && map[nextN][nextM].equals(currentColor)) {
                    visited[nextN][nextM] = true;
                    queue.add(new color(nextN, nextM));
                }
            }
        }
    }
}

//적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다.
// 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
//
//크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다.
// 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
// 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
// (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

//예를 들어, 그림이 아래와 같은 경우에
//RRRBB
//GGBBB
//BBBRR
//BBRRR
//RRRRR
//적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
//
//그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.


// 입력
//첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
//
//둘째 줄부터 N개 줄에는 그림이 주어진다.

// 출력
//적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.

//4 3