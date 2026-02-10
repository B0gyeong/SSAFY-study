import java.io.*;
import java.util.*;

public class Q2636_치즈 {
  static int row; static int col;
  static int[][] board;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  static void bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{0, 0}); // 항상 바깥 공기 시작은 (0, 0) 
    visited = new boolean[row][col];
    visited[0][0] = true;

    while(!q.isEmpty()) {
      int[] curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = curr[0] + dx[i];
        int ny = curr[1] + dy[i];

        // 범위를 벗어나거나 이미 방문한 경우 continue
        if (nx < 0 || ny < 0 || nx >= row || ny >= col || visited[nx][ny]) continue;

        // 1. 바깥 공기를 만났을 때 -> 방문 처리, queue에 추가
        if (board[nx][ny] == 0) {
          visited[nx][ny] = true;
          q.offer(new int[]{nx, ny});
        }
        // 2. 치즈를 만났을 때 -> 방문 처리, 녹을 치즈 마킹
        else if (board[nx][ny] == 1) {
          visited[nx][ny] = true;
          board[nx][ny] = 2;
        }
      }
    }
  }

  static int melt() {
    int count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j] == 2) {
          board[i][j] = 0; // 치즈 녹음
          count++;
        }
      }
    }
    return count;
  }

  static public void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());
    board = new int[row][col];
    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < col; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int time = 0;
    int answer = 0;
    while (true) {
      bfs();
      int meltedCheese = melt();
      
      if (meltedCheese == 0) break;

      // 완전히 녹으면 바로 앞에서 break -> answer은 1시간 전 치즈 개수
      answer = meltedCheese;
      time++;
    }
    
    System.out.println(time);
    System.out.println(answer);
  }
  
}
