package data_structures.heap;

public class MinHeap {

    private Node[] heap;
    private int counter;

    MinHeap(int maxHeapSize) {
        this.heap = new Node[maxHeapSize];
        counter = 0;
    }

    public void push(Node node) {
        int currentIndex = counter;
        heap[counter++] = node;

        while (true) {
            int parentIndex = getParentIndex(currentIndex);
            if (parentIndex >= 0 && heap[parentIndex].value > node.value) {
                heap[currentIndex] = heap[parentIndex];
                heap[parentIndex] = node;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public Node pop() {
        Node value = heap[0];
        heap[0] = heap[--counter];
        heap[counter] = null;
        int currentIndex = 0;

        while (true) {
            if(getRightChild(currentIndex) < counter) {

                if(heap[currentIndex].value > getRightChild(currentIndex).value  && getLeftChild(currentIndex).value])
            }
        }

        return value;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private Node getLeftChild(int parentIndex) {
        return heap[2 * parentIndex + 1];
    }

    private Node getRightChild(int parentIndex) {
        return heap[2 * parentIndex + 2];
    }

}
