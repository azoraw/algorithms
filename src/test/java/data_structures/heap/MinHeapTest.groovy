package data_structures.heap

import spock.lang.Specification

class MinHeapTest extends Specification {

    def "should properly add new numbers to heap"() {
        given:
        def minHeap = new MinHeap(10)

        when:
        minHeap.push(1)
        minHeap.push(0)
        minHeap.push(2)
        minHeap.push(9)
        minHeap.push(3)
        minHeap.push(7)
        minHeap.push(8)
        minHeap.push(4)
        minHeap.push(5)
        minHeap.push(6)

        then:
        def heap = minHeap.heap

    }
}
