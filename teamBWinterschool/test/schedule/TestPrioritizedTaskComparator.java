package schedule;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.PrioritizedTask;
import entities.PrioritizedTaskComparator;
import schedule.dummyData.TaskList;

public class TestPrioritizedTaskComparator {
	
	Scheduler schedule;
	List<PrioritizedTask> taskList;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		schedule = new Scheduler();
		taskList = TaskList.buildDummyTaskListOneTask();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLess() {
		PrioritizedTask task1 = new PrioritizedTask();
		PrioritizedTask task2 = new PrioritizedTask();
		PrioritizedTaskComparator comp = new PrioritizedTaskComparator();
		
		task1.setRecurrence(5);
		task2.setRecurrence(3);
		
		assertEquals(1, comp.compare(task1, task2));
	}
	
	@Test
	public void testGreater() {
		PrioritizedTask task1 = new PrioritizedTask();
		PrioritizedTask task2 = new PrioritizedTask();
		PrioritizedTaskComparator comp = new PrioritizedTaskComparator();
		
		task1.setRecurrence(5);
		task2.setRecurrence(6);
		
		assertEquals(-1, comp.compare(task1, task2));
	}
	
	@Test
	public void testEqual() {
		PrioritizedTask task1 = new PrioritizedTask();
		PrioritizedTask task2 = new PrioritizedTask();
		PrioritizedTaskComparator comp = new PrioritizedTaskComparator();
		
		task1.setRecurrence(5);
		task2.setRecurrence(5);
		
		assertEquals(0, comp.compare(task1, task2));
	}
}