package schedule;

import static org.junit.Assert.*;

import java.util.List;

import javax.sound.midi.Soundbank;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

import entities.PrioritizedTask;
import entities.RunnablesDuration;
import entities.TaskOutOfBoundsException;
import schedule.dummyData.TaskList;

public class TestSchedule {
	
	Schedule schedule;
	List<PrioritizedTask> taskList;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		schedule = new Schedule();
		taskList = TaskList.buildDummyTaskListOneTask();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculateRunnabeForGivenTime() {
		PrioritizedTask task1 = new PrioritizedTask();
		RunnablesDuration run1 = new RunnablesDuration();
		run1.setDuration(5);
		RunnablesDuration run2 = new RunnablesDuration();
		run2.setDuration(5);
		
		task1.getRunnables().add(run1);
		task1.getRunnables().add(run2);
		
		try {
			assertEquals(run1,
					schedule.calculateRunnableForGivenTime(task1, 0));
		} catch (TaskOutOfBoundsException e) {
		}
		

		try {
			assertEquals(run2,
					schedule.calculateRunnableForGivenTime(task1, 5));
		} catch (TaskOutOfBoundsException e) {
		}
	}
	
	@Test
	public void testActivation() {
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		PrioritizedTask task1 = taskList.get(0);
		//PrioritizedTask task2 = taskList.get(1);
		
		
		assertEquals(activation[0][0], task1);
		assertEquals(activation[15][0], task1);
		assertEquals(activation[30][0], task1);
		assertEquals(activation[45][0], task1);
		assertEquals(activation[60][0], task1);
	}
	
	@Test
	public void testScheduling() {
		PrioritizedTask task1 = taskList.get(0);
		RunnablesDuration r1 = taskList.get(0).getRunnables().get(0);
		
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		RunnablesDuration[] actual = schedule.scheduling(activation);
		RunnablesDuration[] expected = new RunnablesDuration[61];
		
		expected[0] = r1;
		expected[1] = r1;
		expected[2] = r1;
		
		expected[15] = r1;
		expected[16] = r1;
		expected[17] = r1;
		
		expected[30] = r1;
		expected[31] = r1;
		expected[32] = r1;
		
		expected[45] = r1;
		expected[46] = r1;
		expected[47] = r1;
		
		expected[60] = r1;
	
		assertArrayEquals(expected, actual);
		
		System.out.println("==================");
		System.out.println("Schedule");
		for(int i = 0; i < actual.length; i++) {
			System.out.println(i + " :: " + actual[i]);
		}
		System.out.println("==================");
	}
	
	@Test
	public void testMultipleTask () {
		taskList = TaskList.buildDummyTaskList();
		PrioritizedTask task1 = taskList.get(0);
		RunnablesDuration r1 = taskList.get(0).getRunnables().get(0);
		
		PrioritizedTask task2 = taskList.get(1);
		RunnablesDuration r2 = taskList.get(1).getRunnables().get(0);
		
		PrioritizedTask task3 = taskList.get(2);
		RunnablesDuration r3 = taskList.get(2).getRunnables().get(0);
		
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		RunnablesDuration[] actual = schedule.scheduling(activation);
		RunnablesDuration[] expected = new RunnablesDuration[61];
		
		expected[0] = r1;
		expected[1] = r1;
		expected[2] = r1;
		
		expected[15] = r1;
		expected[16] = r1;
		expected[17] = r1;
		
		expected[30] = r1;
		expected[31] = r1;
		expected[32] = r1;
		
		expected[45] = r1;
		expected[46] = r1;
		expected[47] = r1;
		
		expected[60] = r1;
		
		//Task 2
		expected[3] = r2;
		expected[4] = r2;
		expected[5] = r2;
		expected[6] = r2;
		expected[7] = r2;
		
		expected[20] = r2;
		expected[21] = r2;
		expected[22] = r2;
		expected[23] = r2;
		expected[24] = r2;
		
		expected[40] = r2;
		expected[41] = r2;
		expected[42] = r2;
		expected[43] = r2;
		expected[44] = r2;
		
		//Task 3
		expected[8] = r3;
		expected[9] = r3;
		expected[10] = r3;
		expected[11] = r3;
		expected[12] = r3;
		expected[13] = r3;
		expected[14] = r3;
		
		expected[33] = r3;
		expected[34] = r3;
		expected[35] = r3;
		expected[36] = r3;
		expected[37] = r3;
		expected[38] = r3;
		expected[39] = r3;
		
		System.out.println("Multiple=========");
		System.out.println("Schedule");
		for(int i = 0; i < actual.length; i++) {
			System.out.println(i + " :: " + actual[i]);
		}
		System.out.println("==================");
		
		assertArrayEquals(expected, actual);
		
	}

}
