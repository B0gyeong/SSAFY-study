import java.util.*;
import java.io.*;

public class B1941_소문난칠공주 {
	
	static int[][] grid;
	static boolean[][] visited;
	static boolean[][] selGrid;
	static int cases;
	static int[][] sel;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[sr][sc] = true;
		q.offer(new int[] {sr,sc});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0||nr>=5||nc<0||nc>=5) continue;
				if(visited[nr][nc]) continue;
				if(!selGrid[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr,nc});
			}
		}
		
	}
	public static void dfs(int m, int start, int ySum) {
		// 7명 중 4명이 1이면 가지치기 하기
		if(ySum >= 4) return;
		
		if(m==7) {
			
			visited = new boolean[5][5];
			
			// 선택한 7칸만 bfs
			selGrid = new boolean[5][5];
			for(int i=0; i<7; i++) {
				selGrid[sel[i][0]][sel[i][1]] = true;
			}
			
			// bfs
			bfs(sel[0][0], sel[0][1]);
					
			// sel 0~6돌면서 visited 처리가 모두 됐으면 경우의수 + 1
			for(int i=1; i<7; i++) {
				int r = sel[i][0];
				int c = sel[i][1];
				
				if(!visited[r][c]) {
					return;
				}
			}
			
			cases++;
			return;
		}
		
		for(int i=start; i<25; i++) {
			
			int r = i/5;
			int c = i%5;
			
			sel[m][0] = r;
			sel[m][1] = c;
			
			if(grid[r][c] == 1) {
				dfs(m+1, i+1, ySum+1);
			} else {
				dfs(m+1, i+1, ySum);
			}
			
		}
	}
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		grid = new int[5][5];
		for(int i=0; i<5; i++) {
			String line = br.readLine();
			for(int j=0; j<5; j++) {
				if(line.charAt(j)=='Y') {
					grid[i][j] = 1;
				} else {
					grid[i][j] = 0;
				}
				
			}
		}
		
		sel = new int[7][2]; // 0:r 1:c
		cases = 0;
		dfs(0,0,0);
		System.out.println(cases);
	}

}
