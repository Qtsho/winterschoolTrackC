package schedule;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.custom.ST;

import entities.PrioritizedTask;
import schedule.dummyData.TaskList;

public class Schedule {

	public static void main(String[] args) {
		List<PrioritizedTask> taskList = TaskList.buildDummyTaskList();
		
		List<ScheduledTask> scheduledTasks = new LinkedList<ScheduledTask>();
		
		// Actually the LCM-Function would be called to produce the 60
		int hyperperiod = 60;
		
		PrioritizedTask t1 = taskList.get(0);
		
		ScheduledTask st1 = new ScheduledTask();
		st1.setFrom(0);
		st1.setTo(3);
		scheduledTasks.add(st1);
		

		ScheduledTask st1 = new ScheduledTask();
		st1.setFrom(0);
		st1.setTo(3);
		scheduledTasks.add(st1);
		

		
		
		
	}
}
