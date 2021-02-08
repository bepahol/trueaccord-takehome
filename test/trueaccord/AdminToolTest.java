package trueaccord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdminToolTest {
    
    private Payment payment1 = Payment.parse(PaymentTest.getPayment1());
    private Payment payment2 = new Payment(0, 0,     "");
    private Payment payment3 = new Payment(0, 10.28, "");
    
    private List emptyList                   = Arrays.asList();
    private PaymentPlan paymentPlan1         = PaymentPlan.parse(PaymentPlanTest.getPaymentPlan1());
    private PaymentPlan paymentPlan2         = PaymentPlan.parse(PaymentPlanTest.getPaymentPlan1());
    private List<PaymentPlan> onePaymentPlan = Arrays.asList(paymentPlan1);
    private List<Payment> onePayment         = Arrays.asList(payment1);
    private List<Payment> twoPaymentsSame    = Arrays.asList(payment1, payment1);
    private List<Payment> twoPaymentsDiff    = Arrays.asList(payment1, payment2);
    private List<Payment> threePayments      = Arrays.asList(payment1, payment2, payment3);
    
    public AdminToolTest() {
        paymentPlan1.setStartDate("2020-09-28");
        paymentPlan2.setInstallmentFrequency(InstallmentFrequency.BI_WEEKLY);
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
        Debt debt = new Debt(0, 100);
        List<Debt> debts               = Arrays.asList(debt);
        List<PaymentPlan> paymentPlans = emptyList;
        List<Payment> payments         = new ArrayList<>();
        
        checkComputeDebtInfo(debts, paymentPlans, payments, new DebtInfo(debt, false, 100, ""));
        
        // add payment plan but no payment yet
        paymentPlans = Arrays.asList(new PaymentPlan(0, 0, 100, InstallmentFrequency.WEEKLY, 50, "2020-09-28"));
        checkComputeDebtInfo(debts, paymentPlans, payments, new DebtInfo(debt, true, 100, "2020-09-28"));
        
        // first payment
        payments.add(new Payment(0, 50, "Jan 5, 2021"));
        checkComputeDebtInfo(debts, paymentPlans, payments, new DebtInfo(debt, true, 50, "2020-10-05"));
        
        // second payment
        payments.add(new Payment(0, 50, "Jan 12, 2021"));
        checkComputeDebtInfo(debts, paymentPlans, payments, new DebtInfo(debt, true, 0, ""));
    }
    
    private void checkComputeDebtInfo(List<Debt> debts, List<PaymentPlan> paymentPlans, List<Payment> payments, DebtInfo expectedDebtInfo) {
        AdminTool adminTool = new AdminTool(debts, paymentPlans, payments);
        adminTool.computeDebtInfo();
        
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
    
    @Test
    public void testGetRemainingAmount() {
        Object[][] testCases = {
            {paymentPlan1, emptyList, paymentPlan1.getAmountToPay()},
            {paymentPlan1, onePayment,      51.25},
            {paymentPlan1, twoPaymentsSame, 0.0},
            {paymentPlan1, twoPaymentsDiff, 51.25},
            {paymentPlan1, threePayments,   40.97},
        };
        
        for (Object[] testCase : testCases) {
            PaymentPlan paymentPlan        =   (PaymentPlan)testCase[0];
            List<Payment> payments         = (List<Payment>)testCase[1];
            double expectedRemainingAmount =        (double)testCase[2];
            
            double delta = 0;
            assertEquals(expectedRemainingAmount, AdminTool.getRemainingAmount(paymentPlan, payments), delta);
        }
    }
    
    @Test
    public void testGetNextPaymentDueDate() {  
        Object[][] testCases = {
            {paymentPlan1, emptyList, paymentPlan1.getStartDate()},
            {paymentPlan1, onePayment, "2020-10-05"},
//            {paymentPlan1, twoPaymentsSame, 0.0},
//            {paymentPlan1, twoPaymentsDiff, 51.25},
//            {paymentPlan1, threePayments,   40.97},
        };
        
        for (Object[] testCase : testCases) {
            PaymentPlan paymentPlan           =   (PaymentPlan)testCase[0];
            List<Payment> payments            = (List<Payment>)testCase[1];
            String expectedNextPaymentDueDate =        (String)testCase[2];
            
            assertEquals(expectedNextPaymentDueDate, AdminTool.getNextPaymentDueDate(paymentPlan, payments));
        }
    }
    
}
