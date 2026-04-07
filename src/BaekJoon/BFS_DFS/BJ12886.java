package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BJ12886 {
    static int A;
    static int B;
    static int C;
    static int sum;
    static boolean[][] visited = new boolean[1501][1501];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        sum = A + B + C;

        System.out.println(((A + B + C) % 3 != 0) ? 0 : bfs(A,  B, C));
    }

    static class Stone {
        int a, b, c;

        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private static int bfs(int a, int b, int c) {
        ArrayDeque<Stone> queue = new ArrayDeque<>();

        queue.add(new Stone(a, b, c));
        visited[a][b] = true;

        while (!queue.isEmpty()) {
            Stone stoneGroup = queue.poll();

            if (stoneGroup.a == stoneGroup.b && stoneGroup.b == stoneGroup.c) {
                return 1;
            }

            check(stoneGroup.a, stoneGroup.b, stoneGroup.c, queue, visited);
            check(stoneGroup.a, stoneGroup.c, stoneGroup.b, queue, visited);
            check(stoneGroup.b, stoneGroup.c, stoneGroup.a, queue, visited);
        }
        return 0;
    }

    private static void check(int x, int y, int remainder, ArrayDeque<Stone> queue, boolean[][] visited) {
        if (x != y) {
            int nx = (x < y) ? x * 2 : x - y;
            int ny = (x < y) ? y - x : y * 2;

            if (nx + ny + remainder == sum && !visited[nx][ny]) {
                visited[nx][ny] = true;
                queue.add(new Stone(nx, ny, remainder));
            }
        }
    }
}

//오늘 강호는 돌을 이용해 재미있는 게임을 하려고 한다. 먼저, 돌은 세 개의 그룹으로 나누어져 있으며 각각의 그룹에는 돌이 A, B, C개가 있다.
// 강호는 모든 그룹에 있는 돌의 개수를 같게 만들려고 한다.
//
//강호는 돌을 단계별로 움직이며, 각 단계는 다음과 같이 이루어져 있다.
//
//크기가 같지 않은 두 그룹을 고른다. 그 다음, 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y라고 정한다.
// 그 다음, X에 있는 돌의 개수를 X+X개로, Y에 있는 돌의 개수를 Y-X개로 만든다.
//
//A, B, C가 주어졌을 때, 강호가 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력하는 프로그램을 작성하시오.

//입력
//첫째 줄에 A, B, C가 주어진다. (1 ≤ A, B, C ≤ 500)

//출력
//돌을 같은 개수로 만들 수 있으면 1을, 아니면 0을 출력한다.

//10 15 35
//1

//1 1 2
//0