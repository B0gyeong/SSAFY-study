import java.util.*;

public class 홈방범서비스 {
	static int N;
	static int [][] matrix;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T; tc++) {
        	N = sc.nextInt();
        	int M = sc.nextInt();
        	matrix = new int [N][N];
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			matrix[r][c] = sc.nextInt();
        		}
        	}
        	
        	int maxCnt = -1;
        	if(M-1 >= 0) maxCnt = 1;
        	for(int k=2; k<=N+1; k++) {
        		for(int i=0; i<N*N; i++) {
        			int cnt = count(k-1,i);
        			int money = cnt*M - (k*k + (k-1)*(k-1));
        			if(0 <= money && maxCnt < cnt) {
        				maxCnt = cnt;
        			}
        		}
        	}
        	System.out.println("#"+tc+" "+maxCnt);
        }
        
	}

	private static int count(int k, int i) {
		// k, i일때 집이 얼마나 있는지를 반환하는 함수
		int cnt = 0;
		int r = i/N;
		int c = i%N;
		for (int rI=-k; rI<=k; rI++) {
			int range = k - Math.abs(rI);
			for (int cI=-range; cI<=range; cI++) {
				int nr = r+rI;
				int nc = c+cI;
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				if(matrix[nr][nc] == 1) cnt++;
			}
		}
		return cnt;
	}
}
