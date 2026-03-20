import java.util.*;

class Solution {
    int n, m;
    int[][] gridMap; 
    int[][] statusGrid; // 각 칸의 현재 상태 (0:미방문, 1:가로통과, 2:세로통과, 3:완료)

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int[] opp = {1, 0, 3, 2}; 

    // 각 방향에서 진입했을 때 연결 가능한 파이프 종류
    int[][] hasOpening = {
        {2, 3, 4, 5}, 
        {2, 3, 6, 7}, 
        {1, 3, 4, 7}, 
        {1, 3, 5, 6} 
    };

    public long solution(int[][] grid) {
        this.n = grid.length;
        this.m = grid[0].length;
        this.gridMap = grid;
        this.statusGrid = new int[n][m];

        statusGrid[0][0] = 3;
        
        return dfs(0, 1, 2);
    }

    private long dfs(int r, int c, int inDir) {
        if (r < 0 || r >= n || c < 0 || c >= m || gridMap[r][c] == -1) return 0;

        if (r == n - 1 && c == m - 1) {
            if (!canEnter(gridMap[r][c], inDir)) return 0;
            
            statusGrid[r][c] = 3; 
            long result = isAllPipesUsed() ? 1 : 0;
            statusGrid[r][c] = 0; 
            return result;
        }

        long count = 0;
        int currentStatus = statusGrid[r][c];

        // 이미 파이프가 고정되어 있거나, 교차 파이프를 한 번 지났던 경우
        if (gridMap[r][c] > 0 || currentStatus > 0) {
            int type = (currentStatus > 0) ? 3 : gridMap[r][c];
            
            if (canEnter(type, inDir)) {
                int outDir = getOutDir(type, inDir);
                
                // 교차 파이프 특수 처리
                if (type == 3) {
                    if (currentStatus == 0) { 
                        statusGrid[r][c] = (inDir >= 2) ? 1 : 2; // 가로로 왔으면 1, 세로로 왔으면 2
                        count += dfs(r + dr[outDir], c + dc[outDir], opp[outDir]);
                        statusGrid[r][c] = 0; 
                    } 
                    else if ((currentStatus == 1 && inDir < 2) || (currentStatus == 2 && inDir >= 2)) {
                        statusGrid[r][c] = 3; 
                        count += dfs(r + dr[outDir], c + dc[outDir], opp[outDir]);
                        statusGrid[r][c] = currentStatus; 
                    }
                } else if (currentStatus == 0) { // 일반 파이프
                    statusGrid[r][c] = 3;
                    count += dfs(r + dr[outDir], c + dc[outDir], opp[outDir]);
                    statusGrid[r][c] = 0;
                }
            }
        } 
        // 빈 칸인 경우: 1~7번 파이프를 하나씩 놓아봄
        else {
            for (int type = 1; type <= 7; type++) {
                if (canEnter(type, inDir)) {
                    int outDir = getOutDir(type, inDir);
                    int nextStatus = (type == 3) ? (inDir >= 2 ? 1 : 2) : 3;
                    
                    statusGrid[r][c] = nextStatus; 
                    count += dfs(r + dr[outDir], c + dc[outDir], opp[outDir]);
                    statusGrid[r][c] = 0; 
                }
            }
        }

        return count;
    }

    // 진입 가능한 파이프인지 확인
    private boolean canEnter(int type, int inDir) {
        for (int t : hasOpening[inDir]) {
            if (t == type) return true;
        }
        return false;
    }

    // 파이프 모양에 따른 나가는 방향 계산
    private int getOutDir(int type, int inDir) {
        if (type == 1) return (inDir == 2) ? 3 : 2; 
        if (type == 2) return (inDir == 0) ? 1 : 0; 
        if (type == 3) return opp[inDir];           
        if (type == 4) return (inDir == 0) ? 2 : 0; 
        if (type == 5) return (inDir == 0) ? 3 : 0;
        if (type == 6) return (inDir == 1) ? 3 : 1;
        if (type == 7) return (inDir == 1) ? 2 : 1;
        return -1;
    }

    // 맵에 미리 배치된 모든 파이프를 다 사용했는지 확인
    private boolean isAllPipesUsed() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 문제에서 미리 주어진 파이프는 반드시 완료 상태여야 함
                if (gridMap[i][j] > 0 && statusGrid[i][j] != 3) return false;

                // 반만 연결된 채로 남아있으면 안 됨
                if (statusGrid[i][j] == 1 || statusGrid[i][j] == 2) return false;
            }
        }
        return true;
    }
}