package trueaccord;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentTest {
    
    public PaymentTest() {
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
        Debt.parse( getPayment3() );
    }
    
    @Test
    public void testConstructor_Valid() {
        String[][] testCases = {
            {getPayment1()},
            {getPayment2()},
        };
        
        Payment expected = new Payment(0, 51.25, "2020-09-29T17:19:31Z");
        
        for (String[] testCase : testCases) {
            String json = testCase[0];
            
            assertEquals(expected, Payment.parse(json));
        }
    }
    
    private String getPayment1() {
        return "[{\n" +
"  \"payment_plan_id\": 0,\n" +
"  \"amount\": 51.25,\n" +
"  \"date\": \"2020-09-29T17:19:31Z\"\n" +
"}]";
    }
    
    private String getPayment2() {
        return "[{\"payment_plan_id\": 0, \"amount\": 51.25, \"date\": \"2020-09-29T17:19:31Z\"}]";
    }
    
    private String getPayment3() {
        return "[{payment_plan_id: 0, amount: 51.25, date: 2020-09-29T17:19:31Z}]";
    }
}
