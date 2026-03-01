import java.util.*;

public class Main {
	static int N, maxFavor, maxZero, minR, minC, ans;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	static int [][] favor;
	static int [][] map;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	favor = new int [N*N][5];
    	map = new int [N][N];
    	ans = 0;
    	
    	for(int r=0; r<N*N; r++) {
    		for(int c=0; c<5; c++) {
    			favor[r][c] = sc.nextInt();
    		}
    	}
    	
    	for(int i=0; i<N*N; i++) {
    		int num = favor[i][0];
    		maxFavor = -1;
    		maxZero = -1;
    		minR = N; minC = N;
    		for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			check(i,r,c);
        		}
        	}
    		map[minR][minC] = num;
    	}
    	
    	for(int i=0; i<N*N; i++) {
    		int favorCnt = 0;
    		for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(favor[i][0] == map[r][c]) {
        				for(int d=0; d<4; d++) {
        					int nr = r+dr[d];
        					int nc = c+dc[d];
        					if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
        					if(map[nr][nc] == favor[i][1] || map[nr][nc] == favor[i][2] || map[nr][nc] == favor[i][3] || map[nr][nc] == favor[i][4]) {
        						favorCnt++;
        					}
        				}
        			}
        		}
        	}
    		if(favorCnt==1) ans+=1;
    		if(favorCnt==2) ans+=10;
    		if(favorCnt==3) ans+=100;
    		if(favorCnt==4) ans+=1000;
    	}
    	
    	System.out.println(ans);
	}

	private static void check(int n, int r, int c) {
		if(map[r][c] != 0) return;
		int cntFavor = 0;
		int cntZero = 0;
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			if(map[nr][nc] == favor[n][1] || map[nr][nc] == favor[n][2] || map[nr][nc] == favor[n][3] || map[nr][nc] == favor[n][4]) {
				cntFavor++;
			}
			if(map[nr][nc]==0) cntZero++;
		}
		
		if(cntFavor > maxFavor || (cntFavor == maxFavor && cntZero > maxZero) ||
			(cntFavor == maxFavor && cntZero == maxZero && minR > r) ||
			(cntFavor == maxFavor && cntZero == maxZero && minR == r && minC > c) ) {
			maxFavor = cntFavor;
			maxZero = cntZero;
			minR = r;
			minC = c;
		}
	}
}