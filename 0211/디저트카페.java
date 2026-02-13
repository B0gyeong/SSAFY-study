import java.util.*;

public class 디저트카페 {
	static int N;
	static boolean none;
	static int str; static int stc;
	static int [][] matrix;
	static int maxCnt;
	static List<Integer> select;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			matrix = new int [N][N];
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					matrix[r][c] = sc.nextInt();
				}
			}
			
			maxCnt = Integer.MIN_VALUE;
			none = true;
			
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					select = new ArrayList<>();
					str = r; 
					stc = c;
					solve(1, r, c, select);
				}
			}
			System.out.println("#"+ tc +" " + (none ? -1 : maxCnt));
		}
	}

	private static void solve(int state, int r, int c, List<Integer> select) {
		if(r<0||r>=N||c<0||c>=N) return;
		
		if(state == 4 && r==str && c==stc) {
			none = false;
			maxCnt = Math.max(maxCnt, select.size());
			return;
		}
		
		if(select.contains(matrix[r][c])) return;
		
		select.add(matrix[r][c]);
		
		if(state == 1) {
			solve(1, r+1, c+1, select);
			solve(2, r+1, c-1, select);
		} else if(state == 2) {
			solve(2, r+1, c-1, select);
			solve(3, r-1, c-1, select);
		} else if(state == 3) {
			solve(3, r-1, c-1, select);
			solve(4, r-1, c+1, select);
		} else {  // state == 4
			solve(4, r-1, c+1, select);
		}
		
		select.remove(select.size() - 1);
	}
}