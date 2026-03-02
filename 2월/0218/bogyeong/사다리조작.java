import java.util.*;

public class Main {
	static int N, M, H, minCnt;
	static boolean checkFail;
	static boolean [][] matrix;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	H = sc.nextInt();
    	matrix = new boolean [H+1][N];
    	for(int i=0; i<M; i++) {
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		matrix[a][b] = true;
    	}
    	
    	checkFail = false;
    	minCnt = Integer.MAX_VALUE;
    	solve(0, 0);
    	if(minCnt == Integer.MAX_VALUE || minCnt > 3) System.out.println(-1);
    	else System.out.println(minCnt);
	}
    
	private static void solve(int idx, int colCnt) {
		if(colCnt > 3) {
			return;
		}
		
		if(idx == (H+1) * N) {
			if(check()) {
				minCnt = Math.min(minCnt, colCnt);
			}
			return;
		}
		
		int r = idx/N;
		int c = idx%N;
		if(r == 0 || c == 0 || matrix[r][c]) {
			solve(idx+1, colCnt);
			return;
		}
		
		matrix[r][c] = true;
	    solve(idx+1, colCnt+1);
	    matrix[r][c] = false;
	    solve(idx+1, colCnt);
		
	}

	private static boolean check() {
		for(int r=1; r<=H; r++) {
			for(int c=1; c<N-1; c++) {
				if(matrix[r][c] && matrix[r][c+1]) return false;
			}
		}
		
		for(int c=1; c<=N; c++) {
			int curr = c;
			for(int r=1; r<=H; r++) {
				if(curr < N && matrix[r][curr]) {
					curr = curr+1;
				} else if(1 < curr && matrix[r][curr-1]) {
					curr = curr-1;
				}
			}
			if (curr != c) return false;
		}
		return true;
	}
}