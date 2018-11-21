package entities;
import org.eclipse.app4mc.amalthea.model.Task;

public class PrioritizedTask {
	private Task task;
	private int recurrence;
	private int duration;
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public int getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(int recurrence) {
		this.recurrence = recurrence;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}