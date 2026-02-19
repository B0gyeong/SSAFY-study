import java.util.*;

public class 숫자만들기 {
	static int N;
	static int maxVal;
	static int minVal;
	static int[] ops;
	static int[] tempOpsNum;
	static int[] tempOps;
	static int[] nums;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			ops = new int[4];
			tempOpsNum = new int[4];
			tempOps = new int[N];
			for (int i = 0; i < 4; i++) {
				ops[i] = sc.nextInt();
			}
			nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			maxVal = Integer.MIN_VALUE;
			minVal = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#" + tc + " " + (maxVal - minVal));
		}
	}

	private static void perm(int k) {
		if (k == N - 1) {
			calculate();
		} else {
			for (int i = 0; i < 4; i++) {
				tempOpsNum[i]++;
				tempOps[k] = i;
				perm(k + 1);
				tempOpsNum[i]--;
			}
		}
	}

	private static void calculate() {
		for (int i = 0; i < 4; i++) {
			if (tempOpsNum[i] > ops[i])
				return;
		}

		int curSum = nums[0];
		for (int i = 0; i < N - 1; i++) {
			curSum = opsCalculate(curSum, tempOps[i], nums[i + 1]);
		}
		if (curSum > maxVal)
			maxVal = curSum;
		if (curSum < minVal)
			minVal = curSum;
	}

	private static int opsCalculate(int a, int o, int b) {
		if (o == 0)
			return a + b;
		if (o == 1)
			return a - b;
		if (o == 2)
			return a * b;
		return a / b;
	}
}
