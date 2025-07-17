package Record.LoanTypes;

import Record.Loan;

public class Builder extends Loan { // Inherits the Loan class
    // Default constructor
    public Builder() {
        this.recordID = "000000";
        this.typeOfLoan = "Builder";
        this.interestRate = "30";
        this.amountToPay = "360";
        this.loanTermLeft = "2";
        this.overpayment = "12";
    }

    // Non-default constructor
    public Builder(String recordID, String interestRate, String amountToPay, String loanTermLeft, String overpayment) {
        setRecordID(recordID);
        this.typeOfLoan = "Builder";
        setInterestRate(interestRate);
        setAmountToPay(amountToPay);
        setLoanTermLeft(loanTermLeft);
        setOverpayment(overpayment);
    }

    // setOverpayment checks to make sure that the inputted amount to pay from XYZBank is valid then sets it to overpayment attribute if it is.
    // If it's valid, it'll be used to create a new record. If not, the user will be asked for an input again until it is.
    public void setOverpayment(String newOverpayment) {
        boolean isFloatOrInteger = isNumericOrFloat(newOverpayment);
        float overpaymentAsFloat = -1;

        if (isFloatOrInteger) {
            overpaymentAsFloat = Float.parseFloat(newOverpayment);
        }

        // The while loop will keep asking the user for an input, until the input they have entered is an integer or float.
        while (!isFloatOrInteger || overpaymentAsFloat < 0 || overpaymentAsFloat > 2) {
            System.out.println("Invalid overpayment entered! - Must be a number between 0 and 2.");
            System.out.print("Enter the overpayment amount in terms of percentage (i.e. '1.2' = 1.2%): ");
            newOverpayment = input.next();
            System.out.println();
            isFloatOrInteger = isNumericOrFloat(newOverpayment);
            if (isFloatOrInteger) {
                overpaymentAsFloat = Float.parseFloat(newOverpayment);
            }
        }
        this.amountToPay = newOverpayment;
    }

    // getOverpayment returns the overpayment attribute
    public String getOverpayment() {
        return this.overpayment;
    }
}
