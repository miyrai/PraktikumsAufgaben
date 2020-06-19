package de.hfu;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	
	private Queue queue;

	@Before
	public void init() {
		queue = new Queue(3);
	}

	@Test
	public void queueTestEnqueue() {
		
		int [] tmp_queue = {2,9,15};

		queue.enqueue(2);
		queue.enqueue(9);
		queue.enqueue(15);
		
		for (int i = 0; i < tmp_queue.length; i++) {
			assertEquals(tmp_queue[i], queue.queue[i]);
		}
	}
	
	@Test
	public void queueTestDequeue() {

		queue.enqueue(2);
		queue.enqueue(9);
		queue.enqueue(15);

		assertEquals(2, queue.dequeue());
		
	}
	
	@Test(expected=IllegalStateException.class, timeout = 1000)
	public void queueTestDequeueException() {

			queue.enqueue(2);
			queue.dequeue();
			queue.dequeue();

	}
	
	@Test(expected=IllegalArgumentException.class, timeout = 1000)
	public void queueTestConstructorException() {
	
			Queue tmp_queue = new Queue(0);
	}
	
	@Test
	public void queueTestRingspeicher() {

		int[] tmp_queue = { 2, 9, 15 };

		queue.enqueue(3);
		queue.enqueue(9);
		queue.enqueue(15);
		queue.dequeue();
		queue.enqueue(4);
		queue.enqueue(2);

		for (int i = 0; i < queue.queue.length; ++i) {
			assertEquals(tmp_queue[i], queue.queue[i]);

		}
	}

}
	
		


