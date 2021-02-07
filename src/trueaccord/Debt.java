package trueaccord;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Debt {
    
    private long id;
    private double amount;
    
    public Debt(long id, double amount) {
        this.id = id;
        this.amount = amount;
    }
    
    public static Debt parse(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        JSONObject debt = (JSONObject)array.get(0);
        
        return new Debt((Long)debt.get("id"), (Double)debt.get("amount"));
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id) ^ Double.hashCode(amount);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }
        
        Debt other = (Debt) o;
        return this.id == other.id && this.amount == other.amount;
    }
}
