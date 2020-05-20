import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int length;
	
    // construct an empty deque
    public Deque()
    {
    	first = null;
    	last = null;
    	length = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
    	return first == null;
    }

    // return the number of items on the deque
    public int size(){
    	return length;
    }

    // add the item to the front
    public void addFirst(Item item){
    	if (item == null) throw new IllegalArgumentException();
    	
    	Node toAdd = new Node(item);
    	toAdd.next = first;
    	toAdd.prev = null;
    	length++;
    	
    	if (first == null) {
    		first = toAdd;
    		last = toAdd;
    		return;
    	}
    	
    	first.prev = toAdd;
    	first = toAdd;
    }

    // add the item to the back
    public void addLast(Item item){
    	if (item == null) throw new IllegalArgumentException();
    	
    	Node toAdd = new Node(item);
    	toAdd.next = null;
    	toAdd.prev = last;
    	length++;
    	
    	if(first == null) {
    		first = toAdd;
    		last = toAdd;
    		return;
    	}
    	
    	last.next = toAdd;
    	last = toAdd;
    }

    // remove and return the item from the front
    public Item removeFirst(){
    	if (isEmpty()) throw new java.util.NoSuchElementException();
    	
    	Item value = first.value;
    	length--;
    	
    	if(first.next == null)
    	{
    		first = null;
    		return value;
    	}
    	
    	first = first.next;
    	first.prev = null;
    	
    	return value;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new java.util.NoSuchElementException();
    	
    	Item value = last.value;
    	length--;
    	
    	if (last.prev == null) {
        	first = null;
        	last = null;
        	
        	return value;
        }
    	
    	last = last.prev;
    	last.next = null;
    	
    	return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){  	
    	return new DequeIterator();
    }
    
    private class Node {
    	
    	Item value;
    	Node next;
    	Node prev;
    	
    	public Node(Item v)
    	{
    		value = v;
    	}
    }
    
    private class DequeIterator implements Iterator<Item>{
    	private Node cur = first;

		@Override
		public boolean hasNext() {
			
			return cur != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new java.util.NoSuchElementException();
			
			Item value = cur.value;
			cur = cur.next;
			
			return value;
		}
		
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
    	
    }
    
    // unit testing (required)
    public static void main(String[] args)
    {
    	Deque<String> d = new Deque<String>();
    	
    	System.out.println(d.isEmpty());
    	System.out.println(d.length);
    	
    	d.addFirst("first1");
    	d.addFirst("first2");
    	d.addLast("last1");
    	d.addLast("last2");
    	
    	for(String s : d)
    	{
    		System.out.println(s);
    	}
    	
    	System.out.println(d.isEmpty());
    	System.out.println(d.length);
    	
    	System.out.println(d.removeFirst());
    	System.out.println(d.removeLast());
    	
    	System.out.println(d.length);
    	
    	d.removeFirst();
    	d.removeFirst();
    	
    	System.out.println(d.isEmpty());
    	System.out.println(d.length);
    	
    	Iterator<String> iter = d.iterator();
    	
    	System.out.println(iter.next());
    }

}
