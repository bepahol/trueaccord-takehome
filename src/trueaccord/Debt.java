package trueaccord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import trueaccord.util.JSONUtil;

public class Debt {
    
    private long id;
    private double amount;
    
    public Debt(long id, double amount) {
        this.id = id;
        this.amount = amount;
    }
    
    public static Debt parse(String json) {
        return parseAsList(json).get(0);
    }
    
    public static List<Debt> parseAsList(String json) {
        JSONArray array = (JSONArray)JSONValue.parse(json);
        List<Debt> debts = new ArrayList<>();
        Iterator iter = array.iterator();
        while (iter.hasNext()) {
            JSONObject debt = (JSONObject)iter.next();
            
            Object amount = debt.get("amount");
            double amountAsDouble = JSONUtil.getDoubleValue(amount);
            
            debts.add( new Debt((Long)debt.get("id"), amountAsDouble) );
        }
       
        return debts;
    }
    
    public long getId() {
        return id;
    }
    
    public double getAmount() {
        return amount;
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
