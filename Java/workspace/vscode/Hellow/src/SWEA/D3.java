import java.util.*;

public class D3 {
	public void D3_14() {
		// 13732. 정사각형 판정

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			String result = "no";
			int n = sc.nextInt();
			char[][] chSQ = new char[n][n];

			int a = 0, b = 0;
			int shepCount = 0; // # 개수
			boolean start = false;

			// 입력값 저장
			for (int i = 0; i < n; i++) {
				String str = sc.next();
				for (int j = 0; j < n; j++) {
					char ch = str.charAt(j);
					chSQ[i][j] = ch;
					if (ch == '#') {
						if (!start) {
							a = i;
							b = j;
						}
						shepCount++;
					}
				}
			}

			int len = (int) Math.sqrt(shepCount) - 1;
			if (a + len < n && b + len < n) {
				for (int i = a; i <= a + len; i++) {
					for (int j = b; j <= b + len; j++) {
						if (chSQ[i][j] != '#') {
							break;
						} else {
							shepCount--;
						}
					}
				}
			}

			if (shepCount == 0) {
				result = "yes";
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_13() {
		// 6190. 정곤이의 단조 증가하는 수
		/*
		 * 단조 증가하는 수 조건 : 숫자가 오름차순 형태여야 한다. ex)111566, 233359
		 * 
		 * 1. 주어진 숫자 중 2개를 골라 곱하기
		 * 2. 곱한 값이 단조인지 확인
		 * 
		 * 결과값
		 * 모든 곱이 단조가 아니면 -1 출력
		 * 있다면 가장 큰 단조 출력
		 */

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			PriorityQueue<Integer> pqNum = new PriorityQueue<>((o1, o2) -> o2 - o1);

			int result = 0;
			int n = sc.nextInt(); // 테스트 케이스 번호
			int[] arr = new int[n];

			// 입력값 저장
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			int i = 0;
			while (i < n) {
				int nowNum = arr[i];
				i++;

				for (int a = i; a < n; a++) {
					boolean isDanjo = true;
					int checkNum = nowNum * arr[a];
					char[] charNum = (checkNum + "").toCharArray();
					for (int j = 0; j < charNum.length - 1; j++) {
						if (charNum[j] > charNum[j + 1]) {
							// 단조 아님
							isDanjo = false;
							break;
						}
					}
					// 단조 맞을때만 곱 저장
					if (isDanjo)
						pqNum.add(checkNum);
				}
			}

			if (!pqNum.isEmpty())
				result = pqNum.peek();
			else
				result = -1;

			System.out.println("#" + tc + " " + result);
		}
	}

	public int D3_12(int[][] land) {
		// 땅따먹기
		int row = land.length; // 행 개수
		int[][] dp = new int[row + 1][4];
		int max = 0; // 각 행의 최댓값

		dp[0] = land[0];
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < 4; j++) {
				for (int n = 0; n < 4; n++) {
					if (j != n) {
						// j와 동일한 인덱스 외 다른 인덱스와의 합 중 최댓값 구하기
						max = Math.max(max, land[i][j] + dp[i - 1][n]);
					}
				}
				// dp[i][j]는 j와 동일한 인덱스 번호 이외의 최대합을 가지고 있음.
				dp[i][j] = max;
				max = 0;
			}
		}

