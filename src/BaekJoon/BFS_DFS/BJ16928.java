package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ16928 {

    static ArrayDeque<locationAndDiceCont> queue = new ArrayDeque<>();
    static ArrayList<jump> jumpQueue = new ArrayList<>();
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사다리수
        int M = sc.nextInt(); // 뱀 수

        for (int i = 0; i < N+M; i++) {
            jumpQueue.add(new jump(sc.nextInt(), sc.nextInt()));
        }

        bfs();
    }

    public static class jump{
        int start, end;

        public jump(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class locationAndDiceCont{
        int location, diceCont;

        public locationAndDiceCont(int location, int diceCont) {
            this.location = location;
            this.diceCont = diceCont;
        }
    }

    private static void bfs() {
        queue.add(new locationAndDiceCont(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            locationAndDiceCont cucurrent = queue.poll();
            int nowLocation = cucurrent.location;
            int diceCnt = cucurrent.diceCont;

            if (nowLocation == 100) {
                System.out.println(diceCnt);
                return;
            }

            for (int i = 1; i <= 6; i++) { // 주사위 1~6까지
                int next = nowLocation + i;
                int nextDiceCnt = diceCnt + 1;

                if(next <= 100) {
                    for (jump j : jumpQueue) {
                        if (next == j.start) {
                            next = j.end;
                            break;
                        }
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(new locationAndDiceCont(next, nextDiceCnt));
                    }

                }
            }
        }
    }
}


// 입력
//첫째 줄에 게임판에 있는 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.
//
//둘째 줄부터 N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동한다는 의미이다.
//
//다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동한다는 의미이다.
//
// 1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다.
// 모든 칸은 최대 하나의 사다리 또는 뱀을 가지고 있으며,
// 동시에 두 가지를 모두 가지고 있는 경우는 없다. 항상 100번 칸에 도착할 수 있는 입력만 주어진다.

// 출력
//100번 칸에 도착하기 위해 주사위를 최소 몇 번 굴려야 하는지 출력한다.

//3 7
//32 62
//42 68
//12 98
//95 13
//97 25
//93 37
//79 27
//75 19
//49 47
//67 17

//3

//
//이거 2개를 세트로 묵고 주사위나온수랑 매번 맞는지 확인하고 맞으면 점프시키는거로 하는식
// int input = sc.nextInt();
//                int row = input / 10;
//                int col = input % 10;
//                map[row][col] = input;