import java.io.*;
import java.util.*;

public class 벌꿀채취 {
  static int N, M, C;
  static int maxCost;
  static int[][] board, costTable;
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      board = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 각 위치마다 얻을 수 있는 이익의 최대치를 저장한 테이블
      costTable = new int[N][N - M + 1];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j <= N - M; j++) {
          costTable[i][j] = getCost(i, j);
        }
      }

      int answer = 0;
      for (int i1 = 0; i1 < N; i1++) {
        for (int j1 = 0; j1 <= N - M; j1++) {
          int worker1 = costTable[i1][j1];

          for (int i2 = 0; i2 < N; i2++) {
            for (int j2 = 0; j2 <= N - M; j2++) {
              if (i1 == i2 && j2 < j1 + M) continue;
              int worker2 = costTable[i2][j2];

              answer = Math.max(answer, worker1 + worker2);
            }
          }
        }
      }

      System.out.println("#" + tc + " " + answer);
    }
  }

  static int getCost(int x, int y) {
    int[] selected = new int[M];

    for (int i = 0; i < M; i++) {
      selected[i] = board[x][y + i];
    }

    maxCost = 0;
    dfs(0, 0, 0, selected);
    
    return maxCost;
  }

  static void dfs(int depth, int honeySum, int powSum, int[] hive) {
    if (honeySum > C) return;

    if (depth == M) {
      maxCost = Math.max(maxCost, powSum);
      return;
    }

    // 벌꿀 채취 O
    int honey = hive[depth];
    dfs(depth + 1, honeySum + honey, powSum + (honey * honey), hive);

    // 벌꿀 채취 X
    dfs(depth + 1, honeySum, powSum, hive);
  }
}
