package trueaccord.util;

import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EndpointConsumerTest {
    
    public EndpointConsumerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGet() throws Exception {
        Object[][] testCases = {
            {"https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts",         getDebtsJson()},
            {"https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans", getPaymentPlansJson()},
            {"https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments",      getPaymentsJson()},
        };
        
//        JSONParser parser = new JSONParser();
//        JSONArray array = (JSONArray)parser.parse( getDebtsJson() );
//        System.out.println(array);
//        
//        array = (JSONArray)JSONValue.parse(getDebtsJson() ); 
//        System.out.println(array);
        
        for (Object[] testCase : testCases) {
            String urlEndpoint      = (String)testCase[0];
            String expectedJsonData = (String)testCase[1];
            
            assertEquals(JSONValue.parse(expectedJsonData), EndpointConsumer.getJson(urlEndpoint));
        }
    }
    
    public static String getDebtsJson() {
        return "[\n" +
"  {\n" +
"    \"amount\": 123.46,\n" +
"    \"id\": 0\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 100,\n" +
"    \"id\": 1\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 4920.34,\n" +
"    \"id\": 2\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 12938,\n" +
"    \"id\": 3\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 9238.02,\n" +
"    \"id\": 4\n" +
"  }\n" +
"]";
    }
    
    public static String getPaymentPlansJson() {
        return "[\n" +
"  {\n" +
"    \"amount_to_pay\": 102.5,\n" +
"    \"debt_id\": 0,\n" +
"    \"id\": 0,\n" +
"    \"installment_amount\": 51.25,\n" +
"    \"installment_frequency\": \"WEEKLY\",\n" +
"    \"start_date\": \"2020-09-28\"\n" +
"  },\n" +
"  {\n" +
"    \"amount_to_pay\": 100,\n" +
"    \"debt_id\": 1,\n" +
"    \"id\": 1,\n" +
"    \"installment_amount\": 25,\n" +
"    \"installment_frequency\": \"WEEKLY\",\n" +
"    \"start_date\": \"2020-08-01\"\n" +
"  },\n" +
"  {\n" +
"    \"amount_to_pay\": 4920.34,\n" +
"    \"debt_id\": 2,\n" +
"    \"id\": 2,\n" +
"    \"installment_amount\": 1230.085,\n" +
"    \"installment_frequency\": \"BI_WEEKLY\",\n" +
"    \"start_date\": \"2020-01-01\"\n" +
"  },\n" +
"  {\n" +
"    \"amount_to_pay\": 4312.67,\n" +
"    \"debt_id\": 3,\n" +
"    \"id\": 3,\n" +
"    \"installment_amount\": 1230.085,\n" +
"    \"installment_frequency\": \"WEEKLY\",\n" +
"    \"start_date\": \"2020-08-01\"\n" +
"  }\n" +
"]";
    }
    
    public static String getPaymentsJson() {
        return "[\n" +
"  {\n" +
"    \"amount\": 51.25,\n" +
"    \"date\": \"2020-09-29\",\n" +
"    \"payment_plan_id\": 0\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 51.25,\n" +
"    \"date\": \"2020-10-29\",\n" +
"    \"payment_plan_id\": 0\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 25,\n" +
"    \"date\": \"2020-08-08\",\n" +
"    \"payment_plan_id\": 1\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 25,\n" +
"    \"date\": \"2020-08-08\",\n" +
"    \"payment_plan_id\": 1\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 4312.67,\n" +
"    \"date\": \"2020-08-08\",\n" +
"    \"payment_plan_id\": 2\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 1230.085,\n" +
"    \"date\": \"2020-08-01\",\n" +
"    \"payment_plan_id\": 3\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 1230.085,\n" +
"    \"date\": \"2020-08-08\",\n" +
"    \"payment_plan_id\": 3\n" +
"  },\n" +
"  {\n" +
"    \"amount\": 1230.085,\n" +
"    \"date\": \"2020-08-15\",\n" +
"    \"payment_plan_id\": 3\n" +
"  }\n" +
"]";
    }
}
