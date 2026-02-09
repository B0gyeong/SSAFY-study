import java.util.*;

public class Cheese {
	static boolean [][] airMatirx;
	static int [][] cheese;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	static int N;
	static int M;
	static boolean finish;
	static int finalCnt;
	static class Node {
		int r, c;
		Node(int r, int c) {
			this.r = r; this.c = c;
		}
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	cheese = new int [N][M];
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<M; c++) {
    			cheese[r][c] = sc.nextInt();
    		}
    	}
    	
    	finalCnt = 0;
    	int repeatNum = 0;
    	finish = false;
    	while(!finish) {
    		repeatNum++;
    		airMatirx = isAir(0,0);
        	melt();
    	}
    	System.out.println(repeatNum-1);
    	System.out.println(finalCnt);
    }

	private static void melt() {
		finish = true;
		int cnt = 0;
		for(int r=0; r<N; r++) {
    		for(int c=0; c<M; c++) {
    			if(cheese[r][c]==1) {
    				finish = false;
    				for(int d=0; d<4; d++) {
    					int nr = r + dr[d];
    					int nc = c + dc[d];
    					if(!isWall(nr, nc) && airMatirx[nr][nc]) {
    						cheese[r][c] = 0;
    						cnt++;
    						break;
    					}
    				}
    			}
    		}
    	}
		if(!finish) {
			finalCnt = cnt;
		}
	}

	private static boolean[][] isAir(int r, int c) {
		boolean [][] visited = new boolean[N][M];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r,c));
		visited[r][c] = true;
		while(!q.isEmpty()) {
			Node curr = q.poll();
			for(int d=0; d<4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if( !isWall(nr, nc) && !visited[nr][nc] && cheese[nr][nc]==0) {
					q.add(new Node(nr,nc));
					visited[nr][nc] = true;
				}
			}
			
		}
		return visited;
	}

	private static boolean isWall(int nr, int nc) {
		return 0 > nr || nr >= N || 0 > nc || nc >= M;
	}
}