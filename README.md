# Acccount
The assessment consists of an API to be used for opening a new “current account” of already existing customers.

#### Requirements
• The API will expose an endpoint which accepts the user information (customerID,
initialCredit).

• Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.

• Also, if initialCredit is not 0, a transaction will be sent to the new account.

• Another Endpoint will output the user information showing Name, Surname, balance, and
transactions of the accounts.

# The application has 2 apis
* Customer
* Account

```html
GET /customer => customers
GET /customer/{customerId} => customer
POST /account => creates a new account for customer

```
*hint: first call GET '/customer' to view the existing customers it will retrieve 4 customers (Mostafa, Matin, Martin, Leo).

*hint: to create account you should POST '/account' and it accept body like e.g {
                                                                            "customerId": "40d672a6-12b0-481d-9f3f-6fcd8f98a629",
                                                                           "initialCredit": 12
                                                                            } .

# Run And Build using Maven
```ssh
$ cd account
$ mvn clean install
$ mvn spring-boot:run
```



