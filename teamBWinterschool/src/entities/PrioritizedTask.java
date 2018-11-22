package entities;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.SWModel;

import java.util.LinkedList;
import java.util.List;
import org.eclipse.app4mc.amalthea.model.HWModel;


public class PrioritizedTask {
	private int recurrence;
	
	private Task task;
	List<RunnablesDuration> runnables; 
	
	public PrioritizedTask() {
		super();
		runnables=new LinkedList<>();
	}
		
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
}