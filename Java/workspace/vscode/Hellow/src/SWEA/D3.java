import java.util.*;

public class D3 {
	public void D3_10() {
		// 5215. 햄버거 다이어트
		
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) {
			int result = 0;
			int count = sc.nextInt(); // 재료 개수 
			int kcal = sc.nextInt(); // 총 칼로리
			Integer[][] bugerLike = new Integer[count][2];
			for(int i=0; i<count; i++) {
				bugerLike[i][0] = sc.nextInt(); // 각 재료 선호도
				bugerLike[i][1] = sc.nextInt(); // 각 재료 칼로리 
			}
			
			Arrays.sort(bugerLike, (o1, o2) -> o2[0] - o1[0]);
			
			
			for(int c=0; c<count; c++) {
				for(int i=0; i<count; i++) {
					int score = bugerLike[i][0];
					int taste = bugerLike[i][1];
					
					if(kcal - taste >= 0) {
						kcal -= taste;
						result += score;
					}
				}
			}
			
			System.out.println("#"+tc+ " " + result);
		}
	}
	
	
	public void D3_9() {
		// 1208. [S/W 문제해결 기본] 1일차 - Flatten
		
		/*
		 * 덤프 : 가장 높은 곳에서 가장 낮은 곳으로 이동 하는 것 
		 */
		
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) {
			int result = 0;
			int count = sc.nextInt();
			int[] boxHigh = new int[100];
			for(int i=0; i<100; i++) {
				boxHigh[i] = sc.nextInt();
			}
			
			for(int i=0; i<count; i++) {
				Arrays.sort(boxHigh);
				boxHigh[0] ++;
				boxHigh[99] --;
			}
			Arrays.sort(boxHigh);
			result = boxHigh[99] - boxHigh[0];
			
			System.out.println("#"+tc+ " " + result);
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
		int t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) {
			int result = 0;
			int count = sc.nextInt();
			int[] building = new int[count];
			for(int i=0; i<count; i++) {
				building[i] = sc.nextInt();
			}
			
			for(int i=2; i<count-2; i++) {
				if( building[i] > building[i-1] && building[i] > building[i-2] ) {
					if(building[i] > building[i+1] && building[i] > building[i+2]) {
						int[] findMax = {
								building[i-1],
								building[i-2],
								building[i+1],
								building[i+2]
						};
						Arrays.sort(findMax);
						
						result += building[i] - findMax[3];
					}
				}
			}
			
			System.out.println("#"+tc+ " " + result);
		}
	}
	public void D3_7() {
		// 20551. 증가하는 사탕 수열
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) { 
			int result = 0;
			int[] box = new int[3];
			for(int i=0; i<3; i++) {
				box[i] = sc.nextInt();
			}
			
			/*
			 * 1. 각 상자의 크기  : [0] < [1] < [2]
			 * 2. 결과 값 
			 * - -1 : 불가능한 경우
			 * - 0 : 이미 순열 완성
			 * - 1 이상 : 먹어야 하는 사탕의 개수 
			 */
			
			if(box[0] < box[1] && box[1] < box[2]) result = 0; // 이미 완성 
			else if(box[1] >= 2 && box[2] >= 3) { 
				// 먹어야 하는 사탕 개수 
				if(box[1] >= box[2]) {
					result += box[1] - box[2] + 1;
					box[1] = box[2] - 1;
				}
				if(box[0] >= box[1]) {
					result += box[0] - box[1] + 1;
					box[0] = box[1] - 1;
				}
			}else result = -1;
			
			System.out.println("#"+tc+ " " + result);
		}
	}
	
	
	public void D3_6() {
		// 14178. 1차원 정원
		Scanner sc = new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) { 
			int result =0;
			int flower= sc.nextInt();
			int d = sc.nextInt();
			
			int range = d*2+1; // 분무기 1개로 물을 뿌릴수 있는 꽃의 개수 
			result = flower / range;
			if(flower % range != 0) {
				result ++;
			}
			
			System.out.println("#"+tc+ " " + result);
		}
	}
	
	
	public String removeNum(String strNum) {
		String pw="";
		char[] charNum = strNum.toCharArray();
		Queue<Integer> numQueue = new LinkedList<>();
		
		for(char c : charNum) {
			numQueue.add(Character.getNumericValue(c));
		}
		
		while(numQueue.size() >= 2) {
			int a = numQueue.poll();
			int b = numQueue.peek();
			
			if(a==b) {
				numQueue.poll();
			}else {
				pw += a+"";
			}
		}
		
		while(!numQueue.isEmpty()) {
			pw += numQueue.poll() + ""; 
		}

		return pw;
	}
	
	public void D3_5() {
		// 1234. [S/W 문제해결 기본] 10일차 - 비밀번호
		Scanner sc = new Scanner(System.in);
		int t=10;
		                                                                                                                   
		for(int tc =1; tc<1+t; tc++) {
			// 1. 설정 
			String pw="";
			int len= sc.nextInt();
			String numStr = sc.next();
			
			// 2. 반복된 숫자 제거 
			while(true) {
				String tempPW = removeNum(numStr);
				if(tempPW.length() == len) {
					pw = tempPW;
					break;
				}
				len = tempPW.length();
				numStr = tempPW;
			}
			
			System.out.println("#"+tc+ " " + pw);
		}
	}
	
	public void D3_4() {
		// 1213. [S/W 문제해결 기본] 3일차 - String
		Scanner sc = new Scanner(System.in);
		int t=10;
		                                                                                                                   
		for(int tc =1; tc<1+t; tc++) {
			int result=0;
			int i= sc.nextInt();
			String findStr ="", str ="";
			
			findStr = sc.next();
			str = sc.next();
			
			
			char[] charStr = str.replace(findStr, "!").toCharArray();
			for(char c : charStr) {
				if(c == '!') {
					result++;
				}
			}
			
			System.out.println("#"+i+ " " + result);
		}
		
	}
	public void D3_3() {
		// 19113. 식료품 가게
		Scanner sc = new Scanner(System.in);
		int t;
		t=sc.nextInt();
		
		
		for(int tc =1; tc<1+t; tc++) {
			int count = sc.nextInt(); // 상품 개수 
			Integer[] priceArr = new Integer[count*2]; // 할인 가격, 정상 가격
			String result = "";
			int saleIdx=0;
			
			for(int i=0; i<count*2; i++) {
				priceArr[i] = sc.nextInt();
			}
			
			for(int a=0; a<count*2; a++) {
				if(priceArr[a] != 0) {
					for(int b=a+1; b<count*2; b++) {
						if(priceArr[a] == priceArr[b]*0.75) {
							result += priceArr[a] + " ";
							priceArr[a] = 0;
							priceArr[b] = 0;
							break;
						}
					}
				}
			}
			
			
			System.out.println("#"+tc+ " " + result);
		}
	}
	
	public void D3_2() {
		// 17642. 최대 조작 횟수
		
		Scanner sc = new Scanner(System.in);
		int t;
		t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) {
			long result = 0;
			long a = sc.nextLong();
			long b = sc.nextLong();

			long num = b-a;

			if (num==0) {
				result = 0; // 이미 같은경우
	        }else if(num <= 1) {
	        	result = -1; // A는 더하기 연산만 가능하므로..
	            // 이걸 까먹어서 1시간을 헤맸네
	        } else {
	        	if(num % 2 == 1){
		        	result = (long)((num - 1) / 2) ;              
		        } else {
		        	result = (long)(num/2);
		        }
	        }
			System.out.println("#"+tc+ " "+result);
		}
	}
	
	public void D3_1() {
		// 18662. 등차수열 만들기
		Scanner sc = new Scanner(System.in);
		int t;
		t=sc.nextInt();
		
		for(int tc =1; tc<1+t; tc++) {
			Double result=-1.0;
			int[] num = new int[3];
			for(int i=0; i<num.length; i++) {
				num[i] = sc.nextInt();
			}
			
			if(num[1]-num[0] == num[2]-num[1]) {
				result = 0.0;
			}else {
				Double x = (num[0]+num[2])/2.0;

				if(num[1] > x) {
					result = num[1]-x;
				}else{
					result= x-num[1];
					}
				}
			System.out.println("#"+tc+ " "+result);
		}
	}
	
	
	public static void main(String[]args) {
		D3 d = new D3();
		
		d.D3_8();
	}
}
