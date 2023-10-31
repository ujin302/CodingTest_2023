import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String b = sc.next();

        System.out.println(a * (b.charAt(0) - '2'));
        System.out.println(a * (b.charAt(0) - '1'));
        System.out.println(a * (b.charAt(0) - '0'));
        System.out.println(a * Integer.parseInt(b));
    }
}