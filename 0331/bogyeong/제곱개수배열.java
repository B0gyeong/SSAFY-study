import java.util.*;

class Solution {
    public long[] solution(int[] arr, long l, long r) {
        int n = arr.length;
        long[] countSum = new long[n + 1]; 
        long[] valueSum = new long[n + 1]; 

    for (int i = 0; i < n; i++) {
        countSum[i + 1] = countSum[i] + arr[i];
        valueSum[i + 1] = valueSum[i] + (long) arr[i] * arr[i];
    }

    long K = getSumRange(countSum, valueSum, arr, l - 1, r - 1);
    long W = r - l + 1; 
    long totalLen = countSum[n];

    TreeSet<Long> points = new TreeSet<>();
    for (int i = 0; i <= n; i++) {

        long pStart = countSum[i];
        if (pStart <= totalLen - W) points.add(pStart);

        long pEndBoundary = countSum[i] - W;
        if (pEndBoundary >= 0 && pEndBoundary <= totalLen - W) points.add(pEndBoundary);
    }

    points.add(totalLen - W);

    List<Long> cp = new ArrayList<>(points);
    long C = 0;

    for (int i = 0; i < cp.size() - 1; i++) {
        long start = cp.get(i);
        long next = cp.get(i + 1);
        long end = next - 1; 
        long sSum = getSumRange(countSum, valueSum, arr, start, start + W - 1);
        
        if (start == end) {
            if (sSum == K) C++;
        } else {
            long eSum = getSumRange(countSum, valueSum, arr, end, end + W - 1);
            if (sSum == eSum) {
                if (sSum == K) C += (end - start + 1);
            } else {
                long d = (eSum - sSum) / (end - start);
                if ((K - sSum) % d == 0) {
                    long targetIdx = (K - sSum) / d;
                    if (targetIdx >= 0 && targetIdx <= (end - start)) {
                        C++;
                    }
                }
            }
        }
    }

    long lastPos = cp.get(cp.size() - 1);
    if (getSumRange(countSum, valueSum, arr, lastPos, lastPos + W - 1) == K) {
        C++;
    }

    return new long[]{K, C};
	}
	
	private long getSumRange(long[] countSum, long[] valueSum, int[] arr, long l, long r) {
	    if (l > r) return 0;
	    return getSumUntil(countSum, valueSum, arr, r + 1) - getSumUntil(countSum, valueSum, arr, l);
	}
	
	private long getSumUntil(long[] countSum, long[] valueSum, int[] arr, long x) {
	    if (x <= 0) return 0;
	    int idx = Arrays.binarySearch(countSum, x);
	    if (idx < 0) idx = -(idx + 1);
	    if (idx == 0) return 0;
	
	    return valueSum[idx - 1] + (x - countSum[idx - 1]) * arr[idx - 1];
	}
}

