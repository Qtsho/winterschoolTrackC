package schedule;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.custom.ST;

import entities.PrioritizedTask;
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
	
	// TODO Call the LCM Function
	private int calculateHyperperiod() {
		return 60;
	}	
}
