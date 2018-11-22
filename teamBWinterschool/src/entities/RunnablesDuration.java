package entities;

import org.eclipse.app4mc.amalthea.model.Runnable;

public class RunnablesDuration {
	private Runnable runable;
	private int duration;
	
	public Runnable getRunable() {
		return runable;
	}
	public void setRunable(Runnable runable) {
		this.runable = runable;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
