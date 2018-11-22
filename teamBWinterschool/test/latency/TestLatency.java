package latency;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.sound.midi.Soundbank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.EventChainNotCompletedException;
import entities.RunnablesDuration;

public class TestLatency {

	@Test
	public void testCalculateE2ELatency() {
LatencyCalculator latency = new LatencyCalculator();
		
		RunnablesDuration r1 = new RunnablesDuration();
		r1.setDuration(1);
		RunnablesDuration r2 = new RunnablesDuration();
		r2.setDuration(2);
		RunnablesDuration r3 = new RunnablesDuration();
		r3.setDuration(3);
		
		RunnablesDuration[] scheduledRunnables = new RunnablesDuration[61];
		
		scheduledRunnables[0] = r1;
		scheduledRunnables[1] = r1;
		scheduledRunnables[2] = r1;
		
		scheduledRunnables[15] = r1;
		scheduledRunnables[16] = r1;
		scheduledRunnables[17] = r1;
		
		scheduledRunnables[30] = r1;
		scheduledRunnables[31] = r1;
		scheduledRunnables[32] = r1;
		
		scheduledRunnables[45] = r1;
		scheduledRunnables[46] = r1;
		scheduledRunnables[47] = r1;
		
		scheduledRunnables[60] = r1;
		
		//Task 2
		scheduledRunnables[3] = r2;
		scheduledRunnables[4] = r2;
		scheduledRunnables[5] = r2;
		scheduledRunnables[6] = r2;
		scheduledRunnables[7] = r2;
		
		scheduledRunnables[20] = r2;
		scheduledRunnables[21] = r2;
		scheduledRunnables[22] = r2;
		scheduledRunnables[23] = r2;
		scheduledRunnables[24] = r2;
		
		scheduledRunnables[40] = r2;
		scheduledRunnables[41] = r2;
		scheduledRunnables[42] = r2;
		scheduledRunnables[43] = r2;
		scheduledRunnables[44] = r2;
		
		//Task 3
		scheduledRunnables[8] = r3;
		scheduledRunnables[9] = r3;
		scheduledRunnables[10] = r3;
		scheduledRunnables[11] = r3;
		scheduledRunnables[12] = r3;
		scheduledRunnables[13] = r3;
		scheduledRunnables[14] = r3;
		
		scheduledRunnables[33] = r3;
		scheduledRunnables[34] = r3;
		scheduledRunnables[35] = r3;
		scheduledRunnables[36] = r3;
		scheduledRunnables[37] = r3;
		scheduledRunnables[38] = r3;
		scheduledRunnables[39] = r3;


		Queue<RunnablesDuration> eventChain = new LinkedList<>();
		eventChain.add(r1);
		eventChain.add(r2);
		eventChain.add(r3);
		
		List<Integer> actual;
		
		actual = latency.calculateE2ELatency(scheduledRunnables, eventChain);
		
		
		
		System.out.println("Latencies: ");
		for(Integer i : actual) {
			System.out.println(i);
		}
		//assertEquals(8, actual);
		
		
	}
	
	@Test
	public void testCalculateE2ELatency_onePass_multipleRunnables() {
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
		
		int actual;
		try {
			actual = latency.calculateE2ELatency_onePass(runnables, eventChain);
			assertEquals(8, actual);
		} catch (EventChainNotCompletedException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCalculateE2ELatency_twoRunnables() {
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

		
		int actual;
		try {
			actual = latency.calculateE2ELatency_onePass(runnables, eventChain);
			assertEquals(2, actual);
		} catch (EventChainNotCompletedException e) {
			e.printStackTrace();
		}
		
	}


}
