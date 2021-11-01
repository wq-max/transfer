package org.stack;

public class Stack_Array implements Stack{

    public static final int CAPACITY = 1024;
    protected int capacity;
    protected Object[] S;
    protected int top = -1;

    public Stack_Array(int capacity){
        this.capacity = capacity;
        this.S = new Object[capacity];
    }

    public Stack_Array(){
        this(CAPACITY);
    }

    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty())
            throw new ExceptionStackEmpty("意外：栈空！");
        return S[top];
    }

    @Override
    public void push(Object obj) throws ExceptionStackFull{
        if (getSize() == capacity)
            throw new ExceptionStackFull("意外：栈溢出！");
        S[++top] = obj;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        if (isEmpty())
            throw new ExceptionStackEmpty("意外：栈空！");
        Object element = S[top];
        S[top--] = null;
        return element;
    }
}
