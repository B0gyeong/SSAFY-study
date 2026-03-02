import java.util.*;

public class 수영장 {
	static int minVal;
	static int[] money;
	static int[] months;
	static int[] tempMonths;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			money = new int[4]; // 입력
			months = new int[12];
			for (int i = 0; i < 4; i++) {
				money[i] = sc.nextInt();
			}
			for (int i = 0; i < 12; i++) {
				months[i] = sc.nextInt();
			}

			minVal = money[3];
			tempMonths = new int[12];
			perm(0);
			System.out.println("#" + tc + " " + minVal);
		}

	}

	private static void perm(int k) {
		if (k == 12) {
			calculate();
		} else {
			for (int i = 0; i < 3; i++) { // 1년은 따로 계산할거니까 3까지만 반복
				tempMonths[k] = i;
				perm(k + 1);
			}
		}
	}

	private static void calculate() {
		int sum = 0;
		int idx = 0;
		while (idx < 12) {
			if (tempMonths[idx] == 0) {
				sum += money[0] * months[idx];
				idx++;
			} else if (tempMonths[idx] == 1) {
				sum += money[1];
				idx++;
			} else {
				sum += money[2];
				idx += 3;
			}
		}
		if (minVal > sum)
			minVal = sum;
	}
}
