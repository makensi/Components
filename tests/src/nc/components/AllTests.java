package nc.components;

import junit.framework.Test;
import junit.framework.TestSuite;
import nc.components.activity.IntegerPickerTestActivityTest;

public class AllTests extends TestSuite{
	
	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());

		suite.addTestSuite(IntegerPickerTestActivityTest.class);
		
		return suite;
	}

}
