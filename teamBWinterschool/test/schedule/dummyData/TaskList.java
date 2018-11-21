package schedule.dummyData;

import java.util.LinkedList;
import java.util.List;

import entities.PrioritizedTask;

public class TaskList {
	public static List<PrioritizedTask> buildDummyTaskList() {
		List<PrioritizedTask> taskList = new LinkedList<>();
		
		PrioritizedTask taskA = new PrioritizedTask();
		taskA.setDuration(3);
		taskA.setRecurrence(15);
		
		PrioritizedTask taskB = new PrioritizedTask();
		taskB.setDuration(5);
		taskB.setRecurrence(20);
		
		PrioritizedTask taskC = new PrioritizedTask();
		taskC.setDuration(7);
		taskC.setRecurrence(30);
		
		taskList.add(taskA);
		taskList.add(taskB);
		taskList.add(taskC);
		
		return taskList;
	}
	
	public static List<PrioritizedTask> buildDummyTaskListOneTask() {
		List<PrioritizedTask> taskList = new LinkedList<>();
		
		PrioritizedTask taskA = new PrioritizedTask();
		taskA.setDuration(3);
		taskA.setRecurrence(15);
	
		taskList.add(taskA);
		
		return taskList;
	}
}
