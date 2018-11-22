package entities;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.util.SoftwareUtil;
import java.util.LinkedList;
import java.util.List;



public class PrioritizedTask {
	private int recurrence;
	private Task task;
	private List<RunnablesDuration> runnables; 
	List<Runnable> ListofRunnables;
	
	
	public PrioritizedTask() {
		super();
		runnables=new LinkedList<>();
	}
	
	public void init() {
		ListofRunnables = SoftwareUtil.getRunnableList(this.task, null);
		for(Runnable r : ListofRunnables) {
			RunnablesDuration x=new RunnablesDuration();
			x.setRunable(r);
			runnables.add(x);
		}
		
		for(RunnablesDuration r : runnables) {
			System.out.println(r.getRunable().getName());
			r.setDuration();
		}
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

	public List<RunnablesDuration> getRunnables() {
		return runnables;
	}
}

