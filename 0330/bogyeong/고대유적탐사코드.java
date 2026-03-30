import java.io.*;
import java.util.*;

public class Solution{
    static int K, M;
    static int[] wallNum;
    static int wallIdx = 0; 
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;

    static class Rotate implements Comparable<Rotate> {
        int value, degree, r, c;

        Rotate(int value, int degree, int r, int c) {
            this.value = value;
            this.degree = degree;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Rotate o) {
            if (this.value != o.value) return o.value - this.value;
            if (this.degree != o.degree) return this.degree - o.degree;
            if (this.c != o.c) return this.c - o.c;
            return this.r - o.r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[5][5];
        for (int r = 0; r < 5; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 5; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        wallNum = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            wallNum[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < K; k++) {
            int totalTurnScore = 0;
            Rotate best = null;

            for (int d = 1; d <= 3; d++) { 
                for (int c = 1; c <= 3; c++) { 
                    for (int r = 1; r <= 3; r++) { 
                        int[][] trialMap = rotate(map, r, c, d);
                        int score = checkValue(trialMap);

                        if (score > 0) {
                            Rotate current = new Rotate(score, d, r, c);
                            if (best == null || current.compareTo(best) < 0) {
                                best = current;
                            }
                        }
                    }
                }
            }

            if (best == null) break;

            map = rotate(map, best.r, best.c, best.degree);
            while (true) {
                int score = clearAndFill();
                if (score == 0) break; 
                totalTurnScore += score;
            }

            sb.append(totalTurnScore).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static int[][] rotate(int[][] origin, int row, int col, int degree) {
        int[][] copy = new int[5][5];
        for (int i = 0; i < 5; i++) copy[i] = origin[i].clone();

        for (int d = 0; d < degree; d++) {
            int[][] tmp = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tmp[j][2 - i] = copy[row - 1 + i][col - 1 + j];
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    copy[row - 1 + i][col - 1 + j] = tmp[i][j];
                }
            }
        }
        return copy;
    }

    private static int checkValue(int[][] targetMap) {
        boolean[][] visited = new boolean[5][5];
        int score = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    score += bfs(targetMap, visited, i, j, false);
                }
            }
        }
        return score;
    }

    private static int clearAndFill() {
        boolean[][] visited = new boolean[5][5];
        boolean[][] toRemove = new boolean[5][5];
        int totalScore = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    int count = bfs(map, visited, i, j, false);
                    if (count >= 3) {
                        totalScore += count;
                        // 다시 BFS를 돌아 지워질 위치를 표시 (toRemove)
                        bfs(map, new boolean[5][5], i, j, true, toRemove);
                    }
                }
            }
        }

        if (totalScore > 0) {
            for (int c = 0; c < 5; c++) {
                for (int r = 4; r >= 0; r--) {
                    if (toRemove[r][c]) {
                        map[r][c] = wallNum[wallIdx++];
                    }
                }
            }
        }
        return totalScore;
    }

    private static int bfs(int[][] targetMap, boolean[][] visited, int r, int c, boolean markOnly) {
        return bfs(targetMap, visited, r, c, markOnly, new boolean[5][5]);
    }

    private static int bfs(int[][] targetMap, boolean[][] visited, int r, int c, boolean markOnly, boolean[][] toRemove) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> path = new ArrayList<>();
        int targetValue = targetMap[r][c];

        q.add(new int[]{r, c});
        visited[r][c] = true;
        path.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && !visited[nr][nc] && targetMap[nr][nc] == targetValue) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    path.add(new int[]{nr, nc});
                }
            }
        }

        if (markOnly) {
            for (int[] p : path) toRemove[p[0]][p[1]] = true;
        }
        return path.size() >= 3 ? path.size() : 0;
    }
}