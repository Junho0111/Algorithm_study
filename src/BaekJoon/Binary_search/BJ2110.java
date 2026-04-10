package BaekJoon.Binary_search;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2110 {
    static int N; // 집의 개수
    static int C; // 공유기 개수
    static int[] houses; // 공유기 설치 하실 댁~?

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = sc.nextInt();
        }

        Arrays.sort(houses);

        bs();
    }

    private static void bs() {
        int start = 1; // 최소 거리
        int end = houses[N - 1] - houses[0]; // 최대 거리
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 1; // 제일 처음집을 기준으로 무조건 하나는 설치
            int installHouse = houses[0];

            for (int i = 1; i < N; i++) { //공유기 사이 거리(mid)보다 더 크면 바로 설치하고 이제 그집이랑 다음집이랑 또 계속 비교 반복
                if (houses[i] - installHouse >= mid) {
                    count++;
                    installHouse = houses[i]; // 설치했으므로 기준점 갱신
                }
            }

            if (count >= C) { // 다 하고나서 설치된 공유기 수가 C보다 크그나 같으면 일단 결과에 넣어두고 거리를 늘려봄
                result = mid;
                start = mid + 1;
            } else { // C보다 설치된 공유기 수가 적으면 너무 거리가 먼거임 거리를 줄여봄
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}

//도현이의 집 N개가 수직선 위에 있다. 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
//
//도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
// 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에,
// 한 집에는 공유기를 하나만 설치할 수 있고, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
//
//C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.

//입력
//첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
// 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가 한 줄에 하나씩 주어진다.

//출력
//첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.

//5 3
//1
//2
//8
//4
//9

//3