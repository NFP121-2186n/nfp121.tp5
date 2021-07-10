package question3;

import java.util.*;

public class Tests extends junit.framework.TestCase {

    public void test1(question3.Factory f) throws Exception {
        Set<Integer> set = (Set<Integer>) f.create();
        for (int i = 20; i > 0; i--)
            set.add(i);
        
        assertEquals(true, set.contains(5));
        assertEquals(false, set.contains(0));        
    }

    public void testCreation() {
        try {
            test1(new TreeSetFactory());
            test1(new HashSetFactory());
            
        } catch (NoSuchMethodError e) {
            fail("NoSuchMethodError : " + e.getMessage());
        } catch (Exception e) {
            fail(" exception inattendue : " + e.getMessage());
        }
    }

}
