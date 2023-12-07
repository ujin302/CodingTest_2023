import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // n == 바구니 개수
        // m == 시도 횟수

        Scanner sc = new Scanner(System.in);
        String nm = sc.nextLine(); // 바구니 최대 번호, 시도 횟수

        int[] baskeNum = new int[Integer.parseInt(nm.split(" ")[0])];
        String output = "";

        // 바구니 안의 공 번호 세팅
        for (int i = 0; i < Integer.parseInt(nm.split(" ")[0]); i++) {
            baskeNum[i] = i + 1;
        }

        // 바구니 안의 공 교환
        for (int i = 0; i < Integer.parseInt(nm.split(" ")[1]); i++) {
            String inputChange = sc.nextLine(); // 교환 번호
            int start = Integer.parseInt(inputChange.split(" ")[0]); // 교환 시작
            int end = Integer.parseInt(inputChange.split(" ")[1]); // 교환 종료
            int temp = 0;

            temp = baskeNum[start - 1];
            baskeNum[start - 1] = baskeNum[end - 1];
            baskeNum[end - 1] = temp;
        }

        for (int i = 0; i < baskeNum.length; i++) {
            output += baskeNum[i] + " ";
        }

        System.out.println(output);
    }
}