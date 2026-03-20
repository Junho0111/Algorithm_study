package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ2638 {

    static int N; // 세로
    static int M; // 가로
    static int time = 0;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                map[n][m] = sc.nextInt();
            }
        }

        while (cheezeCheck()) {
            bfs();
            time++;
        }

        System.out.println(time);
    }

    static class Air {
        int x;
        int y;

        public Air(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 다 돌았는데 치즈 없으면 false
    private static boolean cheezeCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //이게 하루치 치즈녹임
    private static void bfs() {
        ArrayDeque<Air> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int[][] cheezeMap = new int[N][M]; // bfs할떄마다 새롭게 초기화

        queue.add(new Air(0, 0)); // 무조건 첫번쨰 공기부터 끝까지 치즈 겉을 전부 돔(치즈 안 제외)
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Air air = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = air.x + dx[i];
                int nextY = air.y + dy[i];

                if(nextX >= 0 && nextY >= 0 && nextX < N &&  nextY < M) {

                    if(map[nextX][nextY] == 0 && !visited[nextX][nextY]) {//다음길이 공기이면 계속 탐색
                        visited[nextX][nextY] = true;
                        queue.add(new Air(nextX, nextY));
                    } else if (map[nextX][nextY] == 1) {//다음길이 치즈이면 공기 몇번만났는지를 카운트하기위해 1증가
                        cheezeMap[nextX][nextY] += 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(cheezeMap[i][j] >= 2) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
//공기기준으ㅗㄹ 돌아야함


//공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서
// 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다.
// 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

//치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다.
// 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

//모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

//입력
//첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다.
// 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다.
// 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

//출력
//출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.

//8 9
//0 0 0 0 0 0 0 0 0
//0 0 0 1 1 0 0 0 0
//0 0 0 1 1 0 1 1 0
//0 0 1 1 1 1 1 1 0
//0 0 1 1 1 1 1 0 0
//0 0 1 1 0 1 1 0 0
//0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0

//4
