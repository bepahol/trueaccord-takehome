package trueaccord;

public class DebtInfo {
    
    private Debt debt;
    private boolean isInPaymentPlan;
    private double remainingAmount;
    private String nextPaymentDueDate;
    
    public DebtInfo(Debt debt, boolean isInPaymentPlan, double remainingAmount, String nextPaymentDueDate) {
        this.debt = debt;
        this.isInPaymentPlan = isInPaymentPlan;
        this.remainingAmount = remainingAmount;
        this.nextPaymentDueDate = nextPaymentDueDate;
    }

    @Override
    public int hashCode() {
        return debt.hashCode() ^ Boolean.hashCode(isInPaymentPlan) ^ Double.hashCode(remainingAmount) ^ nextPaymentDueDate.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }
        
        DebtInfo other = (DebtInfo) o;
        return this.debt == other.debt && 
                this.isInPaymentPlan == other.isInPaymentPlan &&
                this.remainingAmount == other.remainingAmount &&
                this.nextPaymentDueDate.equals(other.nextPaymentDueDate);
    }
}
