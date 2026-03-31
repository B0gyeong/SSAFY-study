import java.io.*;
import java.util.*;

public class B20056_마법사상어와파이어볼 {
  static int N, M, K;
  static List<Fireball> fireballs = new ArrayList<>();
  static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

  static class Fireball {
    int r, c, m, s, d;
    Fireball(int r, int c, int m, int s, int d) {
      this.r = r; this.c = c; this.m = m; this.s = s; this.d = d;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      fireballs.add(new Fireball(r, c, m, s, d));
    }

    for (int time = 0; time < K; time++) {
      if (fireballs.isEmpty()) break;
      move();
    }

    System.out.println(sumM());
  }

  static void move() {
      List<Fireball>[][] nextBoard = new ArrayList[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) nextBoard[i][j] = new ArrayList<>();
      }

      for (Fireball fb : fireballs) {
        // (fb.s % N)은 속력이 아주 클 때 불필요한 연산을 줄여줌
        int nr = (fb.r + dr[fb.d] * (fb.s % N) + N) % N;
        int nc = (fb.c + dc[fb.d] * (fb.s % N) + N) % N;
        nextBoard[nr][nc].add(new Fireball(nr, nc, fb.m, fb.s, fb.d));
      }

      List<Fireball> nextFireballs = new ArrayList<>();
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
          if (nextBoard[r][c].isEmpty()) continue;

          if (nextBoard[r][c].size() == 1) {
              nextFireballs.add(nextBoard[r][c].get(0));
              continue;
          }

          int mSum = 0, sSum = 0;
          int count = nextBoard[r][c].size();
          boolean even = true, odd = true;

          for (Fireball fb : nextBoard[r][c]) {
            mSum += fb.m;
            sSum += fb.s;
            if (fb.d % 2 == 0) odd = false;
            else even = false;
          }

          int nextM = mSum / 5;
          if (nextM == 0) continue;

          int nextS = sSum / count;
          // 모두 짝수이거나 모두 홀수면 0, 2, 4, 6 아니면 1, 3, 5, 7
          int[] nextDs = (even || odd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

          for (int d : nextDs) {
            nextFireballs.add(new Fireball(r, c, nextM, nextS, d));
          }
        }
      }
      fireballs = nextFireballs;
  }

  static int sumM() {
    int sum = 0;
    for (Fireball fb : fireballs) sum += fb.m;
    return sum;
  }
}