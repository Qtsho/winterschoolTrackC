package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import latency.TestLatency;
import schedule.TestPrioritizedTaskComparator;
import schedule.TestSchedule;
import test.lcm.TestLcm;

@RunWith(Suite.class)
@SuiteClasses({TestLatency.class, TestPrioritizedTaskComparator.class, TestSchedule.class, TestLcm.class})
public class AllTests {
	
}
