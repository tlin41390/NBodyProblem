public class ArrayList<T> implements List<T> {
    T [] arr;
    int size;

    public ArrayList()
    {
        arr =  (T[]) new Object[10];
    }
    @Override
    public int size()
    {
        return size;
    }

    private void grow_array()
    {
        T[] newArr = (T[]) new Object[arr.length*2];
        for(int i=0;i<arr.length;i++)
        {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
    @Override
    public T get(int index)
    {
        if( index<0||index>=arr.length)
        {
           return null;
        }
        return arr[index];
    }
    @Override
    public boolean add(T data)
    {
        if(size==arr.length)
        {
            grow_array();
        }
        arr[size++]= data;
        return true;
    }
    @Override
    public void add(T data, int index)
    {
        if(size == arr.length)
        {
            grow_array();
        }
        for(int i =size;i>index;i--)
        {
            arr[i]=arr[i-1];
        }
        arr[index]=data;
        ++size;
    }
}
