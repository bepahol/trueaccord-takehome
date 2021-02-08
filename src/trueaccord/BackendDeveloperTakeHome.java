package trueaccord;

import trueaccord.util.EndpointConsumer;

public class BackendDeveloperTakeHome {

    public static void main(String[] args) throws Exception {
        AdminTool tool = new AdminTool(
            Debt.parseAsList(        EndpointConsumer.getJsonString("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts") ), 
            PaymentPlan.parseAsList( EndpointConsumer.getJsonString("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans") ), 
            Payment.parseAsList(     EndpointConsumer.getJsonString("https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments") )
        );
        
        System.out.println("DEBT INFO ------------");
        tool.outputDebtInfo();
    }
    
}
