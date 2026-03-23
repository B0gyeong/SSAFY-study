import java.io.*;
import java.util.*;

public class Main {

    static int r;
    static int c;
    static int[][] A;
    static int k;
    static int rSize = 3, cSize = 3;

    static class Pair implements Comparable<Pair> {
        int num;
        int count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        // 등장 횟수로 정렬, 등장횟수가 같으면 숫자 오름차순 정렬
        @Override
        public int compareTo(Pair o) {
            if(this.count != o.count) return this.count - o.count;
            return this.num - o.num;
        }
    }

    static void operateR() {
        int maxCol = 0; // 연산 후 가장 길어진 열의 길이를 찾기 위함
        for (int i = 0; i < rSize; i++) {
            int[] freq = new int[101];
            for (int j = 0; j < cSize; j++) {
                if (A[i][j] == 0) continue;
                freq[A[i][j]]++;
            }

            Pair[] pairs = new Pair[101];
            int pairCount = 0;
            for (int n = 1; n <= 100; n++) {
                if (freq[n] > 0) pairs[pairCount++] = new Pair(n, freq[n]);
            }

            // 데이터가 있는 만큼만 정렬
            Arrays.sort(pairs, 0, pairCount);

            // 기존 행을 0으로 싹 비우기
            for (int j = 0; j < 100; j++) A[i][j] = 0;

            int idx = 0;
            for (int j = 0; j < pairCount; j++) {
                if (idx >= 100) break;
                A[i][idx++] = pairs[j].num;
                A[i][idx++] = pairs[j].count;
            }
            maxCol = Math.max(maxCol, idx); // 가장 긴 길이를 저장
        }
        cSize = maxCol; // 전체 열 크기 업데이트
    }

    static void operateC() {
        int maxRow = 0;
        for (int j = 0; j < cSize; j++) {
            int[] freq = new int[101];
            for (int i = 0; i < rSize; i++) {
                if (A[i][j] == 0) continue;
                freq[A[i][j]]++;
            }

            Pair[] pairs = new Pair[101];
            int pairCount = 0;
            for (int n = 1; n <= 100; n++) {
                if (freq[n] > 0) pairs[pairCount++] = new Pair(n, freq[n]);
            }

            Arrays.sort(pairs, 0, pairCount);

            // 기존 열을 0으로 싹 비우기
            for (int i = 0; i < 100; i++) A[i][j] = 0;

            int idx = 0;
            for (int i = 0; i < pairCount; i++) {
                if (idx >= 100) break;
                A[idx++][j] = pairs[i].num;   // 행 번호(idx)가 증가함
                A[idx++][j] = pairs[i].count;
            }
            maxRow = Math.max(maxRow, idx);
        }
        rSize = maxRow; // 전체 행 크기 업데이트
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        A = new int[100][100];

        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while(true) {

            // A[r-1][c-1] == k 면 break 후 time 출력
            if(A[r-1][c-1] == k) {
                System.out.println(time);
                break;
            }

            // 100초가 지나도 A[r-1][c-1] == k 가 되지 않으면 -1
            if (time == 100) {
                System.out.println(-1);
                break;
            }

            if (rSize >= cSize) {
                operateR();
            } else {
                operateC();
            }

            time++;

        }

    }

}