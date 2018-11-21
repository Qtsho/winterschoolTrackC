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
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() {
		Schedule schedule = new Schedule();
		List<PrioritizedTask> taskList = TaskList.buildDummyTaskListOneTask();
		PrioritizedTask[][] activation = schedule.calculateActivation(taskList);
		PrioritizedTask task1 = taskList.get(0);
		
		assertEquals(activation[0][0], task1);
		assertEquals(activation[15][0], task1);
		assertEquals(activation[30][0], task1);
		assertEquals(activation[45][0], task1);
		assertEquals(activation[60][0], task1);
	}

}
