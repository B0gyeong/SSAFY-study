import java.io.*;
import java.util.*;

public class S2105_디저트카페 {
  static int N, startX, startY, maxCnt;
  static int[][] map;
  static boolean[] desserts = new boolean[101]; // 디저트 종류는 1 ~ 100
  static int[] dx = {1,1,-1,-1}, dy={1,-1,-1,1}; // 방향 순서: RD, LD, LU, RU

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      
      map = new int[N][N];
      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      maxCnt = -1; // 경로가 없으면 -1 출력

      for (int i = 0; i < N - 2; i++) {
        for (int j = 1; j < N - 1; j++) {
          startX = i;
          startY = j;
          Arrays.fill(desserts, false);
          dfs(i, j, 0, 0);
        }
      }
      
      System.out.println("#" + tc + " " + maxCnt);
    }
  }

  static void dfs(int x, int y, int d, int cnt) {
    if (d == 3 && x == startX && y == startY) {
      // 원점으로 돌아왔을 때 -> 디저트 최댓값 갱신
      maxCnt = Math.max(maxCnt, cnt);
      return;
    }

    // 분기 1) 직진, 분기 2) 다음 방향으로 꺾기
    for (int i = d; i <= d + 1 && i < 4; i++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      // 범위를 벗어나는지 확인
      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      // 이미 먹어 본 디저트인지 확인
      if (desserts[map[nx][ny]]) continue;

      desserts[map[nx][ny]] = true;
      dfs(nx, ny, i, cnt + 1);
      desserts[map[nx][ny]] = false;
    }
  }
}
