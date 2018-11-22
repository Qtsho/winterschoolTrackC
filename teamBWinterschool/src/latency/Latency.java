package latency;

import java.util.List;
import java.util.Queue;

import entities.RunnablesDuration;

public class Latency {
	
	
	
	public int calculateE2ELatency(RunnablesDuration[] runnables, Queue<RunnablesDuration> eventChain) {
		if(eventChain.size() == 0) {
			throw new IllegalArgumentException("EventChain mustn't be empty");
		}
		
		boolean chainStarted = false;
		boolean chainFinished = false;
		RunnablesDuration currentState = null;
		RunnablesDuration nextState = null;
		int e2eLatency = 0;
		
		for(int i = 0; i < runnables.length; i++) {
			// Start event chain
			if(runnables[i] != null && !chainStarted) {
				if(runnables[i].equals(eventChain.peek())) {
					chainStarted = true;
					
					// Items from the current chain will be deleted from the array.
					runnables[i] = null;
				}
			}
			
			// Stay in current state (input is a runnable)
			else if(chainStarted 
					&& runnables[i] != null 
					&& eventChain.peek() != null 
					&& runnables[i].equals(eventChain.peek())) {
				
				// Items from the current chain will be deleted from the array.
				runnables[i] = null;
			}
			// Move to next state (input is a runnable)
			else if(chainStarted 
					&& runnables[i] != null 
					&& eventChain.peek() != null 
					&& !runnables[i].equals(eventChain.peek())) {
				
				// Items from the current chain will be deleted from the array.
				eventChain.poll();
				runnables[i] = null;
			}
			// Move to next state (input is null)
			else if (chainStarted && runnables[i] == null) {
				 //eventChain.poll();
			}
			
			// Move to final state
			if(runnables[i] == null && eventChain.peek() == null) {
				chainFinished = true;
				break;
			};
			
			
			if(chainStarted) {
				e2eLatency++;
			}
		}
		
		if(!chainFinished) {
			// TODO The chain couldn't be finished
		}
		
		return e2eLatency;
	}
	
	
}
