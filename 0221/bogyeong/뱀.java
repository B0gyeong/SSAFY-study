import java.util.*;

public class Main {
	static int N, K, L;
	// 상(0), 하(1), 좌(2), 우(3)
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};

	// 방향 전환 정보를 담는 클래스
	static class Turn {
		int t;
		char d;
		Turn(int t, char d) {
			this.t = t; this.d = d;
		}
	}

	// 뱀의 몸통 좌표를 저장하는 클래스 (꼬리를 줄이기 위해 사용)
	static class Tails {
		int r, c;
		Tails(int r, int c) {
			this.r = r; this.c = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		boolean [][] snake = new boolean [N+1][N+1]; // 뱀이 현재 위치하고 있는지 확인용
		
		K = sc.nextInt();
		boolean [][] apples = new boolean [N+1][N+1];
		for(int i=0; i<K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			apples[r][c] = true; // 사과가 있는 곳은 true
		}
		
		L = sc.nextInt();
		Queue <Turn> turns = new LinkedList<>();
		for(int i=0; i<L; i++) {
			int X = sc.nextInt();
			char C = sc.next().charAt(0);
			turns.add(new Turn(X, C));
		}
		
		int nr = 1; int nc = 1;  // 뱀의 머리 위치 (1,1 시작)
		Queue <Tails> tails = new LinkedList<>(); // 뱀의 몸통이 위치한 좌표들을 순서대로 저장 (Queue의 poll은 꼬리가 됨)
		tails.add(new Tails(nr, nc));
		int d = 3; // 초기 방향: 오른쪽 
		snake[1][1] = true;
		int time = 0;
		while(true) {
			time++;

			// 머리를 다음 칸으로 이동
			nr = nr+dr[d];
			nc = nc+dc[d];
			
			if(nr<=0 || nr>N || nc<=0 || nc>N || snake[nr][nc]) break;

			// 이동한 칸에 뱀의 머리를 둠
			snake[nr][nc] = true;
			tails.add(new Tails(nr, nc));
			
			// 이동한 칸에 사과가 있는지 확인
			if(!apples[nr][nc]) {
				// 사과가 없으면: 꼬리를 당겨줌 (가장 먼저 들어왔던 좌표 제거)
				Tails tail = tails.poll();
				snake[tail.r][tail.c] = false;
			} else {
				// 사과가 있으면: 사과만 사라지고 꼬리는 그대로 둠 (몸길이 증가)
				apples[nr][nc] = false;
			}
			
			// 현재 시간이 방향을 바꿀 시간인지 확인
			if(!turns.isEmpty() && turns.peek().t == time) {
				Turn curr = turns.poll();
				d = move(curr.d , d);
			}
		}
		System.out.println(time);
	}
	
	private static int move(char d, int currd) {
		int reD = 0;
		if(d == 'L') {
			if(currd == 0) reD = 2;
			if(currd == 1) reD = 3;
			if(currd == 2) reD = 1;
			if(currd == 3) reD = 0;
		} else {
			if(currd == 0) reD = 3;
			if(currd == 1) reD = 2;
			if(currd == 2) reD = 0;
			if(currd == 3) reD = 1;
		}
		
		return reD;
	}

}
