import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2573_빙산 {

    // 상하좌우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

//    static int[][] map;
//    static boolean[][] visited;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited;

        // map 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while(true) {
            int count = 0;
            visited = new boolean[N][M];

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j]!=0&&!visited[i][j]) {
                        bfs(map, visited, i, j);
                        count++;
                    }
                }
            }

            if(count>=2) {
                System.out.println(year);
                break;
            }

            // 빙산이 다 녹을때까지 분리되지 않으면 0을 출력
            if(count==0) {
                System.out.println(0);
                break;
            }

            updateMap(map);
            year++;
        }

    }

    public static void bfs(int[][] map, boolean[][] visited, int sr, int sc) {
        int N = map.length;
        int M = map[0].length;
        Queue<int[]> q = new ArrayDeque<>();

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
        // 맵의 각 칸마다 0 개수 구하기
        int N = map.length;
        int M = map[0].length;
        int[][] countZero = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                countZero[i][j] = 0;
                for(int d=0; d<4; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    if(ni<0||ni>=N||nj<0||nj>=M) continue;
                    if(map[ni][nj]==0) {
                        countZero[i][j]++;
                    }
                }
            }
        }

        // 0 개수 만큼 줄이기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(countZero[i][j]!=0) {
                    if(map[i][j]>countZero[i][j]) {
                        map[i][j] -= countZero[i][j];
                    } else {
                        map[i][j] = 0;
                    }
                }
            }
        }

    }
}