package schedule;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.eclipse.swt.custom.ST;

import entities.PrioritizedTask;
import entities.PrioritizedTaskComparator;
import entities.RunnablesDuration;
import entities.TaskOutOfBoundsException;
import schedule.dummyData.TaskList;
import util.LCM;

public class Scheduler {

	public static void main(String[] args) {
		Scheduler schedule = new Scheduler();
		List<PrioritizedTask> taskList = TaskList.buildDummyTaskListOneTask();
		
		schedule.calculateActivation(taskList);
	}
	
	public PrioritizedTask[][] calculateActivation(List<PrioritizedTask> taskList) {
		int hyperperiod = calculateHyperperiod(taskList);
		
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
	
	public RunnablesDuration[] scheduling(PrioritizedTask[][] activation, List<PrioritizedTask> taskList) {
		PrioritizedTask currentTask = null;
		int currentTaskEnd = 0;
		int timeSpendInCurrentTask = 0;
		RunnablesDuration[] scheduledRunnables = new RunnablesDuration[calculateHyperperiod(taskList)+1];
		PriorityQueue<PrioritizedTask> taskQueue = new PriorityQueue<>(10, new PrioritizedTaskComparator());
		
		// TODO Refactor: Extract into functions
		for (int time = 0; time < scheduledRunnables.length; time++) {
			// Step 1: Check activations and add to taskQueue
			for (int taskNum = 0; taskNum < activation[time].length; taskNum++) {
				if (activation[time][taskNum] != null) {
					taskQueue.add(activation[time][taskNum]);
				}
			}
			
			// Step 2: Add to scheduledTasks
			if (currentTask != null) {
				// Get runnable of currentTask for time
				RunnablesDuration runnablesDuration;
				try {
					runnablesDuration = calculateRunnableForGivenTime(currentTask, timeSpendInCurrentTask);
					scheduledRunnables[time] = runnablesDuration;
					timeSpendInCurrentTask++;
				} catch (TaskOutOfBoundsException e) {
					currentTask = null;
					if (taskQueue.peek() != null) {
						currentTask = taskQueue.poll();
						timeSpendInCurrentTask = 0;
						
						// Get runnable of currentTask for time
						try {
							runnablesDuration = calculateRunnableForGivenTime(currentTask, timeSpendInCurrentTask);
							scheduledRunnables[time] = runnablesDuration;
							timeSpendInCurrentTask++;
						} catch (TaskOutOfBoundsException e1) {
							// This shouldn't happen
						}
					}
				}
			} else {
				if (taskQueue.peek() != null) {
					currentTask = taskQueue.poll();
					timeSpendInCurrentTask = 0;
					
					// Get runnable of currentTask for time
					RunnablesDuration runnablesDuration;
					try {
						runnablesDuration = calculateRunnableForGivenTime(currentTask, timeSpendInCurrentTask);
						scheduledRunnables[time] = runnablesDuration;
						timeSpendInCurrentTask++;
					} catch (TaskOutOfBoundsException e) {
						// This shouldn't happen
					}
				}
			}
		}
		return scheduledRunnables;
	}
	
	public RunnablesDuration calculateRunnableForGivenTime(PrioritizedTask task, int requestedTime) throws TaskOutOfBoundsException {
		int currentTime = 0;
		for(RunnablesDuration runnable : task.getRunnables()) {
			for(int r = 0; r < runnable.getDuration(); r++) {
				if(currentTime == requestedTime)
					return runnable;
				currentTime++;
			}
		}
		
		throw new TaskOutOfBoundsException();
}
	
	
	// TODO Call the LCM Function
	private int calculateHyperperiod(List<PrioritizedTask> taskList) {
		int[] recurrences = new int[taskList.size()];
		
		for(int i = 0; i < taskList.size(); i++) {
			recurrences[i] = taskList.get(i).getRecurrence();
		}
		
		
		return LCM.caculateLCM(recurrences);
	}	
}
