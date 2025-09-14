import java.util.Scanner;

public class Calculator {
    static final String AVAILABLE_OPERATIONS = "+,-,*,/";
    static Scanner scanner = new Scanner(System.in);

    public static void startCalculationProcess() {
        System.out.println("Старт работы калькулятора! Для сброса нажмите клавишу C, для завершения работы программы нажмите клавишу S");
        startCalculationProcess(getOperand());
    }

    public static void startCalculationProcess(double operand1) {
        char operation = getOperation();
        double operand2 = getOperand();
        double result = calculate(operand1, operand2, operation);
        System.out.println("Результат: " + result);
        startCalculationProcess(result);
    }

    private static double getOperand() {
        double operand;
        System.out.println("Введите число:");
        if (!scanner.hasNextDouble()) {
            char character = scanner.next().charAt(0);
            checkExitCharacter(character);
            System.out.println("Некорректное число! Повторите ввод.");
            operand = getOperand();
        } else {
            operand = scanner.nextDouble();
        }

        return operand;
    }

    private static char getOperation() {
        char operation;
        System.out.println("Доступные операции: " + AVAILABLE_OPERATIONS + "\nВведите операцию:");
        if (!scanner.hasNext()) {
            System.out.println("Не введена операция! Введите операцию.");
            scanner.next();
            operation = getOperation();
        } else {
            char character = scanner.next().charAt(0);
            checkExitCharacter(character);
            if (AVAILABLE_OPERATIONS.indexOf(character) == -1) {
                System.out.println("Некорректная операция! Повторите ввод.");
                operation = getOperation();
            } else {
                operation = character;
            }
        }

        return operation;
    }

    private static double calculate(double operand1, double operand2, char operation) {
        switch (operation) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return checkNull(operand2) ? operand1 : operand1 / operand2;
            default:
                System.out.println("Неподдерживаемая операция!");
                return operand1;
        }
    }

    private static boolean checkNull(double num) {
        if (num == 0) {
            System.out.println("Нельзя делить на нуль!");
        }

        return num == 0;
    }

    private static void checkExitCharacter(char ch) {
        switch (ch) {
            case 'c':
            case 'C': {
                System.out.println("Начинаем сначала!");
                scanner.reset();
                startCalculationProcess();
                break;
            }
            case 's':
            case 'S': {
                System.out.println("Программа завершена!");
                scanner.close();
                System.exit(0);
                break;
            }
        }
    }
}
