package codewars;

import java.util.ArrayList;
import java.util.List;

public class DigPow {

    public static long digPow(int number, int power) {
        List<Integer> digitsInReversedOrder = getDigitsInReversedOrder(number);
        double computedPowerSum = computePowerSum(digitsInReversedOrder, power);
        if (resultExist(computedPowerSum, number)) {
            return (long) computedPowerSum / number;
        }
        return -1;
    }

    private static double computePowerSum(List<Integer> digitsInReversedOrder, int power) {
        double result = 0;
        int size = digitsInReversedOrder.size();
        for (int i = 1; i <= size; i++) {
            result += Math.pow(digitsInReversedOrder.get(size - i), power++);
        }
        return result;
    }

    private static List<Integer> getDigitsInReversedOrder(int number) {
        List<Integer> digitsInInverseOrder = new ArrayList<>();
        while (number > 0) {
            digitsInInverseOrder.add(number % 10);
            number = number / 10;
        }
        return digitsInInverseOrder;
    }

    private static boolean resultExist(double computedPowerSum, int number) {
        return computedPowerSum % number == 0;
    }
}
