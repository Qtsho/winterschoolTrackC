package schedule.dummyData;

import java.util.LinkedList;
import java.util.List;

import entities.PrioritizedTask;
import entities.RunnablesDuration;

public class TaskList {
	public static List<PrioritizedTask> buildDummyTaskList() {
		List<PrioritizedTask> taskList = new LinkedList<>();
		
		RunnablesDuration r1 = new RunnablesDuration();
		r1.setDuration(3);
		RunnablesDuration r2 = new RunnablesDuration();
		r2.setDuration(5);
		RunnablesDuration r3 = new RunnablesDuration();
		r3.setDuration(7);
		
		
		PrioritizedTask taskA = new PrioritizedTask();
		taskA.setRecurrence(15);
		taskA.getRunnables().add(r1);
		
		PrioritizedTask taskB = new PrioritizedTask();
		taskB.setRecurrence(20);
		taskB.getRunnables().add(r2);
		
		PrioritizedTask taskC = new PrioritizedTask();
		taskC.setRecurrence(30);
		taskC.getRunnables().add(r3);
		
		taskList.add(taskA);
		taskList.add(taskB);
		taskList.add(taskC);
		
		return taskList;
	}
	
	public static List<PrioritizedTask> buildDummyTaskListOneTask() {
		List<PrioritizedTask> taskList = new LinkedList<>();
		
		RunnablesDuration r1 = new RunnablesDuration();
		r1.setDuration(3);

		PrioritizedTask taskA = new PrioritizedTask();
		taskA.setRecurrence(15);
		taskA.getRunnables().add(r1);
	
		taskList.add(taskA);
		
		return taskList;
	}
}
