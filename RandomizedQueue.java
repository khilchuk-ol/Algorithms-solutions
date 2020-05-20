import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] buf;
	private int N;
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	buf = (Item[]) new Object[8];
    	N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return N;
    }

    // add the item
    public void enqueue(Item item) {
    	
    	if(item == null) { throw new IllegalArgumentException(); }
    	
    	if(N == buf.length)
    	{
    		resize(2 * buf.length);
    	}
    	
    	buf[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
    	
    	int ind = StdRandom.uniform(0, N);
    	Item v = buf[ind];
    	buf[ind] = buf[--N];
    	
    	if (buf.length == N / 4) { resize(buf.length / 4); }
    	
    	return v;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	
    	return buf[StdRandom.uniform(0, N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	return new RandomIterator(N);
    }
    
    private void resize(int capacity) {
    	Item[] newArr = (Item[]) new Object[capacity];
    	
    	for(int i = 0; i < buf.length; i++){
    		newArr[i] = buf[i];
    	}
    	
    	buf = newArr;
    }
    
    private class RandomIterator implements Iterator<Item>{
    	int[] randInd;
    	int N = 0;
    	
    	public RandomIterator(int amount) {
    		randInd = new int[amount];
    		
    		for (int i = 0; i < amount; i++) {
    			randInd[i] = i;
    		}
    		
    		StdRandom.shuffle(randInd);
    	}
    	
		@Override
		public boolean hasNext() {
			
			return N < randInd.length;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new java.util.NoSuchElementException();
			
			Item value = buf[randInd[N++]];
			
			return value;
		}
		
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
    	
    }

    // unit testing (required)
    public static void main(String[] args) {
    	
    	RandomizedQueue<String> q = new RandomizedQueue<String>();
    	
    	q.enqueue("first");
    	q.enqueue("second");
    	q.enqueue("third");
    	q.enqueue("fourth");
    	q.enqueue("fifth");
    	q.enqueue("6");
    	q.enqueue("7");
    	q.enqueue("8");
    	q.enqueue("9");
    	q.enqueue("10");
    	
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	System.out.println(q.dequeue());
    	
    	for(String s : q)
    	{
    		System.out.println(s);
    	}
    	
    	System.out.println(q.isEmpty());
    	System.out.println(q.size());
    }

}
