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
		
		System.out.println("Priority==========");
		for(PrioritizedTask task : prioTaskList) {
			System.out.println(task);
		}
		
		System.out.println("ACTIVATION==========");
		for(int i = 0; i < activation.length; i++) {
			System.out.println(" - i: " + i);
			for(int j = 0; j < activation[i].length; j++) {
				System.out.println("    - Task: " + activation[i][j]);
			}
		}
		
		System.out.println("SCHEDULE");
		for(int i = 0; i < scheduled.length; i++) {
			System.out.println(" - i: " + i + "  ::  " + scheduled[i]);
		}
		
		
		System.out.println("Runnable Durations========");
		// Create a dummy event chain
		Queue<RunnablesDuration> eventChain = new LinkedList<>();
		for(PrioritizedTask task : prioTaskList) {
			System.out.println(" - " + task);
			for(RunnablesDuration runnable : task.getRunnables()) {
				eventChain.add(runnable);
				System.out.println("     - " + runnable + " :: " + runnable.getDuration());
			}
		}
		
		List<Integer> latencies = latencyCalc.calculateE2ELatency(scheduled, eventChain);
		System.out.println("======== Latency =========");
		System.out.println(latencies.get(0));
		System.out.println("=================");


	}
	
	public static void main(String[] args) {
		LatencyVisualizer latencyVisualizer = new LatencyVisualizer();
		latencyVisualizer.run();	
		
	}
}
