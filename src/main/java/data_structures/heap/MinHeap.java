package data_structures.heap;

public class MinHeap {

    private Node[] heap;
    private int heapSize;

    MinHeap(int maxHeapSize) {
        this.heap = new Node[maxHeapSize];
        heapSize = 0;
    }

    public void push(Node node) {
        int currentIndex = heapSize;
        heap[heapSize++] = node;

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
        heap[0] = heap[--heapSize];
        heap[heapSize] = null;
        int currentIndex = 0;

        while (currentIndex < heapSize) {
            int leftChildIndex = getLeftChildIndex(currentIndex);
            int rightChildIndex = getRightChildIndex(currentIndex);
            Node leftChild = heap[leftChildIndex];
            Node rightChild = heap[rightChildIndex];
            Node parent = heap[currentIndex];



        }

        return value;
    }

    private void swap(int nodeIndex1, int nodeIndex2) {
        Node tmp = heap[nodeIndex1];
        heap[nodeIndex1] = heap[nodeIndex2];
        heap[nodeIndex2] = tmp;
    }

    private int getRightChildIndex(int currentIndex) {
        return 2 * currentIndex + 2;
    }

    private int getLeftChildIndex(int currentIndex) {
        return 2 * currentIndex + 1;
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
