import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static HashMap<String, Integer> romans = new HashMap<>();

    static {
        romans.put("I", 1);
        romans.put("II", 2);
        romans.put("III", 3);
        romans.put("IV", 4);
        romans.put("V", 5);
        romans.put("VI", 6);
        romans.put("VII", 7);
        romans.put("VIII", 8);
        romans.put("IX", 9);
        romans.put("X", 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите операцию: ");
        String expression = scanner.nextLine();

        System.out.println(calc(expression));
    }
    public static String calc(String input) {

        String s = input.replaceAll("\\s+", "");
        String str2 = s.toUpperCase();
        String[] parts = str2.split("[+*-/]");

        final boolean flag = isRoman(parts);

        if (!flag && !isArabic(parts)) {
            throw new RuntimeException("Используются одновременно разные системы счисления");
        }

        if (flag) {
            parts = toInt(parts);
        }

        if (parts.length > 2) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (parts.length < 2) {
            throw new RuntimeException("Строка не является математической операцией");
        }

        int[] operands = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            operands[i] = Integer.parseInt(parts[i]);
        }

        if (operands[0] > 10 || operands[1] > 10) {
            throw new RuntimeException("Операнд должен быть между 0 и 10 включительно");
        }

        int result;

        if (s.contains("+")) {
            result = operands[0] + operands[1];
        } else if (s.contains("-")) {
            result = operands[0] - operands[1];
        } else if (s.contains("/")) {
            if (operands[1] == 0) {
                throw new RuntimeException("Деление на ноль запрещено");
            }
            result = operands[0] / operands[1];
        } else if (s.contains("*")) {
            result = operands[0] * operands[1];
        } else {
            throw new RuntimeException("Неверный оператор");
        }

        String resultString;

        if (flag) {
            resultString = toRoman(result);
        } else {
            resultString = Integer.toString(result);
        }
        return resultString;
    }

    public static String[] toInt(String[] numbers){
        numbers[0] = romans.get(numbers[0]).toString();
        numbers[1] = romans.get(numbers[1]).toString();
        return numbers;
    }

    public static String toRoman(int result){
        String romanFinal = "";
        if (result < 1) {
            throw new RuntimeException("В римской системе нет чисел меньше 1, в том числе отрицательных");
        }
            while (result == 100) {
                romanFinal += "C";
                result -= 100;
            }
            while (result >= 90) {
                romanFinal += "XC";
                result -= 90;
            }
            while (result >= 50) {
                romanFinal += "L";
                result -= 50;
            }
            while (result >= 40) {
                romanFinal += "XL";
                result -= 40;
            }
            while (result >= 10) {
                romanFinal += "X";
                result -= 10;
            }
            while (result >= 9) {
                romanFinal += "IX";
                result -= 9;
            }
            while (result >= 5) {
                romanFinal += "V";
                result -= 5;
            }
            while (result >= 4) {
                romanFinal += "IV";
                result -= 4;
            }
            while (result >= 1) {
                romanFinal += "I";
                result -= 1;
            }

        return romanFinal;
    }

    public static boolean isRoman(String[] parts) {
        return romans.containsKey(parts[0]) && romans.containsKey(parts[1]);
    }
    public static boolean isArabic(String[] parts) {
        return parts[0].matches("[0-9]+") && parts[1].matches("[0-9]+");
    }
}

