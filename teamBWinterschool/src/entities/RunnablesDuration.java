package entities;

import java.util.List;

import org.eclipse.app4mc.amalthea.model.ExecutionNeed;
import org.eclipse.app4mc.amalthea.model.Need;
import org.eclipse.app4mc.amalthea.model.NeedConstant;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.util.SoftwareUtil;

public class RunnablesDuration {
	private Runnable runable;
	private int duration;
	private long hahaha;
	
	public Runnable getRunable() {
		return runable;
	}
	public void setRunable(Runnable runable) {
		this.runable = runable;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration() {
		
		List<ExecutionNeed> exeNeedList = SoftwareUtil.getExecutionNeedsList(runable, null);
		for(int i=0; i<exeNeedList.size(); i++) {
			ExecutionNeed exeNeedDur = exeNeedList.get(0);
			Need abc = exeNeedDur.getDefault().get(0).getValue();
			
			if(abc instanceof NeedConstant) {
				hahaha=((NeedConstant)abc).getValue();
				System.out.println(hahaha);
			}
		}
		this.duration=(int)hahaha;
	}
}
