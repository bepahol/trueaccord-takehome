package trueaccord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import trueaccord.util.JSONUtil;

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
        return parseAsList(json).get(0);
    }
    
    public static List<PaymentPlan> parseAsList(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        List<PaymentPlan> paymentPlans = new ArrayList<>();
        Iterator iter = array.iterator();
        while (iter.hasNext()) {
            JSONObject paymentPlan = (JSONObject)iter.next();
            
            Object amountToPay = paymentPlan.get("amount_to_pay");
            double amountToPayAsDouble = JSONUtil.getDoubleValue(amountToPay);
            
            Object installmentAmount = paymentPlan.get("installment_amount");
            double installmentAmountAsDouble = JSONUtil.getDoubleValue(installmentAmount);
                        
            paymentPlans.add( new PaymentPlan((Long)paymentPlan.get("id"), (Long)paymentPlan.get("debt_id"), amountToPayAsDouble, InstallmentFrequency.valueOf((String)paymentPlan.get("installment_frequency")), installmentAmountAsDouble, (String)paymentPlan.get("start_date")) );
        }
       
        return paymentPlans;
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
    
    public String getStartDate() {
        return startDate;
    }
    
    public InstallmentFrequency getInstallmentFrequency() {
        return installmentFrequency;
    }
    
    public void setInstallmentFrequency(InstallmentFrequency installmentFrequency) {
        this.installmentFrequency = installmentFrequency;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
