import java.util.ArrayList;
import java.util.List;

public class PowerOfTwoMaxHeap {
    private List<Integer> heap;
    private int numberOfChildren; // Number of children per node (2^x)

    // Constructor takes in the value of x, which determines 2^x children per node
    public PowerOfTwoMaxHeap(int x) {
        this.heap = new ArrayList<>();
        this.numberOfChildren = (int) Math.pow(2, x);
    }

    // Insert a new value into the heap
    public void insert(int value) {
        heap.add(value);
        bubbleUp(heap.size() - 1); // Bubble up from the last inserted element
    }

    // Pop the maximum value (the root) from the heap
    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int maxValue = heap.get(0); // The root is the max element
        int lastValue = heap.remove(heap.size() - 1); // Remove last element

        if (!heap.isEmpty()) {
            heap.set(0, lastValue); // Move last element to root
            bubbleDown(0); // Restore heap property by bubbling down
        }

        return maxValue;
    }

    // Helper method to calculate the parent index
    private int getParentIndex(int index) {
        return (index - 1) / numberOfChildren;
    }

    // Helper method to calculate the index of the k-th child of a parent
    private int getChildIndex(int parentIndex, int childNumber) {
        return numberOfChildren * parentIndex + childNumber + 1;
    }

    // Bubble up the element at the given index to maintain the max heap property
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = getParentIndex(index);
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Bubble down the element at the given index to maintain the max heap property
    private void bubbleDown(int index) {
        while (true) {
            int largestIndex = index;
            // Check all children of the current index
            for (int i = 0; i < numberOfChildren; i++) {
                int childIndex = getChildIndex(index, i);
                if (childIndex < heap.size() && heap.get(childIndex) > heap.get(largestIndex)) {
                    largestIndex = childIndex;
                }
            }

            // If the largest child is larger than the current element, swap them
            if (largestIndex != index) {
                swap(index, largestIndex);
                index = largestIndex;
            } else {
                break;
            }
        }
    }

    // Swap elements at two indices in the heap
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // For testing purposes
    public void printHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); // 4 children per node (2^2)

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(40);
        heap.insert(30);

        heap.printHeap(); // Print heap after inserts

        System.out.println("Max Value: " + heap.popMax()); // Should remove and print 40
        heap.printHeap(); // Print heap after popMax
    }
}
