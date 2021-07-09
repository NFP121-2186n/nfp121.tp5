package question1;

public class addTest extends junit.framework.TestCase {
	// test la fonctionalite de 'add'
	public void testAdd() {
		question1.Ensemble<Integer> e1;
		e1 = new question1.Ensemble<Integer>();
		assertEquals(true, e1.add(2));
		assertEquals(false, e1.add(2));
		assertEquals(true, e1.add(3));
		assertEquals(true, e1.add(4));
		assertEquals(false, e1.add(4));

	}
	
}
