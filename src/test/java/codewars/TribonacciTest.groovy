package codewars

import spock.lang.Specification
import spock.lang.Unroll

class TribonacciTest extends Specification {

    @Unroll
    def "should compute first #numberOfResults tribonacci numbers"() {
        given:
        def algorithm = new Tribonacci()

        expect:
        algorithm.tribonacci(seed as double[], numberOfResults) == tribonacci as double[]

        where:
        seed              | numberOfResults || tribonacci
        [1, 1, 1]         | 10              || [1, 1, 1, 3, 5, 9, 17, 31, 57, 105]
        [0, 0, 1]         | 10              || [0, 0, 1, 1, 2, 4, 7, 13, 24, 44]
        [0, 1, 1]         | 10              || [0, 1, 1, 2, 4, 7, 13, 24, 44, 81]
        [0, 1]            | 10              || [0, 1, 1, 2, 4, 7, 13, 24, 44, 81]
        [14.0, 2.0, 18.0] | 1               || [14.0]
    }
}