		return dp[row - 1][3];
	}

	public void D3_11() {
		// 1209. [S/W 문제해결 기본] 2일차 - Sum

		Scanner sc = new Scanner(System.in);
		int t = 10;

		for (int tc = 1; tc < 1 + t; tc++) {
			int n = sc.nextInt(); // 테스트 케이스 번호
			int num = 100;
			int[][] numArr = new int[num][num];
			// 입력값 저장
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					numArr[i][j] = sc.nextInt();
				}
			}

			int[] sum = new int[4]; // 행, 열, 왼쪽 대각선, 오른쪽 대각선
			int[] maxSum = new int[4]; // 행, 열, 왼쪽 대각선, 오른쪽 대각선

			// 행, 열 최대값
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					sum[0] += numArr[i][j]; // 행
					sum[1] += numArr[j][i]; // 열
					sum[2] += numArr[j][j]; // 왼쪽 대각선
					sum[3] += numArr[j][num - 1 - j]; // 오른쪽 대각선
				}
				maxSum[0] = Math.max(maxSum[0], sum[0]);
				maxSum[1] = Math.max(maxSum[1], sum[1]);
				maxSum[2] = Math.max(maxSum[2], sum[2]);
				maxSum[3] = Math.max(maxSum[3], sum[3]);

				sum[0] = 0;
				sum[1] = 0;
				sum[2] = 0;
				sum[3] = 0;
			}
			Arrays.sort(maxSum);
			System.out.println("#" + tc + " " + maxSum[3]);
		}
	}

	public void D3_10() {
		// 2805. 농작물 수확하기

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int n = sc.nextInt();
			int[][] numArr = new int[n][n];
			// 입력값 저장
			for (int i = 0; i < n; i++) {
				String str = sc.next();
				char[] strToChar = str.toCharArray();
				for (int j = 0; j < n; j++) {
					int num = Character.getNumericValue(strToChar[j]);
					numArr[i][j] = num;
				}
			}

			int center = n / 2; // 가운데 숫자
			int a = 0; // 최고점 찍은 후 사용

			for (int i = 0; i < n; i++) {
				if (i > center) {
					a--;
				} else {
					a = i;
				}

				for (int j = a; j > 0; j--) {
					result += numArr[i][center - j];
					result += numArr[i][center + j];
				}
				result += numArr[i][center];
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_9() {
		// 1208. [S/W 문제해결 기본] 1일차 - Flatten

		/*
		 * 덤프 : 가장 높은 곳에서 가장 낮은 곳으로 이동 하는 것
		 */

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int count = sc.nextInt();
			int[] boxHigh = new int[100];
			for (int i = 0; i < 100; i++) {
				boxHigh[i] = sc.nextInt();
			}

			for (int i = 0; i < count; i++) {
				Arrays.sort(boxHigh);
				boxHigh[0]++;
				boxHigh[99]--;
			}
			Arrays.sort(boxHigh);
			result = boxHigh[99] - boxHigh[0];

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_8() {
		// 1206. [S/W 문제해결 기본] 1일차 - View

		/*
		 * 0,1,8,9는 0
		 * 따라서 for문은 2~7까지
		 * 1. 조건
		 * >> ( [i] > [i-1] && [i] > [i-2] ) && ([i] > [i+1] && [i] > [i+2] )
		 * 
		 * 2. [i-2 ~ i + 2] 이 중에 가장 큰수 구하기
		 * 3. 건물 += [i] - 가장 큰 수
		 */

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int count = sc.nextInt();
			int[] building = new int[count];
			for (int i = 0; i < count; i++) {
				building[i] = sc.nextInt();
			}

			for (int i = 2; i < count - 2; i++) {
				if (building[i] > building[i - 1] && building[i] > building[i - 2]) {
					if (building[i] > building[i + 1] && building[i] > building[i + 2]) {
						int[] findMax = {
								building[i - 1],
								building[i - 2],
								building[i + 1],
								building[i + 2]
						};
						Arrays.sort(findMax);

						result += building[i] - findMax[3];
					}
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_7() {
		// 20551. 증가하는 사탕 수열
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int[] box = new int[3];
			for (int i = 0; i < 3; i++) {
				box[i] = sc.nextInt();
			}

			/*
			 * 1. 각 상자의 크기 : [0] < [1] < [2]
			 * 2. 결과 값
			 * - -1 : 불가능한 경우 >> n < 2 < 1 , n < 1 < 2
			 * - 0 : 이미 순열 완성
			 * - 1 이상 : 먹어야 하는 사탕의 개수
			 */

			if (box[1] >= 2 && box[2] >= 3) {
				// 최소 사탕 개수 && 이미 완성
				if (box[1] >= box[2]) {
					result += box[1] - box[2] + 1;
					box[1] = box[2] - 1;
				}

				if (box[0] >= box[1]) {
					result += box[0] - box[1] + 1;
					box[0] = box[1] - 1;
				}
			} else {
				// 불가능한 경우
				result = -1;
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_6() {
		// 14178. 1차원 정원
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int flower = sc.nextInt();
			int d = sc.nextInt();

			int range = d * 2 + 1; // 분무기 1개로 물을 뿌릴수 있는 꽃의 개수
			result = flower / range;
			if (flower % range != 0) {
				result++;
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public String removeNum(String strNum) {
		String pw = "";
		char[] charNum = strNum.toCharArray();
		Queue<Integer> numQueue = new LinkedList<>();

		for (char c : charNum) {
			numQueue.add(Character.getNumericValue(c));
		}

		while (numQueue.size() >= 2) {
			int a = numQueue.poll();
			int b = numQueue.peek();

			if (a == b) {
				numQueue.poll();
			} else {
				pw += a + "";
			}
		}

		while (!numQueue.isEmpty()) {
			pw += numQueue.poll() + "";
		}

		return pw;
	}

	public void D3_5() {
		// 1234. [S/W 문제해결 기본] 10일차 - 비밀번호
		Scanner sc = new Scanner(System.in);
		int t = 10;

		for (int tc = 1; tc < 1 + t; tc++) {
			// 1. 설정
			String pw = "";
			int len = sc.nextInt();
			String numStr = sc.next();

			// 2. 반복된 숫자 제거
			while (true) {
				String tempPW = removeNum(numStr);
				if (tempPW.length() == len) {
					pw = tempPW;
					break;
				}
				len = tempPW.length();
				numStr = tempPW;
			}

			System.out.println("#" + tc + " " + pw);
		}
	}

	public void D3_4() {
		// 1213. [S/W 문제해결 기본] 3일차 - String
		Scanner sc = new Scanner(System.in);
		int t = 10;

		for (int tc = 1; tc < 1 + t; tc++) {
			int result = 0;
			int i = sc.nextInt();
			String findStr = "", str = "";

			findStr = sc.next();
			str = sc.next();

			char[] charStr = str.replace(findStr, "!").toCharArray();
			for (char c : charStr) {
				if (c == '!') {
					result++;
				}
			}

			System.out.println("#" + i + " " + result);
		}

	}

	public void D3_3() {
		// 19113. 식료품 가게
		Scanner sc = new Scanner(System.in);
		int t;
		t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			int count = sc.nextInt(); // 상품 개수
			Integer[] priceArr = new Integer[count * 2]; // 할인 가격, 정상 가격
			String result = "";
			int saleIdx = 0;

			for (int i = 0; i < count * 2; i++) {
				priceArr[i] = sc.nextInt();
			}

			for (int a = 0; a < count * 2; a++) {
				if (priceArr[a] != 0) {
					for (int b = a + 1; b < count * 2; b++) {
						if (priceArr[a] == priceArr[b] * 0.75) {
							result += priceArr[a] + " ";
							priceArr[a] = 0;
							priceArr[b] = 0;
							break;
						}
					}
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_2() {
		// 17642. 최대 조작 횟수

		Scanner sc = new Scanner(System.in);
		int t;
		t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			long result = 0;
			long a = sc.nextLong();
			long b = sc.nextLong();

			long num = b - a;

			if (num == 0) {
				result = 0; // 이미 같은경우
			} else if (num <= 1) {
				result = -1; // A는 더하기 연산만 가능하므로..
				// 이걸 까먹어서 1시간을 헤맸네
			} else {
				if (num % 2 == 1) {
					result = (long) ((num - 1) / 2);
				} else {
					result = (long) (num / 2);
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	public void D3_1() {
		// 18662. 등차수열 만들기
		Scanner sc = new Scanner(System.in);
		int t;
		t = sc.nextInt();

		for (int tc = 1; tc < 1 + t; tc++) {
			Double result = -1.0;
			int[] num = new int[3];
			for (int i = 0; i < num.length; i++) {
				num[i] = sc.nextInt();
			}

			if (num[1] - num[0] == num[2] - num[1]) {
				result = 0.0;
			} else {
				Double x = (num[0] + num[2]) / 2.0;

				if (num[1] > x) {
					result = num[1] - x;
				} else {
					result = x - num[1];
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	public static void main(String[] args) {
		D3 d = new D3();

		int[][] land = { { 1, 2, 3, 5 }, { 5, 6, 7, 8 }, { 4, 3, 2, 1 } };
		d.D3_14();
	}
}
