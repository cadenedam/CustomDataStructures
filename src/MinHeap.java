import MinHeapInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    private Vector<E> heap;

    private void heapify(int i) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int small = i;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(small)) < 0) {
            small = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(small)) < 0) {
            small = rightChild;
        }

        if (small != i) {
            swap(i, small);
            heapify(small);
        }
    }

    public MinHeap(){
        heap = new Vector<>();
    }


    @Override
    public void add(E item) {
        if (item == null) {
            throw new NullPointerException("Specified item is null.");
        }

        heap.add(item);
        bubble(heap.size() - 1);
    }


    @Override
    public void clear() {
        while (!heap.isEmpty()) {
            int last = heap.size() - 1;
            heap.remove(last);
        }
    }


    @Override
    public E peekMin() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }


    @Override
    public E removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        E min = heap.get(0);

        E last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapify(0);
        }

        return min;
    }


    @Override
    public int size() {
        return heap.size();
    }

    private void swap(int index1, int index2) {
        E tempHeap = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, tempHeap);
    }

    private void bubble(int idx) {
        int parentIdx = (idx - 1) / 2;

        while (idx > 0 && heap.get(idx).compareTo(heap.get(parentIdx)) < 0) {
            swap(idx, parentIdx);
            idx = parentIdx;
            parentIdx = (idx - 1) / 2;
        }
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public E get(int index) {
        return heap.get(index);
    }

    public Vector<E> getHeap() {
        return heap;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the MinHeap how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}

