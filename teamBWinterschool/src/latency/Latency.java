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
			else if(chainStarted && lastInput) {
				chainFinished = true;
				break;
			}
			// Else: stay in current state
			
			
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
