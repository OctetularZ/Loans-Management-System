package Customer;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import Record.Loan;
import java.util.regex.Pattern;

public class Customer implements CheckerPrinter{
    Scanner input = new Scanner(System.in);
    protected String customerID;
    protected String customerIncome;
    protected boolean eligibility;
    protected ArrayList<Loan> creditRecords = new ArrayList<>();

    public Customer(String customerID, String customerIncome) {
        setCustomerID(customerID);
        setCustomerIncome(customerIncome);
    }

    // isStringOnlyAlphabet is a method which is used to ensure that whatever is passed to it as an argument, only contains the alphabet (a-z).
    private boolean isStringOnlyAlphabet(String str) {
        return !str.isEmpty() && str.matches("^[a-zA-Z]*$");
    }

    // isNumeric is a method used to ensure that whatever is passed to it as an argument, only contains number
    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("\\d+");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    // isNumericOrFloat is the same as isNumeric but allows floats as well.
    private boolean isNumericOrFloat(String strNum) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    // Used to check the eligibility of a customer before adding records to them
    // Eligibility criteria: Total amount left to pay should not be greater than 4 times the customer’s annual income
    public boolean checkEligibility(String amountToPay, String annualIncome) {
        boolean isFloatOrIntegerAmountToPay = isNumericOrFloat(amountToPay);
        boolean isFloatOrIntegerAnnualIncome = isNumericOrFloat(annualIncome);

        // The while loop will keep asking the user for an input, until the input they have entered is an integer/float.
        while (!isFloatOrIntegerAmountToPay) {
            System.out.println("Invalid amount to pay entered!");
            System.out.print("Enter the amount left to pay in thousands (i.e. '540' = £540,000): ");
            amountToPay = input.next();
            System.out.println();
            isFloatOrIntegerAmountToPay = isNumericOrFloat(amountToPay);
        }

        // The while loop will keep asking the user for an input, until the input they have entered is an integer/float.
        while (!isFloatOrIntegerAnnualIncome) {
            System.out.println("Invalid annual income entered!");
            System.out.print("Enter your annual income in thousands (i.e. '260' = £260,000): ");
            annualIncome = input.next();
            System.out.println();
            isFloatOrIntegerAnnualIncome = isNumericOrFloat(annualIncome);
        }

        float amountToPayAsFloat = Float.parseFloat(amountToPay);
        float annualIncomeAsFloat = Float.parseFloat(annualIncome);
        float maxAmountAllowed = annualIncomeAsFloat * 4;

        // Returns whether the user meets the criteria as a boolean
        return amountToPayAsFloat <= maxAmountAllowed;
    }

    // Used to print the customer's information/data
    public void printDetails(Customer customer) {
        Formatter fmt = new Formatter();
        fmt.format("%-10s %-10s %-10s %-10s %-10s\n", "RecordID", "LoanType", "IntRate", "AmountLeft", "TimeLeft");
        System.out.println();

        // The below checks if there are any records in the customer's credit records array.
        // If there are no records, the user will be notified.
        // Otherwise, all the customer's records will be displayed in a tabular format along with other info such as the amount of records currently stored.
        if (customer.creditRecords.isEmpty()) {
            System.out.println("This customer currently has no records...");
        }
        else {
            System.out.println("------------------------------------------------------------------");
            System.out.println("Customer ID: " + customer.customerID);
            System.out.println("Eligible to arrange loans: " + customer.getEligibility());
            System.out.println();
            for (Loan record : customer.creditRecords) {
                try {
                    fmt.format("%-10s %-10s %-10s %-10s %-10s\n", record.getRecordID(), record.getTypeOfLoan(), record.getInterestRate(), record.getAmountToPay(), record.getLoanTermLeft());
                }
                catch (NullPointerException ignored) { // This ensures that the zero/null values are ignored so the program doesn't run into errors and the records are displayed.
                }
            }
            System.out.println(fmt);
            System.out.println("------------------------------------------------------------------");
        }
    }

    // setCustomerID checks to make sure that the inputted customer ID from XYZBank is valid then sets it to customerID attribute if it is.
    // If it's valid, it'll be used to create a new customer class. If not, the user will be asked for an input again until it is.
    public void setCustomerID(String newCustomerID) {
        String firstHalf = "";
        String secondHalf = "";
        if (newCustomerID.length() == 6) {
            firstHalf = newCustomerID.substring(0, 3); // Gets the first 3 characters of the inputted customerID
            secondHalf = newCustomerID.substring(3); // Gets the last 3 characters of the inputted customerID
        }

        boolean isInteger = isNumeric(secondHalf);
        boolean isAlphabet = isStringOnlyAlphabet(firstHalf);

        // The while loop below will keep asking the user for an input if the first 3 characters are not alphabet or the last 3 characters are not integers or if it's not equal to 6 characters in length.
        while (newCustomerID.length() != 6 || !isInteger || !isAlphabet) {
            System.out.println("Invalid customer ID format");
            System.out.print("Enter the customer ID (3 letters followed by 3 digits): ");
            newCustomerID = input.next();
            System.out.println();
            if (newCustomerID.length() == 6) {
                firstHalf = newCustomerID.substring(0, 3);
                secondHalf = newCustomerID.substring(3);
            }
            isAlphabet = isStringOnlyAlphabet(firstHalf);
            isInteger = isNumeric(secondHalf);
        }
        this.customerID = newCustomerID.toUpperCase();
    }

    // getCustomerID returns the customerID attribute
    public String getCustomerID() {
        return this.customerID;
    }

    // setCustomerIncome checks to make sure that the inputted customer income from XYZBank is valid then sets it to customerIncome attribute if it is.
    // If it's valid, it'll be used to create a new customer class. If not, the user will be asked for an input again until it is.
    public void setCustomerIncome(String newCustomerIncome) {
        boolean isFloatOrInteger = isNumericOrFloat(newCustomerIncome);

        // The while loop will keep asking the user for an input, until the input they have entered is an integer/float.
        while (!isFloatOrInteger) {
            System.out.println("Invalid customer income entered!");
            System.out.print("Enter the customer income in thousands (i.e. '260' = £260,000): ");
            newCustomerIncome = input.next();
            System.out.println();
            isFloatOrInteger = isNumericOrFloat(newCustomerIncome);
        }
        this.customerIncome = newCustomerIncome;
    }

    // getCustomerIncome returns the customerIncome attribute
    public String getCustomerIncome() {
        return this.customerIncome;
    }

    // setEligibility is used to update the user's eligibility status.
    public void setEligibility(String newEligibility) {
        // If the user enters 'yes', eligibility will be set to true. If 'no', then it'll be set to false.
        // If neither of those option have been entered, the user will be asked for an input until it is.
        while (!(newEligibility.equalsIgnoreCase("yes") || newEligibility.equalsIgnoreCase("no"))) {
            System.out.println("Invalid customer eligibility status entered!");
            System.out.print("Enter the customer's eligibility status (YES or NO): ");
            newEligibility = input.next();
            System.out.println();
        }

        this.eligibility = newEligibility.equalsIgnoreCase("yes");
    }

    // getEligibility returns the eligibility attribute as 'yes' or 'no' corresponding to true or false
    public String getEligibility() {
        if (!this.eligibility) {
            return "NO";
        }
        else {
            return "YES";
        }
    }

    // Adding a record to creditRecords
    public void addToRecords(Loan newCreditRecord) {
        this.creditRecords.add(newCreditRecord);
    }

    // Returns the credit records array
    public ArrayList<Loan> getCreditRecords() {
        return this.creditRecords;
    }
}
