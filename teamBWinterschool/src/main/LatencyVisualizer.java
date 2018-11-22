package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import entities.PrioritizedTask;
import entities.RunnablesDuration;
import latency.LatencyCalculator;
import modelExtraction.ModelExtractor;
import schedule.Scheduler;

public class LatencyVisualizer {
	ModelExtractor modelExtractor;
	Scheduler scheduler;
	LatencyCalculator latencyCalc;
	
	public LatencyVisualizer() {
		this.modelExtractor = new ModelExtractor();
		this.scheduler = new Scheduler();
		this.latencyCalc = new LatencyCalculator();
	}
	
	public void run() {
		String modelPath = "Model/simpleModel.amxmi";
		
		
		
		PrioritizedTask[] prioTasks = modelExtractor.calculatePrioritizedTaskList(modelPath);
		List<PrioritizedTask> prioTaskList = new ArrayList<>(Arrays.asList(prioTasks));
		PrioritizedTask[][] activation = scheduler.calculateActivation(prioTaskList);
		RunnablesDuration[] scheduled = scheduler.scheduling(activation, prioTaskList);
		//Queue<String> eventChain = modelExtractor.extractEventChain(modelPath);
		
		
		// Create a dummy event chain
		Queue<RunnablesDuration> eventChain = new LinkedList<>();
		for(PrioritizedTask task : prioTaskList) {
			for(RunnablesDuration runnable : task.getRunnables()) {
				eventChain.add(runnable);
			}
		}
		
		List<Integer> latencies = latencyCalc.calculateE2ELatency(scheduled, eventChain);
		System.out.println("======== RESULT =========");
		System.out.println(latencies.get(0));
		System.out.println("=================");


	}
	
	public static void main(String[] args) {
		LatencyVisualizer latencyVisualizer = new LatencyVisualizer();
		latencyVisualizer.run();	
		
	}
}
