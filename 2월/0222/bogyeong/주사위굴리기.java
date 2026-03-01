import java.util.*;

public class Main {
	static int N, M, x, y, K;
	static int [] orders, dices;
	static int [] dr = {0, 0, 0, -1, 1};
	static int [] dc = {0, 1, -1, 0, 0};
	static int [][] matrix;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); M = sc.nextInt();
		x = sc.nextInt(); y = sc.nextInt();
		K = sc.nextInt();
		
		matrix = new int [N][M];
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				matrix[r][c] = sc.nextInt();
			}
		}
		
		orders = new int [K];
		for(int i=0; i<K; i++) {
			orders[i] = sc.nextInt();
		}
		
		dices = new int [7];
		int nr = x; int nc = y;
		for(int i=0; i<K; i++) {
			if(nr + dr[orders[i]]<0 || nr + dr[orders[i]]>=N || nc + dc[orders[i]]<0 || nc + dc[orders[i]]>=M) continue;
			nr = nr + dr[orders[i]];
			nc = nc + dc[orders[i]];
			diceF(orders[i]);
			
			if(matrix[nr][nc] == 0) {
				matrix[nr][nc] = dices[6];
			} else {
				dices[6] = matrix[nr][nc];
				matrix[nr][nc] = 0;
			}
			
			System.out.println(dices[1]);
		}
	}
	private static void diceF(int i) {
		int temp = dices[1];
		if(i==1) {
			dices[1] = dices[4];
			dices[4] = dices[6];
			dices[6] = dices[3];
			dices[3] = temp;
		} else if (i==2) {
			dices[1] = dices[3];
			dices[3] = dices[6];
			dices[6] = dices[4];
			dices[4] = temp;
		} else if (i==3) {
			dices[1] = dices[5];
			dices[5] = dices[6];
			dices[6] = dices[2];
			dices[2] = temp;
		} else {
			dices[1] = dices[2];
			dices[2] = dices[6];
			dices[6] = dices[5];
			dices[5] = temp;
		}
		
	}
}
