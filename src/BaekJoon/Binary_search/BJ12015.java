package BaekJoon.Binary_search;

import java.util.Scanner;

public class BJ12015 {
    static int size;
    static int[] A;

    static int[] list;
    static int length = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();

        A = new int[size];
        list = new int[size];

        for (int i = 0; i < size; i++) {
            A[i] = sc.nextInt();
        }

        list[0] = A[0];
        length = 1;

        for (int i = 1; i < size; i++) {
            if (list[length - 1] < A[i]) { //list 꼬리값보다 크면 바로 뒤에 추가
                list[length] = A[i];
                length++;
            } else { // list 꼬리값보다 작거나 같으면 bs로 적절한 위치에 추가 (최적의 list를 유지하기위해 들어가는 타겟 바로 다음값의 자리에 덮어씀)
                bs(A[i]);
            }
        }

        System.out.println(length);
    }

    private static void bs(int target) {
        int start = 0;
        int end = length - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (list[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        list[start] = target;
    }
}
//수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
//
//예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
// 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

//입력
//첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
//
//둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)

//출력
//첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

//6
//10 20 10 30 20 50

//4

