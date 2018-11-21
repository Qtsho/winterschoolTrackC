package schedule;

import org.eclipse.app4mc.amalthea.model.Task;

public class ScheduledTask {
	private Task task;
	private double from;
	private double to;
	
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public double getFrom() {
		return from;
	}
	public void setFrom(double from) {
		this.from = from;
	}
	public double getTo() {
		return to;
	}
	public void setTo(double to) {
		this.to = to;
	}
	
	
	
}
