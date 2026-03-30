// https://www.codetree.ai/ko/frequent-problems/hsat/problems/check-digital-logic-pattern/description

import java.io.*;
import java.util.*;

public class CheckDigitalLogicPattern {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 디지털 로직 입력 (문자열)
        String logic = br.readLine();
        
        // 2. K(패턴 길이)와 M(반복 횟수) 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int N = logic.length();
        
        // 패턴 길이가 전체 로직보다 길면 반복될 수 없으므로 STABLE(0)
        if (K > N) {
            System.out.println(0);
            return;
        }

        // 3. 롤링 해시 설정 (메모리 최적화의 핵심)
        long hash = 0;
        long base = 3;             // 0, 1 데이터용 베이스
        long mod = 1_000_000_007;  // 해시 충돌 방지용 소수
        long power = 1;            // base^(K-1)

        // 첫 번째 윈도우(0 ~ K-1) 해시값 계산
        for (int i = 0; i < K; i++) {
            hash = (hash * base + (logic.charAt(i) - '0' + 1)) % mod;
            if (i > 0) power = (power * base) % mod;
        }

        // 해시값별 등장 횟수를 저장할 맵
        Map<Long, Integer> counts = new HashMap<>();
        counts.put(hash, 1);
        
        // 만약 기준 M이 1이라면 시작하자마자 UNSTABLE(1)
        if (M <= 1) {
            System.out.println(1);
            return;
        }

        int result = 0; // 기본값 STABLE(0)

        // 4. 슬라이딩 윈도우로 한 칸씩 밀며 검사
        for (int i = K; i < N; i++) {
            // 맨 앞 글자 제거 연산
            long front = ((logic.charAt(i - K) - '0' + 1) * power) % mod;
            hash = (hash - front + mod) % mod;
            
            // 새 글자 추가 연산
            hash = (hash * base + (logic.charAt(i) - '0' + 1)) % mod;

            // 해당 해시값이 몇 번 나왔는지 카운트
            int count = counts.getOrDefault(hash, 0) + 1;
            
            if (count >= M) {
                result = 1; // UNSTABLE 발견
                break;
            }
            counts.put(hash, count);
        }

        // 5. 최종 결과 출력 (0 또는 1)
        System.out.println(result);
    }
}