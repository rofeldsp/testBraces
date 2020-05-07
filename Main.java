import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        if (!(countBracesAndCheckForbiddenSymbols(str))) {
            System.out.println("Fail");
            return;
        }

        int index = 1;
        ArrayList<Integer> braces = new ArrayList<>(); // вносим в массив скобки по их коду в ASCII
        braces.add((int) str.charAt(0));
        while (true) {
            index = checkString(str, index, braces);
            if (index == str.length()) {
                System.out.println("OK");
                return;
            } else if (index == -1) {
                System.out.println("Fail");
                return;
            }
        }
    }

    private static boolean countBracesAndCheckForbiddenSymbols(String str) {
        int roundBraces = 0;
        int sqrBraces = 0;
        int figureBraces = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
               roundBraces++;
            } else if (str.charAt(i) == ')') {
                roundBraces--;
            } else if (str.charAt(i) == '[') {
                sqrBraces++;
            } else if (str.charAt(i) == ']') {
                sqrBraces--;
            } else if (str.charAt(i) == '{') {
                figureBraces++;
            } else if (str.charAt(i) == '}') {
                figureBraces--;
            } else {
                return false;
            }
        }
        if (roundBraces == 0 && sqrBraces == 0 && figureBraces == 0) {
            return true;
        } else {
            return false;
        }
    }

    static  int checkString(String str, int index, ArrayList<Integer> braces) {
        int braceToClose = braces.get(braces.size() - 1);
        if (braceToClose == 40) {  // 40 == '(' разница между всеми видами скобок в ASCII — 2 символа, но в случае c круглыми скобками это 1 символ, поэтом делаем --
           braceToClose--;
        }
        if (str.charAt(index) == ']' || str.charAt(index) == '}' || str.charAt(index) == ')') {
           if ((str.charAt(index) == braceToClose + 2)) {
               braces.remove(braces.size() - 1);
               return index + 1;
           } else {
               return -1;
           }
        } else {
           braces.add((int) str.charAt(index));
           return checkString(str, index + 1, braces);
        }
    }
}
