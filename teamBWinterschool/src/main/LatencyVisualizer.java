package main;

import java.util.ArrayList;
import java.util.Arrays;
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
		Queue<String> eventChain = modelExtractor.extractEventChain(modelPath);
		
		// TODO Fix data type of eventChain
		//latencyCalc.calculateE2ELatency(scheduled, eventChain);
		
		// TODO Integrate LCM
		// TODO Latency
		// Finish latency (Multiple passes)

	}
	
	public static void main(String[] args) {
		LatencyVisualizer latencyVisualizer = new LatencyVisualizer();
		latencyVisualizer.run();	
		
	}
}
