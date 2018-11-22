package latency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import entities.EventChainNotCompletedException;
import entities.RunnablesDuration;

public class LatencyCalculator {
	
	// TODO Calculates too many latencies (see test `TestLateny.testCalculateE2ELatency`})
	/**
	 * @return a sorted list of all latencies for the given event chain. The first element is the worst case.
	 */
	public List<Integer> calculateE2ELatency(RunnablesDuration[] runnables, Queue<RunnablesDuration> eventChain) {		
		List<Integer> latencies = new LinkedList<>();
		
		try {
			while(true) {
				Queue<RunnablesDuration> eventChainCopy = new LinkedList<RunnablesDuration>(eventChain);
				int latency = calculateE2ELatency_onePass(runnables, eventChainCopy);
				latencies.add(latency);
			}
		} catch (EventChainNotCompletedException e) {
			// No further results
		}
		
		Collections.sort(latencies, Collections.reverseOrder());
		
		return latencies;
	}
	
	public int calculateE2ELatency_onePass(RunnablesDuration[] runnables, Queue<RunnablesDuration> eventChain) throws EventChainNotCompletedException {
		if(eventChain.size() == 0) {
			throw new IllegalArgumentException("EventChain mustn't be empty");
		}
		
		boolean chainStarted = false;
		boolean chainFinished = false;
		RunnablesDuration currentState = null;
		RunnablesDuration nextState = eventChain.peek();
		RunnablesDuration input = null;
		int e2eLatency = 0;
		
		for(int i = 0; i < runnables.length; i++) {
			input = runnables[i];
			boolean lastInput = i == runnables.length - 1 ;

			// Start of task chain
			if(!chainStarted && currentState == null) {
				if(input != null 
						&& nextState != null 
						&& input.equals(nextState)) {
					
					currentState = eventChain.poll();
					nextState = eventChain.peek();
					chainStarted = true;
					runnables[i] = null;
				}
			}
			// Move to next state
			else if(chainStarted 
					&& nextState != null 
					&& currentState != null
					&& input != null 
					&& input.equals(nextState)) {
				currentState = eventChain.poll();
				nextState = eventChain.peek();
				runnables[i] = null;
			}
			
			// Chain ends
			if(chainStarted && currentState != null && nextState == null && (input == null || !input.equals(currentState))) {
				chainFinished = true;
				break;
			}
			// Chain ends
			else if(chainStarted && lastInput && nextState == null) {
				chainFinished = true;
				break;
			}
			// Else: stay in current state
			
			
			if(chainStarted) {
				e2eLatency++;
			}
		}
		
		if(!chainFinished) {
			throw new EventChainNotCompletedException();
		}
		
		return e2eLatency;
	}
	
	
}
