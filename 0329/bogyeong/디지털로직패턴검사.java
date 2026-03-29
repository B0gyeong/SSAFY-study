import java.util.HashMap;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String S = scanner.next();

        int K = scanner.nextInt();
        int M = scanner.nextInt();
        
        // 해싱값을 카운트하는 해시맵
        HashMap<Long, Integer> cnt = new HashMap<>();
        
        // 첫 길이 K의 패턴을 해싱
        long p = 0;
        for (int i = 0; i < K; i++) {
            p = p * 2 + (S.charAt(i) - '0');
        }
        cnt.put(p, 1);

        // 전체 문자열을 순회하며 해싱값 갱신
        for (int i = K; i < S.length(); i++) {
            // 이전 패턴의 해싱값을 이용해 새로운 해싱값 계산
            p = p * 2 - (long) (S.charAt(i - K) - '0') * (1L << K) + (S.charAt(i) - '0');
            // 해싱된 패턴 개수 증가
            cnt.put(p, cnt.getOrDefault(p, 0) + 1);
        }

        boolean flag = false;
        for (int value : cnt.values()) {
            if (value >= M) {
                flag = true;
                break;
            }
        }

        System.out.println(flag ? 1 : 0);
        
        scanner.close();
    }
}
