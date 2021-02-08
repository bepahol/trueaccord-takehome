package trueaccord.util;

public class JSONUtil {
    
    public static double getDoubleValue(Object value) {
        double valueAsDouble;
        if (value instanceof Long) {
            long longAmount = (Long)value;
            valueAsDouble = longAmount;
        }
        else {
            valueAsDouble = (Double)value;
        }
        
        return valueAsDouble;
    }
    
}
