package Record.LoanTypes;

import Record.Loan;

public class Personal extends Loan { // Inherits the Loan class
    // Default constructor
    public Personal() {
        this.recordID = "000000";
        this.typeOfLoan = "Personal";
        this.interestRate = "30";
        this.amountToPay = "360";
        this.loanTermLeft = "2";
    }

    // Non-default constructor
    public Personal(String recordID, String interestRate, String amountToPay, String loanTermLeft) {
        setRecordID(recordID);
        this.typeOfLoan = "Personal";
        setInterestRate(interestRate);
        setAmountToPay(amountToPay);
        setLoanTermLeft(loanTermLeft);
    }
}
