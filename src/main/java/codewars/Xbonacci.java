package codewars;

public class Xbonacci {

    public double[] xbonacci(double[] seed, int numberOfResults) {
        if (numberOfResults == 0) {
            return new double[0];
        }
        double[] sequenceResult = new double[numberOfResults];
        for (int index = 0; index < Math.min(seed.length, numberOfResults); index++) {
            sequenceResult[index] = seed[index];
        }


        for (int index = 0; index < numberOfResults - seed.length; index++) {
            double sum = sum(seed);
            sequenceResult[seed.length + index] = sum;
            seed[index % seed.length] = sum;
        }
        return sequenceResult;
    }

    private double sum(double[] seed) {
        double sum = 0;
        for (double element : seed) {
            sum += element;
        }
        return sum;
    }
}
