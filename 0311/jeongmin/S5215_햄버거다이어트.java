import java.util.*;
import java.io.*;

public class S5215_햄버거다이어트 {

	static int N;
	static int L;

	static class Node {
		int grade;
		int cal;

		public Node(int grade, int cal) {
			this.grade = grade;
			this.cal = cal;
		}
	}

	static Node[] ingred;
	static int maxGrade;

	public static void solve(int idx, int curG, int curC) {
		if (curC > L)
			return;

		if (idx == N) {
			maxGrade = Math.max(curG, maxGrade);
			return;
		}

		solve(idx + 1, curG + ingred[idx].grade, curC + ingred[idx].cal);

		solve(idx + 1, curG, curC);
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			ingred = new Node[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int gra = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());

				ingred[i] = new Node(gra, cal);

			}

			maxGrade = Integer.MIN_VALUE;
			solve(0, 0, 0);

			System.out.printf("#%d %d", t, maxGrade);

		}

	}
}
