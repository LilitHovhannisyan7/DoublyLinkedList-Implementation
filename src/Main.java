class DoublyLinkedList<T>
{
    class Node<T>
    {
        T value;
        Node<T> next;
        Node<T> previous;
        public Node(T val)
        {
            this.value = val;
        }
    }

    private Node<T> head;
    private Node<T> tail;


    public int getSize()
    {
        Node<T> current = this.head;
        int count = 0;
        while(current != null)
        {
            ++count;
            current = current.next;
        }
        return count;
    }

    public void pushBack(T val)
    {
        Node<T> node = new Node<T>(val);
        if(this.head == null)
        {
            this.head = node;
            this.tail = node;
            return;
        }
        this.tail.next = node;
        node.previous = this.tail;
        this.tail = node;
        this.tail.next = null;

    }

    public void pushFront(T val)
    {
        Node<T> node = new Node<T>(val);
        if(this.head == null)
        {
            this.head = node;
            this.tail = node;
            return;
        }
        this.head.previous = node;
        node.next = this.head;
        this.head = node;
    }


    public Node<T> popBack()
    {

        if(this.head == null)
        {
            throw new RuntimeException("The list is empty");
        }
        Node<T> temp = this.tail;
        if(this.head == this.tail)
        {
            this.head = null;
            this.tail = null;
        }
        else
        {
            this.tail = this.tail.previous;
            this.tail.next = null;
            return temp;
        }

        return temp;
    }



    public Node<T> popFront()
    {
        if(this.head == null)
        {
            throw new RuntimeException("The list is empty");
        }
        if(this.head == this.tail)
        {
            this.head = null;
            this.tail = null;
        }
        Node<T> temp = this.head;
        this.head = this.head.next;
        this.head.previous = null;
        return temp;
    }



    public void insert(T val, int position)
    {
        if(position < 0 || position > this.getSize())
        {
            throw new RuntimeException("Invalid position");
        }
        if(position == 0)
        {
            this.pushFront(val);
        }
        else if(position == this.getSize())
        {
            this.pushBack(val);
        }
        else
        {
            Node<T> node = new Node<>(val);
            int count = 0;
            Node<T> current = this.head;
            while(count != (position - 1))
            {
                ++count;
                current = current.next;
            }
            current.next.previous = node;
            node.next = current.next;
            current.next = node;
            node.previous = current;
        }

    }



    public Node<T> delete(int position) {
        if (this.head == null) {
            throw new RuntimeException("The list is empty");
        }
        if (position < 0 || position >= this.getSize()) {
            throw new RuntimeException("Invalid position");
        }

        if (position == 0) {
            return this.popFront();
        } else if (position == this.getSize() - 1) {
            return this.popBack();
        } else {
            int count = 0;
            Node<T> current = this.head;
            while (count != (position - 1)) {
                current = current.next;
                ++count;
            }
            Node<T> temp = current.next;
            current.next.next.previous = current;
            current.next = current.next.next;
            temp.previous = null;
            temp.next = null;
            return temp;
        }
    }

    public int search(T val)
    {
        Node<T> current = this.head;
        int count = 0;
        while(current != null)
        {
            if(current.value.equals(val))
            {
                return count;
            }
            ++count;
            current = current.next;
        }
        return -1;
    }

    public void insert(DoublyLinkedList<T> list, int position)
    {
        if(position == 0)
        {
            list.tail.next = this.head;
            this.head.previous = list.tail;
            this.head = list.head;

        }
        else if(position == this.getSize())
        {
            this.tail.next = list.head;
            list.head.previous = this.tail;
            this.tail = list.tail;

        }
        else
        {
            Node<T> current = this.head;
            int count = 0;
            while(count != (position - 1))
            {
                current = current.next;
                ++count;
            }
            list.tail.next = current.next;
            current.next.previous = list.tail;
            current.next = list.head;
            list.head.previous = current;
        }

        list.head = null;
        list.tail = null;
    }



    @Override
    public String toString()
    {
        Node<T> current = this.head;
        StringBuilder sBuild = new StringBuilder();
        while(current != null)
        {
            sBuild.append(current.value).append(" ");
            current = current.next;
        }
        return sBuild.toString();
    }
}
public class Main
{
    public static void main(String[] args)
    {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();

        doublyLinkedList.pushBack(1);
        doublyLinkedList.pushBack(2);
        doublyLinkedList.pushBack(3);
        doublyLinkedList.pushFront(0);

        System.out.println(doublyLinkedList);

        DoublyLinkedList<Integer>.Node<Integer> frontNode = doublyLinkedList.popFront();


        System.out.println(frontNode.value);
        System.out.println(doublyLinkedList);
        DoublyLinkedList<Integer>.Node<Integer> backNode = doublyLinkedList.popBack();
        System.out.println(backNode.value);
        System.out.println(doublyLinkedList);

        doublyLinkedList.insert(5, 2);


        System.out.println(doublyLinkedList);

        DoublyLinkedList<Integer>.Node<Integer> deletedNode = doublyLinkedList.delete(1);


        System.out.println(deletedNode.value);


        int position = doublyLinkedList.search(2);
        System.out.println(position);


        DoublyLinkedList<Integer> secondList = new DoublyLinkedList<>();
        secondList.pushBack(10);
        secondList.pushBack(20);
        secondList.pushBack(30);


        doublyLinkedList.insert(secondList, 1);

        System.out.println(doublyLinkedList);

    }
}