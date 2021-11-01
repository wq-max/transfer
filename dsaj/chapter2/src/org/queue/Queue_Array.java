package org.queue;

public class Queue_Array implements Queue{
    public static final int CAPACITY = 1024;
    protected int capacity;
    protected Object[] Q;
    protected int f = 0;
    protected int r = 0;

    public Queue_Array(int capacity){
        this.capacity = capacity;
        this.Q = new Object[capacity];
    }

    public Queue_Array(){
        this(CAPACITY);
    }

    @Override
    public int getSize() {
        return (capacity-f+r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty())
            throw new ExceptionQueueEmpty("意外：队列空！");
        return Q[f];
    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        if (isEmpty())
            throw new ExceptionQueueEmpty("意外：队列空！");
        Object element = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return element;
    }

    @Override
    public void enqueue(Object obj) throws ExceptionQueueFull {
        if (getSize() == capacity - 1)
            throw new ExceptionQueueFull("意外：栈溢出！");
        Q[r] = obj;
        r = (r + 1) % capacity;
    }

    @Override
    public void traversal() {
        for (int i = f; i < r; i++)
            System.out.print(Q[i] + " ");
        System.out.println();
    }
}
