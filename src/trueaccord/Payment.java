package trueaccord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import trueaccord.util.JSONUtil;

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
        return parseAsList(json).get(0);
    }
    
    public static List<Payment> parseAsList(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        List<Payment> payments = new ArrayList<>();
        Iterator iter = array.iterator();
        while (iter.hasNext()) {
            JSONObject payment = (JSONObject)iter.next();
            
            Object amount = payment.get("amount");
            double amountAsDouble = JSONUtil.getDoubleValue(amount);
                        
            payments.add( new Payment((Long)payment.get("payment_plan_id"), amountAsDouble, (String)payment.get("date")) );
        }
       
        return payments;
    }
    
    public long getPaymentPlanId() {
        return paymentPlanId;
    }
    
    public double getAmount() {
        return amount;
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
