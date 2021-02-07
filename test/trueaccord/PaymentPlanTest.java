package trueaccord;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentPlanTest {
    
    public PaymentPlanTest() {
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
        PaymentPlan.parse( getPaymentPlan3() );
    }
    
    @Test
    public void testConstructor_Valid() {
        String[][] testCases = {
            {getPaymentPlan1()},
            {getPaymentPlan2()},
        };
        
        PaymentPlan expected = new PaymentPlan(0, 0, 102.50, InstallmentFrequency.WEEKLY, 51.25, "2020-09-28T16:18:30Z");
        
        for (String[] testCase : testCases) {
            String json = testCase[0];
            
            assertEquals(expected, PaymentPlan.parse(json));
        }
    }
    
    private String getPaymentPlan1() {
        return "[{\n" +
"  \"id\": 0,\n" +
"  \"debt_id\": 0,\n" +
"  \"amount_to_pay\": 102.50,\n" +
"  \"installment_frequency\": \"WEEKLY\",\n" +
"  \"installment_amount\": 51.25,\n" +
"  \"start_date\": \"2020-09-28T16:18:30Z\"\n" +
"}]";
    }
    
    private String getPaymentPlan2() {
        return "[{\"id\": 0, \"debt_id\": 0, \"amount_to_pay\": 102.50, \"installment_frequency\": \"WEEKLY\", \"installment_amount\": 51.25, \"start_date\": \"2020-09-28T16:18:30Z\"}]";
    }
    
    private String getPaymentPlan3() {
        return "[{id: 0, debt_id: 0, amount_to_pay: 102.50, installment_frequency: WEEKLY, installment_amount: 51.25, start_date: 2020-09-28T16:18:30Z}]";
    }    
}
