package com.vlzov.program;

import java.util.Scanner;

public class Program {

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean flag = true;

    private static int earnings = 0;
    private static int spendings = 0;

    private static final double EARNINGS_TAX = 0.06; // 6%
    private static final double EARNINGS_SPENDINGS_TAX = 0.15; // 15%

    static void main() {
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

            switch (Integer.parseInt(input)){
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
                case 3:             // Выбрать систему налогооблажения

                    taxes();

                    break;
                default:
                    System.out.println("Такой операции нет.");
            }

        }

    }

    private static void taxes() {

        if (getEarningsTax() < getEarningsSpendingsTax()) {

            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + getEarningsTax());
            System.out.println("Налог на другой системет: " + getEarningsSpendingsTax());
            System.out.println("Экономия: " + getTaxesDiff());

            ending();

        } else if (getEarningsSpendingsTax() < getEarningsTax()) {

            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + getEarningsSpendingsTax());
            System.out.println("Налог на другой системет: " + getEarningsTax());
            System.out.println("Экономия: " + getTaxesDiff());

            ending();

        } else {

            System.out.println("Нет разницы в выборе УСН");
            System.out.println("Ваш налог составит: " + getEarningsTax());

            ending();

        }

    }

    private static double getEarningsTax() {return earnings * EARNINGS_TAX;}

    private static double getEarningsSpendingsTax() {
        double tax = (earnings - spendings) * EARNINGS_SPENDINGS_TAX;
        if (tax > 0) return tax;
        else return 0;
    }

    private static double getTaxesDiff() {return Math.abs(getEarningsTax() - getEarningsSpendingsTax());}

    private static void ending() {

        System.out.println("Программа завершена!");
        flag = false;

    }

    private static void mainMenu() {

        System.out.println("Выберите операцию и введите её номер:");
        System.out.println("1. Добавить новый доход");
        System.out.println("2. Добавить новый расход");
        System.out.println("3. Выбрать систему налогооблажения");

    }


}
