package schedule;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.eclipse.swt.custom.ST;

import entities.PrioritizedTask;
import entities.PrioritizedTaskComparator;
import schedule.dummyData.TaskList;

public class Schedule {

	public static void main(String[] args) {
		Schedule schedule = new Schedule();
		List<PrioritizedTask> taskList = TaskList.buildDummyTaskListOneTask();
		
		schedule.calculateActivation(taskList);
	}
	
	public PrioritizedTask[][] calculateActivation(List<PrioritizedTask> taskList) {
		int hyperperiod = calculateHyperperiod();
		
		PrioritizedTask[][] activation = new PrioritizedTask[hyperperiod+1][taskList.size()];
		
		for(PrioritizedTask task : taskList) {
			int rec = task.getRecurrence();
			int taskNo = taskList.indexOf(task);
			
			for(int i = 0; i <= hyperperiod;  i += rec) {
				activation[i][taskNo] = task;
			}
		}
		
		
		// TODO Remove afterwards
		System.out.println("=========");
		System.out.println("Activation");
		for(int i = 0; i < activation.length; i++) {
			System.out.println(i + " :: " + activation[i][0]);
		}
		System.out.println("=========");
		
		return activation;
	}
	
	public PrioritizedTask[] scheduling(PrioritizedTask[][] activation) {
		PrioritizedTask currentTask = null;
		int currentTaskEnd = 0;
		PrioritizedTask[] scheduledTasks = new PrioritizedTask[calculateHyperperiod()+1];
		PriorityQueue<PrioritizedTask> taskQueue = new PriorityQueue<>(10, new PrioritizedTaskComparator());
		
		
		for (int time = 0; time < scheduledTasks.length; time++) {
			// Step 1: Check activations and add to taskQueue
			for (int taskNum = 0; taskNum < activation[time].length; taskNum++) {
				if (activation[time][taskNum] != null) {
					taskQueue.add(activation[time][taskNum]);
				}
			}
			
			// Step 2: Add to scheduledTasks
			if (currentTask != null) {
				
			} else {
				if (taskQueue.peek() != null) {
					currentTask = taskQueue.poll();
				}
			}
		}
		return scheduledTasks;
	}
	
	// TODO Call the LCM Function
	private int calculateHyperperiod() {
		return 60;
	}	
}
