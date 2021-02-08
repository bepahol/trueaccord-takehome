package trueaccord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Payment {
    
    private long paymentPlanId;
    private double amount;
    private String date;
    
    public Payment(long paymentPlanId, double amount, String date) {
        this.paymentPlanId = paymentPlanId;
        this.amount = amount;
        this.date = date;
    }
    
    public static Payment parse(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        JSONObject payment = (JSONObject)array.get(0);
        
        return new Payment((Long)payment.get("payment_plan_id"), (Double)payment.get("amount"), (String)payment.get("date"));
    }
    
    public long getPaymentPlanId() {
        return paymentPlanId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(paymentPlanId) ^ Double.hashCode(amount) ^ date.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }
        
        Payment other = (Payment) o;
        return this.paymentPlanId == other.paymentPlanId && 
                this.amount == other.amount &&
                this.date.equals(other.date);
    }
}
