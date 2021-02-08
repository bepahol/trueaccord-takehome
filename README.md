## How to run

1. Java 8

### Netbeans
Easiest way is to load this repo into Netbeans and run BackendDeveloperTakeHome.java

### Commandline
#### Src
FIXME
#### Test
FIXME

## What I would do if I had more time

- Add more comments
- More thorough testing for null checks, boundary cases, invalid inputs/cases, run scenarios, etc
- Add javadocs, for like constructors, add descriptions, etc i.e. documentation
- Check endpoint API that I'm consuming - make sure all preconditions are true
    i.e. check PaymentPlan really is One-to-one with debt. 
- Optimize some of my data structures
    i.e. things like, tweak arraylist init sizes
- Optimize code
    i.e. shortcircuit paymentplans if they are empty and not call filterPayments
- Rename some things like DebtInfos or find better readable names
- Add more helper/util methods
- Maybe change in AdminTool the params of some functions, like some of the filters methods, so that the params feels good on the handoff, feels right, feels clean
- Maybe put back filteredPaymentPlans(list), requirement is that there's only one paymentplan, but in future (extensibility) this could change

## Problems/Assumptions

1. Assumption: endpoint APIs returned back ids and startdates in ascending order
2. Not sure how you pay .085 of a dollar
```json
[
  {
    "amount_to_pay": 102.5,
    "debt_id": 0,
    "id": 0,
    "installment_amount": 51.25,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-09-28"
  },
  {
    "amount_to_pay": 100,
    "debt_id": 1,
    "id": 1,
    "installment_amount": 25,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-08-01"
  },
  {
    "amount_to_pay": 4920.34,
    "debt_id": 2,
    "id": 2,
    "installment_amount": 1230.085,
    "installment_frequency": "BI_WEEKLY",
    "start_date": "2020-01-01"
  },
  {
    "amount_to_pay": 4312.67,
    "debt_id": 3,
    "id": 3,
    "installment_amount": 1230.085,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-08-01"
  }
]
```
3. Example payloads have different UTC times than the ACTUAL payloads (yyyy-mm-dd)
```json
[{
  "id": 0,
  "debt_id": 0,
  "amount_to_pay": 102.50,
  "installment_frequency": "WEEKLY",
  "installment_amount": 51.25,
  "start_date": "2020-09-28T16:18:30Z"
}]
```
4. Why the actual payment date is way off from interval and start date on payment plan?
``` json
  {
    "amount_to_pay": 4920.34,
    "debt_id": 2,
    "id": 2,
    "installment_amount": 1230.085,
    "installment_frequency": "BI_WEEKLY",
    "start_date": "2020-01-01"
  },
```
```json
  {
    "amount": 4312.67,
    "date": "2020-08-08",
    "payment_plan_id": 2
  },
```
What to do in this case?

    The doc says "The next payment date can be calculated by using the payment plan start_date, the installment frequency, and any preexisting payments".
    
    My formula for payoff date: start_date + (installmentFrequency * # of payments)
5. Assumption: BI_WEEKLY = twice a month (not twice a week)

## Endpoints
https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans
```json
[
  {
    "amount": 123.46,
    "id": 0
  },
  {
    "amount": 100,
    "id": 1
  },
  {
    "amount": 4920.34,
    "id": 2
  },
  {
    "amount": 12938,
    "id": 3
  },
  {
    "amount": 9238.02,
    "id": 4
  }
]
```
https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans
```json
[
  {
    "amount_to_pay": 102.5,
    "debt_id": 0,
    "id": 0,
    "installment_amount": 51.25,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-09-28"
  },
  {
    "amount_to_pay": 100,
    "debt_id": 1,
    "id": 1,
    "installment_amount": 25,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-08-01"
  },
  {
    "amount_to_pay": 4920.34,
    "debt_id": 2,
    "id": 2,
    "installment_amount": 1230.085,
    "installment_frequency": "BI_WEEKLY",
    "start_date": "2020-01-01"
  },
  {
    "amount_to_pay": 4312.67,
    "debt_id": 3,
    "id": 3,
    "installment_amount": 1230.085,
    "installment_frequency": "WEEKLY",
    "start_date": "2020-08-01"
  }
]
```
https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments
```json
[
  {
    "amount": 51.25,
    "date": "2020-09-29",
    "payment_plan_id": 0
  },
  {
    "amount": 51.25,
    "date": "2020-10-29",
    "payment_plan_id": 0
  },
  {
    "amount": 25,
    "date": "2020-08-08",
    "payment_plan_id": 1
  },
  {
    "amount": 25,
    "date": "2020-08-08",
    "payment_plan_id": 1
  },
  {
    "amount": 4312.67,
    "date": "2020-08-08",
    "payment_plan_id": 2
  },
  {
    "amount": 1230.085,
    "date": "2020-08-01",
    "payment_plan_id": 3
  },
  {
    "amount": 1230.085,
    "date": "2020-08-08",
    "payment_plan_id": 3
  },
  {
    "amount": 1230.085,
    "date": "2020-08-15",
    "payment_plan_id": 3
  }
]
```