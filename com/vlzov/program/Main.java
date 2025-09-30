package com.vlzov.program;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean flag = true;

    private static int earnings = 0;
    private static int spendings = 0;

    private static final double EARNINGS_TAX_RATE = 0.06; // 6%
    private static final double EARNINGS_MINUS_SPENDINGS_TAX_RATE = 0.15; // 15%

    public static void main(String[] args) {
        String input;

        while (flag) {
            mainMenu();
            input = scanner.nextLine();
            checkInput(input);
        }
    }

    private static void checkInput(String input) {
        String inputMoney;

        if (input.equals("end")) {
            flag = false;
        } else {
            switch (Integer.parseInt(input)) {
                case 1:             // Добавить доход
                    System.out.print("Введите сумму дохода: ");
                    inputMoney = scanner.nextLine();
                    earnings += Integer.parseInt(inputMoney);
                    System.out.println();
                    break;
                case 2:             // Добавить расход
                    System.out.print("Введите сумму расхода: ");
                    inputMoney = scanner.nextLine();
                    spendings += Integer.parseInt(inputMoney);
                    System.out.println();
                    break;
                case 3:             // Выбрать систему налогообложения
                    taxes();
                    break;
                default:
                    System.out.println("Такой операции нет.");
            }
        }
    }

    private static void taxes() {
        double earningsTax = calculateEarningsTax(earnings);
        double earningsMinusSpendingsTax = calculateEarningsMinusSpendingsTax(earnings, spendings);
        double taxesDiff = Math.abs(earningsTax - earningsMinusSpendingsTax);

        if (earningsTax < earningsMinusSpendingsTax) {
            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + earningsTax);
            System.out.println("Налог на другой системе: " + earningsMinusSpendingsTax);
            System.out.println("Экономия: " + taxesDiff);
            ending();
        } else if (earningsMinusSpendingsTax < earningsTax) {
            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + earningsMinusSpendingsTax);
            System.out.println("Налог на другой системе: " + earningsTax);
            System.out.println("Экономия: " + taxesDiff);
            ending();
        } else {
            System.out.println("Нет разницы в выборе УСН");
            System.out.println("Ваш налог составит: " + earningsTax);
            ending();
        }
    }

    private static double calculateEarningsTax(int earnings) {
        return earnings * EARNINGS_TAX_RATE;
    }

    private static double calculateEarningsMinusSpendingsTax(int earnings, int spendings) {
        double tax = (earnings - spendings) * EARNINGS_MINUS_SPENDINGS_TAX_RATE;
        return Math.max(tax, 0);
    }

    private static void ending() {
        System.out.println("Программа завершена!");
        flag = false;
    }

    private static void mainMenu() {
        System.out.println("Выберите операцию и введите её номер:");
        System.out.println("1. Добавить новый доход");
        System.out.println("2. Добавить новый расход");
        System.out.println("3. Выбрать систему налогообложения");
    }
}
