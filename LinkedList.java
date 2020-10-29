import java.util.*;
public class LinkedList<T> implements List<T> 
{
    Node<T> head;
    int size;
    private static class Node<T>
    {
        Node<T> next;
        T data;

        public Node(T data)
        {
            this.data = data;
            next = null;
        }
    }

    public LinkedList()
    {
        head = null;
        size =0;
    }

    public boolean add(T data)
    {
        Node<T> node = new Node(data);
        if(head == null)
        {
            head = node;
            ++size;
            return false;
        }else{
            Node<T> curr = head;
            while(curr.next!=null)
            {
                curr = curr.next;
            }
            curr.next = node;
            ++size;
            return true;
        }
    }

    public void add(T data, int index)
    {
        Node<T> node = new Node(data);
        Node<T> curr = head;
        if (index == 0)
        {   
            node.next = curr;
            head = node;
            ++size;
        }

        for(int i=0;i<index-1;i++)
        {
            curr = curr.next;
        }
        curr = node;
    }

    public T get(int index)
    {
        if(index<0||index>=size)
        {
            return null;
        }
        Node<T> curr = head;
        for(int i =0;i<index;i++)
        {
            curr = curr.next;
        }
        return curr.data;
    }
    public int size()
    {
        return size;
    }
}

