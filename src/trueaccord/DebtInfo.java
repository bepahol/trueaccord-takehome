package trueaccord;

public class DebtInfo {
    
    private Debt debt;
    private boolean isInPaymentPlan;
    private String nextPaymentDueDate;
    
    public DebtInfo(Debt debt, boolean isInPaymentPlan, String nextPaymentDueDate) {
        this.debt = debt;
        this.isInPaymentPlan = isInPaymentPlan;
        this.nextPaymentDueDate = nextPaymentDueDate;
    }

    @Override
    public int hashCode() {
        return debt.hashCode() ^ Boolean.hashCode(isInPaymentPlan) ^ nextPaymentDueDate.hashCode();
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
                this.nextPaymentDueDate.equals(other.nextPaymentDueDate);
    }
}
