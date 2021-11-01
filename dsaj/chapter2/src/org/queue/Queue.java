package org.queue;

import org.stack.ExceptionStackFull;

public interface Queue {

    public int getSize();
    public boolean isEmpty();
    public Object front() throws ExceptionQueueEmpty;
    public Object dequeue() throws ExceptionQueueEmpty;
    public void enqueue(Object obj) throws ExceptionQueueFull;
    public void traversal();
}
