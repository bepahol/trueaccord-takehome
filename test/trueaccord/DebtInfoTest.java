package trueaccord;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DebtInfoTest {
    
    public DebtInfoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testEquals() {
        Debt debt = new Debt(0, 123.46);
        DebtInfo debtInfo1 = new DebtInfo(debt, true,  100.2, "abc");
        DebtInfo debtInfo2 = new DebtInfo(debt, true,  100.2, "");
        DebtInfo debtInfo3 = new DebtInfo(debt, false, 100.2, "abc");
        
        assertEquals(debtInfo1, debtInfo1);
        assertNotEquals(debtInfo1, debtInfo2);
        assertNotEquals(debtInfo2, debtInfo1);
        assertNotEquals(debtInfo1, debtInfo3);
    }
    
}
