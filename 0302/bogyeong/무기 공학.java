import java.util.*;

public class Main {	
	static int N, M, maxSum;
	static int [][] matrix;
	static boolean [][] visited;
	
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		matrix = new int [N][M];
		visited = new boolean [N][M];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				matrix[r][c] = sc.nextInt();
			}
		}
		
		maxSum = Integer.MIN_VALUE;
        solve(0,0);
        System.out.println(maxSum);
	}

	private static void solve(int idx, int sum) {
		if(idx == N*M) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(check(idx, i)) {
				int s = selected(idx, i);
				solve(idx+1, sum+s);
				unselected(idx, i);
			}
		}
		
		solve(idx+1, sum);
	}

	private static void unselected(int idx, int s) {
		int cr = idx / M;
		int cc = idx % M;
		
		visited[cr][cc] = false;
		
		if(s == 0) {
			visited[cr][cc-1]= false;
			visited[cr+1][cc]= false;
		} else if (s==1) {
			visited[cr][cc-1] = false;
			visited[cr-1][cc] = false;
		} else if (s==2) {
			visited[cr][cc+1] = false;
			visited[cr-1][cc] = false;
		} else {
			visited[cr][cc+1] = false;
			visited[cr+1][cc] = false;
		}
	}

	private static int selected(int idx, int s) {
		int cr = idx / M;
		int cc = idx % M;
		
		visited[cr][cc] = true;
		int sum = 2 * matrix[cr][cc];
		
		if(s == 0) {
			visited[cr][cc-1]= true;
			sum+= matrix[cr][cc-1];
			visited[cr+1][cc]= true;
			sum+= matrix[cr+1][cc];
		} else if (s==1) {
			visited[cr][cc-1] = true;
			sum+= matrix[cr][cc-1];
			visited[cr-1][cc] = true;
			sum+= matrix[cr-1][cc];
		} else if (s==2) {
			visited[cr][cc+1] = true;
			sum+= matrix[cr][cc+1];
			visited[cr-1][cc] = true;
			sum+= matrix[cr-1][cc];
		} else {
			visited[cr][cc+1] = true;
			sum+= matrix[cr][cc+1];
			visited[cr+1][cc] = true;
			sum+= matrix[cr+1][cc];
		}
		
		return sum;
	}

	private static boolean check(int idx, int s) {
		int cr = idx / M;
		int cc = idx % M;
		
		if(visited[cr][cc]) return false;
		
		if(s == 0) {
			if(isWall(cr, cc-1) || visited[cr][cc-1]) return false;
			if(isWall(cr+1, cc) || visited[cr+1][cc]) return false;
		} else if (s==1) {
			if(isWall(cr, cc-1) || visited[cr][cc-1]) return false;
			if(isWall(cr-1, cc) || visited[cr-1][cc]) return false;
		} else if (s==2) {
			if(isWall(cr, cc+1) || visited[cr][cc+1]) return false;
			if(isWall(cr-1, cc) || visited[cr-1][cc]) return false;
		} else {
			if(isWall(cr, cc+1) || visited[cr][cc+1]) return false;
			if(isWall(cr+1, cc) || visited[cr+1][cc]) return false;
		}
		
		return true;
	}

	private static boolean isWall(int r, int c) {
		return 0>r || r>=N || c<0 || c>=M;
	}
}