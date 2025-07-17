package Record.LoanTypes;

import Record.Loan;

public class Auto extends Loan { // Inherits the Loan class
    // Default constructor
    public Auto() {
        this.recordID = "000000";
        this.typeOfLoan = "Auto";
        this.interestRate = "30";
        this.amountToPay = "360";
        this.loanTermLeft = "2";
    }

    // Non-default constructor
    public Auto(String recordID, String interestRate, String amountToPay, String loanTermLeft) {
        setRecordID(recordID);
        this.typeOfLoan = "Auto";
        setInterestRate(interestRate);
        setAmountToPay(amountToPay);
        setLoanTermLeft(loanTermLeft);
    }
}