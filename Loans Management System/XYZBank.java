import Customer.Customer;
import Record.Loan;
import Record.LoanTypes.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class XYZBank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int maxRecords = 0;

        // The while loop below is used to ensure that the user enters a valid value for the max number of records.
        /* If the value they enter is less than 1 or is simply not an integer,
           then they will be asked for another input and will be given a prompt of the problem with their input.
        */
        // They will be asked to try again until they enter a valid input.
        while (maxRecords < 1) {
            try {
                System.out.print("Maximum number of records (1 or more): ");
                maxRecords = input.nextInt();
                if (maxRecords < 1) {
                    System.out.println("Maximum number of records must be greater than 0...");
                    System.out.println();
                }
            }
            catch (InputMismatchException err) { // Catches any input which isn't an integer.
                System.out.println("Invalid input for number of records!");
                System.out.println();
                input.nextLine();
            }
        }

        System.out.println();

        // customers array is used to store all the customer objects
        ArrayList<Customer> customers = new ArrayList<>();

        // customerIDs array is used to store all the customer IDs
        ArrayList<String> customerIDs = new ArrayList<>();

        int i = 0; // Counter for the number of records that have been created. Used to ensure number of records doesn't exceed the max

        // While loop below is used to keep asking the user what they want to do whether that may be adding a customer, adding a record, deleting a record, etc.
        // They also have the option to quit out of the program.
        while (true) {
            System.out.println("""
                    - 'AC' - (Add a customer)
                    - 'AR' - (Add a record)
                    - 'DA' - (Display all records)
                    - 'D' - (Displays a customer's records)
                    - 'U' - (Update a customer's information)
                    - 'R' - (Remove a record)
                    - 'E' - (Exit program)""");
            System.out.println();
            System.out.println("Choose an option: ");
            String option = input.next();
            System.out.println();
            if (option.equalsIgnoreCase("AC")) {
                // Registers a new customer

                // The series of user inputs below will be used to register a new customer, so it can be added to the customers list.
                System.out.print("Enter the Customer ID (3 letters followed by 3 digits): ");
                String customerID = input.next();
                System.out.print("Enter the customer's annual income in thousands (i.e. '260' = £260,000): ");
                String customerIncome = input.next();
                System.out.println();

                // The if block below checks if the customer already exists and if it doesn't the customer will be registered
                // Otherwise a message will be displayed to let the user know that the customer already exists
                if (customerIDs.contains(customerID.toUpperCase())) {
                    System.out.println("Customer ID already exists...");
                    System.out.println();
                }
                else {
                    var newCustomer = new Customer(customerID, customerIncome);
                    customers.add(newCustomer);
                    customerIDs.add(customerID.toUpperCase());

                    System.out.println("Customer Successfully Added!");
                    System.out.println();
                }

            }
            else if (option.equalsIgnoreCase("AR")) {
                // Adds a record to a customer

                System.out.print("Enter the customer ID of whom you would like to add the record to: ");
                String customerID = input.next();
                if (!customerIDs.contains(customerID.toUpperCase())) {
                    System.out.println("Customer ID doesn't exist...");
                    System.out.println();
                }
                else {
                    // Selecting the customer from the customers array using the customer ID entered by the user
                    Customer customer = null;
                    for (Customer customerObj : customers) {
                        if (customerObj.getCustomerID().equalsIgnoreCase(customerID)) {
                            customer = customerObj;
                        }
                    }

                    System.out.println("-----------------------------------------------------------------------------");
                    System.out.print("Enter the type of loan (Auto, Builder, Mortgage, Personal or Other): ");
                    String typeOfLoan = input.next();
                    typeOfLoan = Loan.setTypeOfLoan(typeOfLoan);

                    // The series of user inputs below will be used to create a new record, so it can be added to the customer's credit records.
                    System.out.print("Enter the Record ID (6 digits/numbers): ");
                    String recordID = input.next();
                    System.out.print("Enter the interest rate (i.e. '30' = 30%): ");
                    String interestRate = input.next();
                    System.out.print("Enter the amount left to pay in thousands (i.e. '540' = £540,000): ");
                    String amountToPay = input.next();
                    System.out.print("Enter the loan term left in years (i.e. '2' = 2 years): ");
                    String loanTermLeft = input.next();
                    System.out.println();

                    // Creating the Record based on the loan type entered by the user
                    Loan newRecord;
                    if (typeOfLoan.equalsIgnoreCase("auto")) {
                        newRecord = new Auto(recordID, interestRate, amountToPay, loanTermLeft);
                    }
                    else if (typeOfLoan.equalsIgnoreCase("builder")) {
                        System.out.print("Enter the overpayment amount in terms of percentage (a number between 0 and 2 - i.e. '1.2' = 1.2%): ");
                        String overpayment = input.next();
                        newRecord = new Builder(recordID, interestRate, amountToPay, loanTermLeft, overpayment);
                    }
                    else if (typeOfLoan.equalsIgnoreCase("mortgage")) {
                        System.out.print("Enter the overpayment amount in terms of percentage (a number between 0 and 2 - i.e. '1.2' = 1.2%): ");
                        String overpayment = input.next();
                        newRecord = new Mortgage(recordID, interestRate, amountToPay, loanTermLeft, overpayment);
                    }
                    else if (typeOfLoan.equalsIgnoreCase("other")) {
                        newRecord = new Other(recordID, interestRate, amountToPay, loanTermLeft);
                    }
                    else {
                        newRecord = new Personal(recordID, interestRate, amountToPay, loanTermLeft);
                    }

                    // Checking customer eligibility before adding the record
                    // If customer is eligible, the record will be added
                    // Otherwise, a message will be displayed to the user to let them know that the user isn't eligible for a loan
                    assert customer != null;
                    if (!customer.checkEligibility(amountToPay, customer.getCustomerIncome())) {
                        System.out.println("Customer isn't eligible for a loan!");
                        System.out.println();
                        customer.setEligibility("NO");
                    }
                    else {
                        // The if statement below checks if the value of i is less than the max number of records.
                        // If it is, then a new record will be added to the customer using the user's inputted values.
                        // Otherwise, the user will receive a message to let them know that the maximum number of records has been reached, and they cannot add anymore records.
                        if (i < maxRecords) {
                            customer.addToRecords(newRecord);
                            i++;
                            System.out.println("Record Successfully Added!");
                            System.out.println();
                            customer.setEligibility("YES");
                        }
                        else {
                            System.out.println("Maximum records stored!");
                            System.out.println();
                        }
                    }
                }

            }
            else if (option.equalsIgnoreCase("D")) {
                // Displays all of a customer's records

                // Selecting which customer's records the user wants to display
                // If there are no registered customers, a message will be displayed to let the user know
                if (customerIDs.isEmpty()) {
                    System.out.println("There are currently no registered customers...");
                    System.out.println();
                }
                else {
                    System.out.print("Enter the customer ID of whom you would like their records displayed: ");
                    String customerID = input.next();

                    // If the customerID entered by the user is not in the customer ID array, a message will be displayed to let the user know that the customer doesn't exist
                    if (!customerIDs.contains(customerID.toUpperCase())) {
                        System.out.println("Customer ID doesn't exist...");
                        System.out.println();
                    } else {
                        // Getting the customer's object using the customer ID inputted by the user
                        Customer customer = null;
                        for (Customer customerObj : customers) {
                            if (customerObj.getCustomerID().equalsIgnoreCase(customerID)) {
                                customer = customerObj;
                            }
                        }
                        System.out.println();
                        System.out.println("Maximum number of Records: " + maxRecords);
                        assert customer != null;

                        // Displays the customer records if they have records in credit records array.
                        // If not, then a message will be displayed to let them know that the customer has no records.
                        if (!customer.getCreditRecords().isEmpty()) {
                            System.out.println("Registered Records: " + Loan.recordIDList.size());
                        }
                        customer.printDetails(customer);
                        System.out.println();
                    }
                }
            }
            else if (option.equalsIgnoreCase("DA")) {
                // Displays all the stored records of customer

                // Same as the display option but instead of asking the user to input a customer ID,
                // it loops through all the customers in the customers array and displays all their records.
                if (Loan.recordIDList.isEmpty()) {
                    System.out.println("There are currently no records stored...");
                    System.out.println();
                }
                else {
                    System.out.println("Maximum number of Records: " + maxRecords);
                    if (!Loan.recordIDList.isEmpty()) {
                        System.out.println("Registered Records: " + Loan.recordIDList.size());
                    }
                    for (Customer customer : customers) {
                        if (!customer.getCreditRecords().isEmpty()){
                            customer.printDetails(customer);
                            System.out.println();
                        }
                    }
                }
            }
            else if (option.equalsIgnoreCase("R")) {
                // Removes a record from a customer

                // If the recordIDList is empty, a message will be displayed to the user to let them know.
                // Otherwise, the record will be removed
                if (Loan.recordIDList.isEmpty()) {
                    System.out.println("There are currently no records stored...");
                    System.out.println();
                }
                else {
                    System.out.print("Enter the record ID of the record you would like to remove: ");
                    String recordID = input.next();

                    // If the record ID exists, customers will be looped through to get the customer who has the record ID, so it can be removed from their credit records array
                    // If the record ID doesn't exist, a message will be displayed to the user.
                    if (!Loan.recordIDList.contains(recordID.toUpperCase())) {
                        System.out.println("Record ID doesn't exist...");
                        System.out.println();
                    } else {
                        for (Customer customer : customers) {
                            customer.getCreditRecords().removeIf(record -> record.getRecordID().equalsIgnoreCase(recordID));
                        }
                        Loan.recordIDList.remove(recordID.toUpperCase());
                        i--;
                        System.out.println("Record successfully deleted!");
                        System.out.println();
                    }
                }
            }
            else if (option.equalsIgnoreCase("U")) {
                // Updates a registered customer's data
                if (customerIDs.isEmpty()) {
                    System.out.println("There are currently no registered customers...");
                    System.out.println();
                }
                else {
                    // Asks the user to select an option based on the given options to choose which value of the customer to update
                    System.out.print("Enter the customer ID of whom you would like to update their info: ");
                    String customerID = input.next();

                    if (!customerIDs.contains(customerID.toUpperCase())) {
                        System.out.println("Customer ID doesn't exist...");
                        System.out.println();
                    } else {
                        // Getting the customer's objects
                        Customer customer = null;
                        for (Customer customerObj : customers) {
                            if (customerObj.getCustomerID().equalsIgnoreCase(customerID)) {
                                customer = customerObj;
                            }
                        }
                        System.out.println();
                        System.out.println("""
                                (1) Customer ID
                                (2) Customer Income
                                (3) Eligibility
                                """);
                        System.out.println("Which of these customer's data would you like to update (i.e. Use '1' to update customer ID)?");
                        String updateData = input.next();

                        // Depending on the user input, an option will be selected and that data value of the customer will be updated.
                        if (updateData.equalsIgnoreCase("1")) {
                            System.out.print("Enter the new Customer ID (3 letters followed by 3 digits): ");
                            String newCustomerID = input.next();

                            if (customerIDs.contains(newCustomerID.toUpperCase())) {
                                System.out.println("Customer ID already exists...");
                                System.out.println();
                            }
                            else {
                                assert customer != null;
                                customerIDs.remove(customerID.toUpperCase());
                                customer.setCustomerID(newCustomerID);
                                customerIDs.add(newCustomerID.toUpperCase());

                                System.out.println("Customer ID Successfully Updated!");
                                System.out.println();
                            }
                        }
                        else if (updateData.equalsIgnoreCase("2")) {
                            System.out.print("Enter the customer's new annual income in thousands (i.e. '260' = £260,000): ");
                            String newCustomerIncome = input.next();

                            assert customer != null;
                            customer.setCustomerIncome(newCustomerIncome);

                            System.out.println("Customer Income Successfully Updated!");
                            System.out.println();
                        }
                        else if (updateData.equalsIgnoreCase("3")) {
                            System.out.print("Enter the customer's new eligibility status ('Yes' or 'No'): ");
                            String newCustomerEligibilityStatus = input.next();

                            assert customer != null;
                            customer.setEligibility(newCustomerEligibilityStatus);

                            System.out.println("Customer Eligibility Status Successfully Updated!");
                            System.out.println();
                        }
                        else {
                            System.out.println("Not a valid option!");
                            System.out.println();
                        }
                    }
                }
            }
            // Breaks out of the loop and exits the program if the user selects the 'E' option
            else if (option.equalsIgnoreCase("E")) {
                System.out.println("Exiting...");
                break;
            }
            else {
                // Tells the user that the option they entered is invalid if they don't input any valid options.
                System.out.println("Invalid option!");
                System.out.println();
            }
        }
    }
}
