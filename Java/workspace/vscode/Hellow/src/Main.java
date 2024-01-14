import java.util.Dictionary;
import java.util.Scanner;

public class Main {
    public static int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = {};
        answer = new int[photo.length];
        // name 사진 속 인물
        // yearing 그리움 점수
        // photo 구해야할 각 사진의 인물들

        for (int i = 0; i < photo.length; i++) {
            for (int j = 0; j < photo[i].length; j++) {
                for (int n = 0; n < name.length; n++) {
                    if (photo[i][j].equals(name[n])) {
                        // System.out.print(name[n]);
                        // System.out.print(yearning[n]);
                        answer[i] += yearning[n];
                        // System.out.print(answer[i]);
                    } else
                        continue;
                }
            }
        }
        return answer;

    }

    public static void main(String[] args) {
        String[] name = { "kali", "mari", "don" };
        int[] year = { 11, 1, 55 };
        String[][] photo = {
                { "kali", "mari", "don" },
                { "pony", "tom", "teddy" },
                { "con", "mona", "don" }
        };

        int[] str = solution(name, year, photo);
        System.out.println(str[0]);
        System.out.println(str[1]);

        System.out.println(str[2]);
        // String a = solution("abc12345", "ABC", 2);
        // System.out.println(a);

        // a = solution("Program29b8UYP", "merS123", 7);
        // System.out.println(a);

        // a = solution("He11oWor1d", "lloWorl", 2);
        // System.out.println(a);
    }
}