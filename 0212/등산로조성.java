import java.util.*;

public class 등산로조성 {
	static int N;
	static int K;
	static int maxH;
	static int maxLenth;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static int [][] matrix;
	static boolean [][] visited;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
        	N = sc.nextInt();
        	K = sc.nextInt();
        	matrix = new int [N][N];
        	maxH = 0;
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			matrix[r][c] = sc.nextInt();
        			if(maxH < matrix[r][c]) maxH = matrix[r][c];
        		}
        	}
        	
        	maxLenth = Integer.MIN_VALUE;
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(matrix[r][c]==maxH) {
        				visited = new boolean[N][N];
        				visited[r][c]= true; 
        				solve(r, c, 1, false);
        			}
        		}
        	}
        	
        	System.out.println("#"+tc+" "+maxLenth);
        }
        
	}

	private static void solve(int r, int c, int dist, boolean down) {	
		if(dist > maxLenth) maxLenth = dist;
		
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[nr][nc]) continue;
			
			if(matrix[nr][nc] < matrix[r][c]) {
				visited[nr][nc] = true; 
				solve(nr, nc, dist+1, down);
				visited[nr][nc] = false;
			} else {
				if(matrix[nr][nc] - K < matrix[r][c] && !down) {
					int temp = matrix[nr][nc];
					matrix[nr][nc] = matrix[r][c]-1;
					visited[nr][nc] = true; 
					solve(nr, nc, dist+1, !down);
					matrix[nr][nc] = temp;
					visited[nr][nc] = false;
				}
			}
		}
	}	
}