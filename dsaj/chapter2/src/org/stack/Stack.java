package org.stack;

public interface Stack {

    public int getSize();
    public boolean isEmpty();
    public Object top() throws ExceptionStackEmpty;
    public void push(Object obj) throws ExceptionStackFull;
    public Object pop() throws ExceptionStackEmpty;
}
