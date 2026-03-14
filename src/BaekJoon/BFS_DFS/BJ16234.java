package BaekJoon.BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BJ16234 {

    static int N; // N X N 크기
    static int L; // 인구 차이가 L명 이상
    static int R; // 인구 차이가 R명 이하
    static int[][] map;

    static boolean[][] visited;

    static final int[] dx = {-1, 1, 0, 0}; // 위 아 왼 오
    static final int[] dy = {0, 0, -1, 1};

    static int day = 0;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (movePopulation()) {
            day++;
        }

        System.out.println(day);
    }

    static class Country {
        int x, y;

        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean movePopulation() {
        visited = new boolean[N][N];
        boolean moveCheck = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && bfs(i, j)) {
                    moveCheck = true;
                }
            }
        }
        return moveCheck;
    }

    private static boolean bfs(int x, int y) {
        ArrayDeque<Country> queue = new ArrayDeque<>();
        ArrayList<Country> federationList = new ArrayList<>();

        queue.add(new Country(x, y));
        federationList.add(new Country(x, y));
        visited[x][y] = true;

        int totalPopulation = map[x][y];

        while (!queue.isEmpty()) {
            Country country = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = country.x + dx[i];
                int nextY = country.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && !visited[nextX][nextY]) {
                    int difference = Math.abs(map[country.x][country.y] - map[nextX][nextY]);

                    if (difference >= L && difference <= R) {
                        visited[nextX][nextY] = true;
                        queue.add(new Country(nextX, nextY));
                        federationList.add(new Country(nextX, nextY));
                        totalPopulation += map[nextX][nextY];
                    }
                }
            }
        }

        if (federationList.size() > 1) {
            int avg = totalPopulation / federationList.size();

            for (Country country : federationList) {
                map[country.x][country.y] = avg;
            }
            return true;
        }

        return false;
    }
}

//N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다.
// 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다.
// 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
//
//오늘부터 인구 이동이 시작되는 날이다.
//인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.

//국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
//위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
//국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
//연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
//연합을 해체하고, 모든 국경선을 닫는다.

//각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

//입력
//첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
//
//둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
//
//인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

//출력
//인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

//2 20 50
//50 30
//20 40

//1