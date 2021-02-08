package trueaccord;

import java.util.ArrayList;
import java.util.Arrays;
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
        PaymentPlan paymentPlan = getPaymentPlan(debt, paymentPlans);
        List<Payment> filteredPayments = filterPayments(paymentPlan, payments);
        
        boolean isInPaymentPlan = paymentPlan != null;
        double remainingAmount = (!isInPaymentPlan)? debt.getAmount() : getRemainingAmount(paymentPlan, filteredPayments);
//        String nextPaymentDueDate = (!isInPaymentPlan || remainingAmount == 0)? null : getNextPaymentDueDate(paymentPlan, filteredPayments);
//        debtInfos.add(new DebtInfo(debt, isInPaymentPlan, remainingAmount, nextPaymentDueDate));
    }
    
    public void printDebtInfo() {
        for (DebtInfo debtInfo : debtInfos) 
            System.out.println(debtInfo);
    }
    
    public List<DebtInfo> getDebtInfos() {
        return debtInfos;
    }
    
    public static PaymentPlan getPaymentPlan(Debt debt, List<PaymentPlan> paymentPlans) {
        for (PaymentPlan paymentPlan : paymentPlans)
            if (debt.getId() == paymentPlan.getDebtId()) 
                return paymentPlan;
        
        return null;
    }
    
    public static List<Payment> filterPayments(PaymentPlan paymentPlan, List<Payment> payments) {
        if (paymentPlan == null)
            return new ArrayList<>();
                    
        return payments.stream().filter(payment -> paymentPlan.getId() == payment.getPaymentPlanId()).collect(Collectors.toList());
    }
    
    public static double getRemainingAmount(PaymentPlan paymentPlan, List<Payment> payments) {
        double totalSoFar = getTotalPaidSoFar(payments);
        return paymentPlan.getAmountToPay() - totalSoFar;
    }
    
    private static double getTotalPaidSoFar(List<Payment> payments) {
        double sum = 0;
        
        for (Payment payment : payments) 
            sum += payment.getAmount();
        
        return sum;
    }
    
    public static String getNextPaymentDueDate() {
        return "";
    }
}
