package codewars

import spock.lang.Specification

class DigPowTest extends Specification {

    def "should compute result"() {
        given:
        def algorithm = new DigPow()

        expect:
        algorithm.digPow(number, startingPower) == result

        where:
        number | startingPower || result
        89     | 1             || 1
        92     | 1             || -1
        695    | 2             || 2
        46288  | 3             || 51
    }
}
