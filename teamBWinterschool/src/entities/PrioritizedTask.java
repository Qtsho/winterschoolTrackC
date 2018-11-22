package entities;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.SWModel;

public class PrioritizedTask {
	private int recurrence;
	private double duration; // hi
	private Task task;
	private SWModel swm;
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public int getRecurrence() {
		return recurrence;
	}
	public void setRecurrence() {
		String str="";
		str= task.getStimuli().get(0).toString();
		String str0=str.substring(85, str.length()-3);
		int x= Integer.parseInt(str0);
		this.recurrence = x;
		
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
}