package schedule;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.PrioritizedTask;
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
	public void testActivation() {
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		PrioritizedTask task1 = taskList.get(0);
		
		assertEquals(activation[0][0], task1);
		assertEquals(activation[15][0], task1);
		assertEquals(activation[30][0], task1);
		assertEquals(activation[45][0], task1);
		assertEquals(activation[60][0], task1);
	}
	
	@Test
	public void testScheduling() {
		PrioritizedTask task1 = taskList.get(0);
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		PrioritizedTask[] actual = schedule.scheduling(activation);
		PrioritizedTask[] expected = new PrioritizedTask[61];
		
		expected[0] = task1;
		expected[1] = task1;
		expected[2] = task1;
		expected[15] = task1;
		expected[16] = task1;
		expected[17] = task1;
		expected[30] = task1;
		expected[31] = task1;
		expected[32] = task1;
		expected[45] = task1;
		expected[46] = task1;
		expected[47] = task1;
		expected[60] = task1;
		
		assertArrayEquals(expected, actual);
		
		
	}

}
