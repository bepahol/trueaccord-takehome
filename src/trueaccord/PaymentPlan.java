package trueaccord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PaymentPlan {
    
    private long id;
    private long debtId;
    private double amountToPay;
    private InstallmentFrequency installmentFrequency;
    private double installmentAmount;
    private String startDate;
    
    public PaymentPlan(long id, long debtId, double amountToPay, InstallmentFrequency installmentFrequency, double installmentAmount, String startDate) {
        this.id = id;
        this.debtId = debtId;
        this.amountToPay = amountToPay;
        this.installmentFrequency = installmentFrequency;
        this.installmentAmount = installmentAmount;
        this.startDate = startDate;
    }
    
    public static PaymentPlan parse(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        JSONObject paymentPlan = (JSONObject)array.get(0);
        
        return new PaymentPlan((Long)paymentPlan.get("id"), (Long)paymentPlan.get("debt_id"), (Double)paymentPlan.get("amount_to_pay"), InstallmentFrequency.valueOf((String)paymentPlan.get("installment_frequency")), (Double)paymentPlan.get("installment_amount"), (String)paymentPlan.get("start_date") );
    }
    
    public long getId() {
        return id;
    }
    
    public long getDebtId() {
        return debtId;
    }
    
    public double getAmountToPay() {
        return amountToPay;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) ^ Long.hashCode(debtId) ^ Double.hashCode(amountToPay) ^ installmentFrequency.hashCode() ^ Double.hashCode(installmentAmount) ^ startDate.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }
        
        PaymentPlan other = (PaymentPlan) o;
        return this.id == other.id && 
                this.debtId == other.debtId &&
                this.amountToPay == other.amountToPay &&
                this.installmentFrequency == other.installmentFrequency &&
                this.installmentAmount == other.installmentAmount &&
                this.startDate.equals(other.startDate);
    }
}
