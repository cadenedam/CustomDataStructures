import java.awt.*;


public class BetterQueue<E> implements BetterQueueInterface<E> {


    private final int INIT_CAPACITY = 8;



    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32




    private final double DECREASE_FACTOR = 0.5;



    private E[] queue;
    private int head;
    private int tail;
    private int capacity = INIT_CAPACITY;


    @SuppressWarnings("unchecked")
    public BetterQueue(){
        head = 0;
        tail = 0;
        queue = (E[]) new Object[capacity];
    }


    @Override
    public void add(E item) {
        if (item == null) {
            throw new NullPointerException("The specified element is null");
        }

        boolean full = (tail + 1) % capacity == head;

        if (full) {
            int newCapacity;

            try {
                newCapacity = Math.multiplyExact(capacity, INCREASE_FACTOR);
            } catch (ArithmeticException e) {
                newCapacity = capacity + CONSTANT_INCREMENT;

                if (newCapacity < capacity) {
                    throw new OutOfMemoryError("Cannot allocate more memory for queue");
                }
            }

            @SuppressWarnings("unchecked")
            E[] newQueue = (E[]) new Object[newCapacity];
            int length = size();

            for (int i = 0; i < length; i++) {
                newQueue[i] = queue[(head + i) % capacity];
            }

            head = 0;
            tail = length;
            queue = newQueue;
            capacity = newCapacity;
        }

        queue[tail] = item;
        tail = (tail + 1) % capacity;
    }


    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[head];
        }
    }


    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        E removedItem = queue[head];

        head = (head + 1) % capacity;

        int currentSize = size();
        if (currentSize < capacity * DECREASE_FACTOR && capacity > INIT_CAPACITY) {
            int newCapacity = Math.max((int) (capacity * DECREASE_FACTOR), INIT_CAPACITY);

            if (newCapacity < capacity) {
                @SuppressWarnings("unchecked")
                E[] newQueue = (E[]) new Object[newCapacity];

                for (int i = 0; i < currentSize; i++) {
                    newQueue[i] = queue[(head + i) % capacity];
                }

                head = 0;
                tail = currentSize;
                queue = newQueue;
                capacity = newCapacity;
            }
        }

        return removedItem;
    }


    @Override
    public int size() {
        if (head == tail) {
            return 0;
        } else if (tail > head) {
            return tail - head;
        } else {
            return (capacity - head) + tail;
        }
    }


    @Override
    public boolean isEmpty() {
        return head == tail;
    }


    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the queue how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
