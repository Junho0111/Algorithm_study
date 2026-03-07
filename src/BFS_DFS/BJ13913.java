package BFS_DFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BJ13913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수빈 위치
        int k = sc.nextInt(); // 동생 위치

        bfs(n, k);
    }

    public static void bfs(int n, int k) {
        int[] time = new int[100_001];
        int[] parent = new int[100_001]; // 어디서 왔는지 기록
        Arrays.fill(time, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        time[n] = 0;

        int minTime = -1; // 초기값을 -1로 설정

        while (!queue.isEmpty()) {
            int x = queue.poll();

            if (x == k) {
                minTime = time[x];
                break;
            }

            int[] walkAndTp = {x - 1, x + 1, x * 2};
            for (int next : walkAndTp) {
                if (next >= 0 && next <= 100_000 &&  time[next] == -1) {
                    time[next] = time[x] + 1;
                    parent[next] = x; // 1초후 이동 위치에 => 어느 위치에서 왔는지
                    queue.add(next); // 위치 추가
                }
            }
        }

        System.out.println(minTime);

        ArrayDeque<Integer> path = new ArrayDeque<>();
        while (k != n) { // n은 시작 위치이므로 시작위치 부모는 없음 그래서 != 이거임
            path.addFirst(k); // 거꾸로 올라감 도착위치부터 위로 (addFirst를 해야 출력할때 편함)
            k = parent[k]; // 부모배열에 위치 넣으면 어디서 왔는지 값이 나오니 그거 다시 k에 갱신 이거 반복하면 path에 최적경로가 차곡차곡쌓임
        }
        path.addFirst(n); // 마지막으로 시작위치 추가

        for (Integer i : path) {
            System.out.print(i + " ");
        }
    }
}

// 수빈이는 동생과 숨바꼭질을 하고 있다.
// 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
// 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
// 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

//  입력
// 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

// 출력
// 첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
// 둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.

// >> 5 17
// 4
// 5 10 9 18 17
// ---------------
// >> 5 17
// 4
// 5 4 8 16 17

