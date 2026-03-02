import java.util.*;

public class Swimming {
	static int N;
	static int[][] sPool;
	static int fir;
	static int fic;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			sPool = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					sPool[r][c] = sc.nextInt();
				}
			}
			int str = sc.nextInt();
			int stc = sc.nextInt();
			fir = sc.nextInt();
			fic = sc.nextInt();

			int time = 0;
			time = BFS(str, stc);

			System.out.println("#" + tc + " " + time);
		}
	}

	static class Node {
		int r, c, t;

		Node(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	private static int BFS(int str, int stc) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(str, stc, 0));

		int[][] minTime = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(minTime[i], Integer.MAX_VALUE);
		}

		minTime[str][stc] = 0;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.t > minTime[curr.r][curr.c])
				continue;

			if (curr.r == fir && curr.c == fic)
				return curr.t;

			for (int d = 0; d < 4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];

				if (!isWall(nr, nc) && sPool[nr][nc] != 1) {
					int nextT;

					if (sPool[nr][nc] == 0) {
						nextT = curr.t + 1;
					} else {
						if (curr.t % 3 == 2) {
							nextT = curr.t + 1;
						} else if (curr.t % 3 == 1) {
							nextT = curr.t + 2;
						} else {
							nextT = curr.t + 3;
						}
					}
					if (nextT < minTime[nr][nc]) {
						minTime[nr][nc] = nextT;
						q.add(new Node(nr, nc, nextT));
					}
				}

			}
		}
		return -1;
	}

	private static boolean isWall(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;

	}
}
