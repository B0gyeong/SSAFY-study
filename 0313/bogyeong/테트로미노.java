import java.io.*;
import java.util.*;

public class Main {
	static int N, M, sum;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static int [][] map;
	static boolean [][] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][M];
		for(int r=0; r<N; r++) {
			StringTokenizer line = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(line.nextToken());
			}
		}
		
		visited = new boolean [N][M];
		sum = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				visited[r][c] = true;
				dfs(r, c, 0, map[r][c]);
				visited[r][c] = false;

				checkT(r, c);
			}
		}
		
		System.out.println(sum);
	}
	
	private static void checkT(int r, int c) {
		// TODO Auto-generated method stub
		
		if(r<=N-3 && c<=M-2) {
			sum = Math.max(sum, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1]);
			sum = Math.max(sum, map[r][c+1] + map[r+1][c+1] + map[r+2][c+1] + map[r+1][c]);
		}
		
		if(r<=N-2 && c<=M-3) {
			sum = Math.max(sum, map[r][c+1] + map[r+1][c] + map[r+1][c+1] + map[r+1][c+2]);
			sum = Math.max(sum, map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1]);
		}
	}

	private static void dfs(int r, int c, int k, int sumF) {
		if(k == 3) {
			if (sumF > sum) sum = sumF;
			return;
		}
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0||nr>=N||nc<0||nc>=M||visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, k+1, sumF+map[nr][nc]);
			visited[nr][nc] = false;
			
		}
		
	}

}
			
			
