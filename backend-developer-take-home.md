# TrueAccord Take Home

Please complete the following exercise at your leisure. There is no time limit or language preference. Use whatever languages, tools, and dependencies you are most comfortable with. We will be assessing the code based off correctness, readability, and extendability. This exercise typically takes one to two hours. Try to keep things simple.

## Problem Brief

At TrueAccord, we deal with customer debts in all of our products. The following problem will give you a database schema and API that outputs debts and payment plans. We expect you to think about the problem definitions, and try to solve them to the best of your ability.

## Problems

Imagine that you are writing an internal admin tool. Please complete the following tasks. Use the database schema and API documentation as a reference.

1. Consume the HTTP API endpoints described below to create a script that will output debts, one JSON object per line, to stdout. Each line should contain:
 - All of the debt's fields returned by the API
 - An additional boolean field, "*is_in_payment_plan*" set to true, if the debt is associated with a payment plan
 - NOTE: You are not expected to create your own server backend. Although the data is mocked, use these endpoints as though it was real data.
2. Provide a test suite that validates the output being produced, along with any other operations performed internally
3. Add a new field to the debts in the output: *remaining_amount*, containing the calculated amount remaining to be paid on the debt. Output the value as a JSON number
4. Add a new field to the output: "*next_payment_due_date*", containing the ISO 8601 UTC date of when the next payment is due or null if there is no payment plan or if the debt has been paid off
 - The next payment date can be calculated by using the payment plan start_date, the installment frequency, and any preexisting payments

## Submitting Your Work

Please email us the following once you are done:

- A .zip or .tar.gz of your solution, complete with running instructions
 - If you can, create an offline git repository and submit that once you're done. By committing your work regularly with good comments, you'll have a high level overview of your work for free
 - This is not mandatory, just do what's easiest for you
- Include a high level overview of how you've spent your time
 - If you submit a git repo with numerous commits and good comments, feel free to omit
- Include a description of your process and approach, along with how you think you could have done better with more time

We will have two engineers review it after you're done.

## Simple Database HTTP API Service

This section details the three database tables you'll need and the HTTP Service endpoints that provide access to the data in these tables. Access each service endpoint using an **HTTP GET request**.

### Debts Table

A debt, which is money is owed to a collector.

* id (integer)
* amount (real) - USD amount owed

#### HTTP Service URL: https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts

#### HTTP JSON Response

```json
[{
  "id": 0,
  "amount": 123.46
}]
```

### Payment Plans Table

A payment plan, which is an amount needed to resolve a debt, as well as the frequency of when it will be paid. One-to-one with debt.


* id (integer)
* debt_id (integer) - The associated debt
* amount_to_pay (real) - Total USD amount needed to be paid to resolve this debt
* installment_frequency (text) - The frequency of payments. Is one of: WEEKLY or BI_WEEKLY
* installment_amount (real) - The USD amount of each payment installment
* start_date (string) - ISO 8601 datetime of when the first payment is due.

#### HTTP Service URL: https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans

#### HTTP JSON Response Payload

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

### Payments Table

An individual payment installment which is made on a payment plan. Many-to-one with debts.

* payment_plan_id (integer)
* amount (real)
* date (string) - ISO 8601 datetime of when this payment occurred.

#### HTTP Service URL: https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments

#### HTTP JSON Repsonse Payload

```json
[{
  "payment_plan_id": 0,
  "amount": 51.25,
  "date": "2020-09-29T17:19:31Z"
}]
```