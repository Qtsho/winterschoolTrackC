
package modelExtraction;
import entities.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;
import org.eclipse.app4mc.amalthea.model.ExecutionNeed;
import org.eclipse.app4mc.amalthea.model.Frequency;
import org.eclipse.app4mc.amalthea.model.HWModel;
import org.eclipse.app4mc.amalthea.model.Need;
import org.eclipse.app4mc.amalthea.model.NeedConstant;
import org.eclipse.app4mc.amalthea.model.PeriodicStimulus;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.SWModel;
import org.eclipse.app4mc.amalthea.model.ConstraintsModel;
import org.eclipse.app4mc.amalthea.model.EventChain;
import org.eclipse.app4mc.amalthea.model.Stimulus;
import org.eclipse.app4mc.amalthea.model.Tag;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaLoader;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaWriter;
import org.eclipse.app4mc.amalthea.model.util.ModelUtil;
import org.eclipse.app4mc.amalthea.model.util.SoftwareUtil;
import org.eclipse.app4mc.amalthea.model.util.HardwareUtil;

import org.eclipse.emf.common.util.EList;

public class ModelExtractor {

	public static void main(String[] args) {
		ModelExtractor modelExtractor = new ModelExtractor();
		modelExtractor.calculatePrioritizedTaskList();
	}
	
	public PrioritizedTask[] calculatePrioritizedTaskList() {

		// example: absolute path
		// final File inputFile = new File("d:/temp/democar.amxmi");
		// final File outputFile = new File("d:/temp/democar_1.amxmi");

		// example: relative path
		final File inputFile = new File("Model/simpleModel.amxmi");
		final File outputFile = new File("model-output/LoadModifySave/democar_1.amxmi");

		// ***** Load *****

		Amalthea model = AmaltheaLoader.loadFromFile(inputFile);

		if (model == null) {
			throw new IllegalArgumentException("Error: No model loaded!");
		}
		
		HWModel hwm = model.getHwModel();
		
		// ***** Modify *****

		// Used to create new objects within th eamalthea model.
		final AmaltheaFactory fac = AmaltheaFactory.eINSTANCE;
		
		
		EList<Stimulus> stimuliList = model.getStimuliModel().getStimuli();
		for(Stimulus s : stimuliList) {
			if(s instanceof PeriodicStimulus) {
				((PeriodicStimulus) s).getRecurrence();
			}
		}
		
		SWModel swm = model.getSwModel();
		
		EList<Task> taskList = swm.getTasks();
		PrioritizedTask[] priorList = new PrioritizedTask[taskList.size()];
		
		  
		int flag0=0;
		for(Task t : taskList) {
			priorList[flag0] = new PrioritizedTask();
			priorList[flag0].setTask(t);
			
			t.getStimuli().get(0);
			flag0++;
		}
		System.out.println(priorList[0]);
		
		
		/// making a integer array with using stimuli data from the tasks
		int[] array0=new int[priorList.length];
		int flag=0;
		
		for(int i=0; i<priorList.length; i++) {
			String str="";
			str=priorList[i].getTask().getStimuli().get(0).toString();
			String str0=str.substring(85, str.length()-3);
			int x= Integer.parseInt(str0);
			
			array0[flag]=x;
			flag++;	
			
		}
		flag=0;
		for(int i=0; i<priorList.length; i++) {
			System.out.println(array0[i]);
		}
		
		/// making a bubble sort with using task stimuli data
		for(int i=0; i< priorList.length; i++) {
			for(int j=1; j<priorList.length-i; j++) {
				if(array0[j-1]>array0[j]) {
					int temp=array0[j-1];
					array0[j-1]=array0[j];
					array0[j]=temp;
				}
			}	
		}
		
		
		/// assigning tasks in order of bubble sort result
		for(int i=0; i<priorList.length; i++) {
			for(int j=0; j<array0.length; j++) {
				String str1=taskList.get(j).getStimuli().get(0).toString();
				String pstr1=str1.substring(85, str1.length()-3);
				
				int param=Integer.parseInt(pstr1);
				if(array0[i]==param) {
					priorList[i].setTask(taskList.get(j));
					priorList[i].init();
				}
			}
		}
		
		
		// Testing the List //
		for(int i=0; i<priorList.length; i++) {
			
			//System.out.println(taskOdered.get(i).getName());
			priorList[i].setRecurrence();
		
		}
		//
		// System.out.println(priorList[0].getRunnables().get(0).getDuration());
		

		System.out.println("done");
		
		return priorList;
	}

}
