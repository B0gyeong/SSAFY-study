
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636_치즈 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시뮬레이션
        int hour = 0;
        int lastCheeseCount = 0;
        // 치즈(네트워크)가 모두 녹아 없어지는데 걸리는 시간, 모두 녹기 한시간전 남아있는 칸 개수
        while(true) {
            int count = countCheese(map); // 칸의 개수를 bfs로 카운트

            if(count==0) {
                break;
            }

            lastCheeseCount = count;


            updateMap(map); // 치즈의 겉면만 0으로 바꿈
            hour++;
        }

        System.out.println(hour);
        System.out.println(lastCheeseCount);

    }

    public static int countCheese(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        int count = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void updateMap(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        // boolean[][] toZero = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0||nr>=N||nc<0||nc>=M) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==1) {
                    map[nr][nc] = 0;
                    visited[nr][nc] = true;
                } else {
                    q.offer(new int[]{nr,nc});
                    visited[nr][nc] = true;
                }

            }
        }
    }
}