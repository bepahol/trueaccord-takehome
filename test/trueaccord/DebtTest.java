package trueaccord;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DebtTest {
    
    public DebtTest() {
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
    
    @Test(expected = NullPointerException.class)
    public void testConstructor_NPE() {
        Debt.parse( getDebt3() );
    }
    
    @Test
    public void testConstructor_Valid() {
        String[][] testCases = {
            {getDebt1()},
            {getDebt2()},
        };
        
        Debt expected = new Debt(0, 123.46);
        
        for (String[] testCase : testCases) {
            String json = testCase[0];
            
            assertEquals(expected, Debt.parse(json));
        }
    }
    
    private String getDebt1() {
        return "[{\n" +
"  \"id\": 0,\n" +
"  \"amount\": 123.46\n" +
"}]";
    }
    
    private String getDebt2() {
        return "[{\"id\": 0, \"amount\": 123.46}]";
    }
    
    private String getDebt3() {
        return "[{id: 0, amount: 123.46}]";
    }
    
}
