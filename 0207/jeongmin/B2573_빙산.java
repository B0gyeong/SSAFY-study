import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573_빙산 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        int N = map.length;
        int M = map[0].length;

        q.offer(new int[]{sr,sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr<0||nr>=N||nc<0||nc>=M) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==0) continue;

                q.offer(new int[]{nr,nc});
                visited[nr][nc] = true;
            }
        }
    }

    public static void updateMap(int[][] map) {
        int N = map.length;
        int M = map[0].length;
        int[][] zeroCount = new int[N][M];

        // 4방향 0 개수 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int count =0;
                for(int d=0; d<4; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    if(ni<0||ni>=N||nj<0||nj>=M) continue;
                    if(map[ni][nj] == 0) {
                        count++;
                    }
                }
                zeroCount[i][j] = count;
            }
        }

        // 0 개수만큼 -
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(zeroCount[i][j]!=0) {
                    if(map[i][j]-zeroCount[i][j]>=0) {
                        map[i][j] -= zeroCount[i][j];
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int year = 0;
        while(true) {
            count = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            if(count>=2) {
                System.out.println(year);
                break;
            }
            if(count==0) {
                System.out.println(0);
                break;
            }

            year++;
            updateMap(map);
        }


    }
}