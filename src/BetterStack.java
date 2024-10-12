package CommonUtils;

import java.util.EmptyStackException;

public class BetterStack<E> implements BetterStackInterface<E> {

    private final int INIT_CAPACITY = 8;

    private final int INCREASE_FACTOR = 2;
    private final int CONSTANT_INCREMENT = 1 << 5; // 32

    private final double DECREASE_FACTOR = 0.5;


    private E[] stack;
    private int tail;
    private int capacity;


    @SuppressWarnings("unchecked")
    public BetterStack(){
        stack = (E[]) new Object[INIT_CAPACITY];
        tail = 0;
        capacity = INIT_CAPACITY;
    }

    @Override
    public void push(E item) throws OutOfMemoryError {
        boolean full = tail >= capacity;

        if (full) {
            int newCapacity;

            try {
                newCapacity = Math.multiplyExact(capacity, INCREASE_FACTOR);
            } catch (ArithmeticException e) {
                newCapacity = capacity + CONSTANT_INCREMENT;

                if (newCapacity < capacity) {
                    throw new OutOfMemoryError("Cannot allocate more memory for stack.");
                }
            }

            @SuppressWarnings("unchecked")
            E[] newStack = (E[]) new Object[newCapacity];
            int length = size();

            for (int i = 0; i < length; i++) {
                newStack[i] = stack[i];
            }

            tail = length;
            stack = newStack;
            capacity = newCapacity;
        }

        stack[tail] = item;
        tail++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            tail -= 1;
            return stack[tail];
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[tail - 1];
        }
    }

    @Override
    public boolean isEmpty() {
        return tail == 0;
    }

    @Override
    public int size() {
        return tail;
    }

    @Override
    public void draw(java.awt.Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the stack how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
