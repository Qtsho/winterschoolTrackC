package latency;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.RunnablesDuration;

public class TestLatency {

	@Test
	public void test_multipleRunnables() {
		LatencyCalculator latency = new LatencyCalculator();
		
		RunnablesDuration r1 = new RunnablesDuration();
		r1.setDuration(1);
		RunnablesDuration r2 = new RunnablesDuration();
		r2.setDuration(2);
		RunnablesDuration r3 = new RunnablesDuration();
		r3.setDuration(3);
		
		RunnablesDuration[] runnables = new RunnablesDuration[15];
		runnables[2] = r1;
		runnables[10] = r1;
		
		
		runnables[5] = r2;
		runnables[6] = r2;
		
		runnables[7] = r3;
		runnables[8] = r3;
		runnables[9] = r3;


		Queue<RunnablesDuration> eventChain = new LinkedList<>();
		eventChain.add(r1);
		eventChain.add(r2);
		eventChain.add(r3);
		
		int actual = latency.calculateE2ELatency(runnables, eventChain);
		
		assertEquals(8, actual);
	}

	@Test
	public void test_twoRunnables() {
		LatencyCalculator latency = new LatencyCalculator();
		
		RunnablesDuration r1 = new RunnablesDuration();
		r1.setDuration(1);
		RunnablesDuration r2 = new RunnablesDuration();
		r2.setDuration(1);
		
		RunnablesDuration[] runnables = new RunnablesDuration[15];
		runnables[1] = r1;
		runnables[2] = r2;
		

		Queue<RunnablesDuration> eventChain = new LinkedList<>();
		eventChain.add(r1);
		eventChain.add(r2);

		
		int actual = latency.calculateE2ELatency(runnables, eventChain);
		
		assertEquals(2, actual);
	}


}
