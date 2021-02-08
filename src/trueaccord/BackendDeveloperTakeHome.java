package trueaccord;

//all fields
//bool is_in_payment_plan
//double remaining_amount
//ISO 8601 UTC date next_payment_due_date null if there is no payment plan or if the debt has been paid off

public class BackendDeveloperTakeHome {

    public static void main(String[] args) throws Exception {
        AdminTool tool = new AdminTool(null, null, null);
        tool.outputDebtInfo();
    }
    
}
