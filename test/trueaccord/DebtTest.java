package trueaccord;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trueaccord.util.EndpointConsumerTest;

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
    
    @Test
    public void testParse() {
        List<Debt> expectedDebts = new ArrayList<>();
        expectedDebts.add(new Debt(0, 123.46));
        expectedDebts.add(new Debt(1, 100));
        expectedDebts.add(new Debt(2, 4920.34));
        expectedDebts.add(new Debt(3, 12938));
        expectedDebts.add(new Debt(4, 9238.02));
        
        assertEquals(expectedDebts, Debt.parseAsList( EndpointConsumerTest.getDebtsJson()));
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
