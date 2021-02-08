package trueaccord;

public enum InstallmentFrequency {
    WEEKLY (7),
    BI_WEEKLY (14);
    
    private final int days;
    
    InstallmentFrequency(int days) {
        this.days = days;
    }

    int days() { 
        return days; 
    }
    
}
