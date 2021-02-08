package trueaccord;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminToolTest {
    
    private List emptyList                   = Arrays.asList();
    private PaymentPlan paymentPlan1         = PaymentPlan.parse(PaymentPlanTest.getPaymentPlan1());
    private List<PaymentPlan> onePaymentPlan = Arrays.asList(paymentPlan1);
    private List<Payment> onePayment         = Arrays.asList(Payment.parse(PaymentTest.getPayment1()));
    
    public AdminToolTest() {
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
    public void testComputeDebtInfo() {
//        List<Debt> debts               = Arrays.asList(new Debt(0, 100));
//        List<PaymentPlan> paymentPlans = Arrays.asList(new PaymentPlan(0, 0, 100, InstallmentFrequency.WEEKLY, 50, "Jan 1, 2021"));
//        List<Payment> payments         = Arrays.asList(new Payment());
        
        Debt debt = new Debt(0, 100);
        List<Debt> debts               = Arrays.asList(debt);
        List<PaymentPlan> paymentPlans = Arrays.asList(new PaymentPlan(0, 0, 100, InstallmentFrequency.WEEKLY, 50, "Jan 1, 2021"));
        List<Payment> payments         = Arrays.asList(new Payment(0, 50, "Jan 5, 2021"), new Payment(0, 50, "Jan 12, 2021"));
        
        AdminTool adminTool = new AdminTool(debts, paymentPlans, payments);
        adminTool.computeDebtInfo();
        
        DebtInfo expectedDebtInfo = new DebtInfo(debt, true, 0, null);
        assertEquals(1, adminTool.getDebtInfos().size());
        assertEquals(expectedDebtInfo, adminTool.getDebtInfos().get(0));
    }
    
    @Test
    public void testFilterPaymentPlans() {
        Object[][] testCases = {
            {new Debt(0, 0), emptyList,      null},
            {new Debt(0, 0), onePaymentPlan, paymentPlan1},
            {new Debt(1, 0), onePaymentPlan, null},
        };
        
        for (Object[] testCase : testCases) {
            Debt debt                       =              (Debt)testCase[0];
            List<PaymentPlan> paymentPlans  = (List<PaymentPlan>)testCase[1];
            PaymentPlan expectedPaymentPlan =       (PaymentPlan)testCase[2];
            
            assertEquals(expectedPaymentPlan, AdminTool.getPaymentPlan(debt, paymentPlans));
        }
    }
    
    @Test
    public void testFilterPayments() {
        Object[][] testCases = {
            {null,         emptyList,  emptyList},
            {null,         onePayment, emptyList},
            {paymentPlan1, onePayment, onePayment},
            {paymentPlan1, emptyList,  emptyList},
        };
        
        for (Object[] testCase : testCases) {
            PaymentPlan paymentPlan        =   (PaymentPlan)testCase[0];
            List<Payment> payments         = (List<Payment>)testCase[1];
            List<Payment> expectedPayments = (List<Payment>)testCase[2];
            
            assertEquals(expectedPayments, AdminTool.filterPayments(paymentPlan, payments));
        }
    }
    
}
