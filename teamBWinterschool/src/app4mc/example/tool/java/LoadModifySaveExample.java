/**
 ********************************************************************************
 * Copyright (c) 2018 Robert Bosch GmbH.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Robert Bosch GmbH - initial API and implementation
 ********************************************************************************
 */

package app4mc.example.tool.java;

import java.io.File;
import java.util.LinkedList;

import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;
import org.eclipse.app4mc.amalthea.model.PeriodicStimulus;
import org.eclipse.app4mc.amalthea.model.Stimulus;
import org.eclipse.app4mc.amalthea.model.Tag;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaLoader;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaWriter;
import org.eclipse.app4mc.amalthea.model.util.ModelUtil;
import org.eclipse.emf.common.util.EList;

public class LoadModifySaveExample {

	public static void main(String[] args) {

		// example: absolute path
		// final File inputFile = new File("d:/temp/democar.amxmi");
		// final File outputFile = new File("d:/temp/democar_1.amxmi");

		// example: relative path
		final File inputFile = new File("Model/simpleModel.amxmi");
		final File outputFile = new File("model-output/LoadModifySave/democar_1.amxmi");

		// ***** Load *****

		Amalthea model = AmaltheaLoader.loadFromFile(inputFile);

		if (model == null) {
			System.out.println("Error: No model loaded!");
			return;
		}
		
		// ***** Modify *****

		// Used to create new objects within th eamalthea model.
		final AmaltheaFactory fac = AmaltheaFactory.eINSTANCE;
		
		
		EList<Stimulus> stimuliList = model.getStimuliModel().getStimuli();
		for(Stimulus s : stimuliList) {
			if(s instanceof PeriodicStimulus) {
				((PeriodicStimulus) s).getRecurrence();
			}
		}
		
		EList<Task> taskList = model.getSwModel().getTasks();
		for(Task t : taskList) {
			t.getStimuli().get(0);
		}
		
		LinkedList<Task> taskOdered = new LinkedList<>();
		
		
		int[] array0=new int[taskList.size()];
		int flag=0;
		for(Task t : taskList) {
			String str="";
			str=t.getStimuli().get(0).toString();
			String str0=str.substring(85, str.length()-3);
			int x= Integer.parseInt(str0);
			
			array0[flag]=x;
			flag++;	
		}
		flag=0;
		for(int i=0; i<taskList.size(); i++) {
			System.out.println(array0[i]);
		}
		
		for(int i=0; i<taskList.size(); i++) {
			for(int j=1; j<taskList.size()-i; j++) {
				String str2=taskList.get(j).getStimuli().get(0).toString();
				String pstr=str2.substring(85, str2.length()-3);
				
			}	
		}
		
		
		System.out.println("Size of linked list = " + taskOdered.size()); 
		
		
		/*
		Tag tag = fac.createTag();
		tag.setName("The new tag!");
		ModelUtil.getOrCreateCommonElements(model).getTags().add(tag);
		 */

		// ***** Save *****

		AmaltheaWriter.writeToFile(model, outputFile);

		System.out.println("done");
	}

}
