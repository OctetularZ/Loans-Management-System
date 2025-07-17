package Record.LoanTypes;

import Record.Loan;

public class Other extends Loan { // Inherits the Loan class
    // Default constructor
    public Other() {
        this.recordID = "000000";
        this.typeOfLoan = "Other";
        this.interestRate = "30";
        this.amountToPay = "360";
        this.loanTermLeft = "2";
    }

    // Non-default constructor
    public Other(String recordID, String interestRate, String amountToPay, String loanTermLeft) {
        setRecordID(recordID);
        this.typeOfLoan = "Other";
        setInterestRate(interestRate);
        setAmountToPay(amountToPay);
        setLoanTermLeft(loanTermLeft);
    }
}
