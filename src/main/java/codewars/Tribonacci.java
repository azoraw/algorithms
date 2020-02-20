package codewars;

public class Tribonacci {

    public double[] tribonacci(double[] seed, int numberOfResults) {
        if (numberOfResults == 0) {
            return new double[0];
        }
        int howManyNumbersToSum = 3;
        double[] sequenceResult = new double[numberOfResults];
        double[] tmp = createTmpArray(seed, howManyNumbersToSum);

        for (int index = 0; index < Math.min(tmp.length, numberOfResults); index++) {
            sequenceResult[index] = tmp[index];
        }

        int offset = howManyNumbersToSum - seed.length;
        for (int index = 0; index < numberOfResults - howManyNumbersToSum + offset; index++) {
            double sum = sum(tmp);
            sequenceResult[seed.length + index] = sum;
            tmp[(index + seed.length) % howManyNumbersToSum] = sum;
        }
        return sequenceResult;
    }

    private double[] createTmpArray(double[] seed, int howManyNumbersToSum) {
        double[] tmp = new double[howManyNumbersToSum];
        for (int index = 0; index < seed.length; index++) {
            tmp[index] = seed[index];
        }
        return tmp;
    }

    private double sum(double[] tmp) {
        double sum = 0;
        for (double element : tmp) {
            sum += element;
        }
        return sum;
    }

}
