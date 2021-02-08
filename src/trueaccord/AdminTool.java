package trueaccord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminTool {
    
    private List<Debt> debts;
    private List<PaymentPlan> paymentPlans;
    private List<Payment> payments;
    
    private List<DebtInfo> debtInfos;
    
    public AdminTool(List<Debt> debts, List<PaymentPlan> paymentPlans, List<Payment> payments) {
        this.debts = debts;
        this.paymentPlans = paymentPlans;
        this.payments = payments;
        
        this.debtInfos = new ArrayList<>();
    }
    
    public void outputDebtInfo() {
        computeDebtInfo();
        printDebtInfo();
    }
    
    public void computeDebtInfo() {
        for (Debt debt : debts) {
            computeDebtInfo(debt, paymentPlans, payments);
        }
    }
    
    private void computeDebtInfo(Debt debt, List<PaymentPlan> paymentPlans, List<Payment> payments) {
        List<PaymentPlan> filteredPaymentPlans = filterPaymentPlans(debt, paymentPlans);
        List<Payment>     filteredPayments     = filterPayments(filteredPaymentPlans, payments);
        
        boolean isInPaymentPlan = !filteredPaymentPlans.isEmpty();
//        double remainingAmount = getRemainingAmount()
//        debtInfos.add(new DebtInfo(debt, isInPaymentPlan, 0, nextPaymentDueDate))
    }
    
    public void printDebtInfo() {
        for (DebtInfo debtInfo : debtInfos) 
            System.out.println(debtInfo);
    }
    
    public List<DebtInfo> getDebtInfos() {
        return debtInfos;
    }
    
    public static List<PaymentPlan> filterPaymentPlans(Debt debt, List<PaymentPlan> paymentPlans) {
        return paymentPlans.stream().filter(paymentPlan -> debt.getId() == paymentPlan.getDebtId()).collect(Collectors.toList());
    }
    
    public static List<Payment> filterPayments(List<PaymentPlan> paymentPlans, List<Payment> payments) {
        List<Payment> filteredPayments = new ArrayList<>();
        
        for (PaymentPlan paymentPlan : paymentPlans)
            filteredPayments.addAll( filterPayment(paymentPlan, payments) );
        
        return filteredPayments;
    }
    
    public static List<Payment> filterPayment(PaymentPlan paymentPlan, List<Payment> payments) {
        return payments.stream().filter(payment -> paymentPlan.getId() == payment.getPaymentPlanId()).collect(Collectors.toList());
    }
}
