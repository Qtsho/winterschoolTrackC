package entities;

import java.util.Comparator;

public class PrioritizedTaskComparator implements Comparator<PrioritizedTask>{

	@Override
	public int compare(PrioritizedTask task1, PrioritizedTask task2) {
		// TODO Auto-generated method stub
		if (task1.getRecurrence() > task2.getRecurrence()) {
			return 1;
		} else if (task1.getRecurrence() < task2.getRecurrence()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
