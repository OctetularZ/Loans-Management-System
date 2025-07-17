package Record;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Loan {
    protected Scanner input = new Scanner(System.in);

    // Below are all the protected attributes for a Loan class.
    protected String recordID;
    protected String typeOfLoan;
    protected String interestRate;
    protected String amountToPay;
    protected String loanTermLeft;
    protected String overpayment;

    // List containing all the record IDs of the records added to the credit records of customers.
    // Used to ensure that the user doesn't have duplicate records.
    public static ArrayList<String> recordIDList = new ArrayList<>();


    // isNumeric is a method which is used to ensure that whatever is passed to it as an argument, only contains integers (0-9).
    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("\\d+");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    // isNumericOrFloat is the same as isNumeric but allows floats as well.
    protected boolean isNumericOrFloat(String strNum) {
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    // setRecordID checks to make sure that the inputted record ID from XYZBank is valid then sets it to recordID attribute if it is.
    // If it's valid, it'll be used to create a new record. If not, the user will be asked for an input again until it is.
    public void setRecordID(String newRecordID) {
        boolean isInteger = isNumeric(newRecordID);

        // The while loop below will keep asking the user for an input if it's not all integers or if it's not equal to 6 numbers in length or if the recordID already exists in the record list.
        while (newRecordID.length() != 6 || !isInteger || recordIDList.contains(newRecordID)) {
            if (recordIDList.contains(newRecordID)) {
                System.out.println("Record ID already exists!");
            }
            else {
                System.out.println("Invalid record ID format");
            }
            System.out.print("Enter the Record ID (6 digits/numbers): ");
            newRecordID = input.next();
            System.out.println();
            isInteger = isNumeric(newRecordID);
        }
        this.recordID = newRecordID;
        recordIDList.add(newRecordID);
    }

    // getRecordID returns the recordID attribute
    public String getRecordID() {
        return this.recordID;
    }

    // setTypeOfLoan checks to make sure that the inputted type of loan from XYZBank is valid then sets it to typeOfLoan attribute if it is.
    // If it's valid, it'll be used to create a new record. If not, the user will be asked for an input again until it is.
    public static String setTypeOfLoan(String newTypeOfLoan) {
        Scanner input = new Scanner(System.in);
        // Creates an array containing all the possible type of loans
        ArrayList<String> typesOfLoans = new ArrayList<>();
        typesOfLoans.add("auto");
        typesOfLoans.add("builder");
        typesOfLoans.add("mortgage");
        typesOfLoans.add("personal");
        typesOfLoans.add("other");

        // The while loop will keep asking the user for an input, until the input they have entered is in the typeOfLoans array.
        while (!typesOfLoans.contains(newTypeOfLoan.toLowerCase())) {
            System.out.println("Invalid type of loan entered!");
            System.out.print("Enter the type of loan (Auto, Builder, Mortgage, Personal or Other): ");
            newTypeOfLoan = input.next();
            System.out.println();
        }
        newTypeOfLoan = newTypeOfLoan.toLowerCase();
        return newTypeOfLoan;
    }

    // getTypeOfLoan returns the typeOfLoan attribute
    public String getTypeOfLoan() {
        return this.typeOfLoan;
    }

    // setInterestRate checks to make sure that the inputted interest rate from XYZBank is valid then sets it to interestRate attribute if it is.
    // If it's valid, it'll be used to create a new record class. If not, the user will be asked for an input again until it is.
    public void setInterestRate(String newInterestRate) {
        boolean isFloatOrInteger = isNumericOrFloat(newInterestRate);

        // The while loop will keep asking the user for an input, until the input they have entered is a float/integer.
        while (!isFloatOrInteger) {
            System.out.println("Invalid interest rate entered!");
            System.out.print("Enter the interest rate (i.e. '30' = 30%): ");
            newInterestRate = input.next();
            System.out.println();
            isFloatOrInteger = isNumericOrFloat(newInterestRate);
        }
        this.interestRate = newInterestRate;
    }

    // getInterestRate returns the interestRate attribute
    public String getInterestRate() {
        return this.interestRate;
    }

    // setAmountToPay checks to make sure that the inputted amount to pay from XYZBank is valid then sets it to amountToPay attribute if it is.
    // If it's valid, it'll be used to create a new record class. If not, the user will be asked for an input again until it is.
    public void setAmountToPay(String newAmountToPay) {
        boolean isFloatOrInteger = isNumericOrFloat(newAmountToPay);

        // The while loop will keep asking the user for an input, until the input they have entered is an integer or float.
        while (!isFloatOrInteger) {
            System.out.println("Invalid amount to pay entered!");
            System.out.print("Enter the amount left to pay in thousands (i.e. '540' = £540,000): ");
            newAmountToPay = input.next();
            System.out.println();
            isFloatOrInteger = isNumericOrFloat(newAmountToPay);
        }
        this.amountToPay = newAmountToPay;
    }

    // getAmountToPay returns the amountToPay attribute
    public String getAmountToPay() {
        return this.amountToPay;
    }

    // setLoanTermLeft checks to make sure that the inputted loan term left from XYZBank is valid then sets it to loanTermLeft attribute if it is.
    // If it's valid, it'll be used to create a new record class. If not, the user will be asked for an input again until it is.
    public void setLoanTermLeft(String newLoanTermLeft) {
        boolean isFloatOrInteger = isNumericOrFloat(newLoanTermLeft);

        // The while loop will keep asking the user for an input, until the input they have entered is a float/integer.
        while (!isFloatOrInteger) {
            System.out.println("Invalid loan term left entered!");
            System.out.print("Enter the loan term left in years (i.e. '2' = 2 years): ");
            newLoanTermLeft = input.next();
            System.out.println();
            isFloatOrInteger = isNumericOrFloat(newLoanTermLeft);
        }
        this.loanTermLeft = newLoanTermLeft;
    }

    // getLoanTermLeft returns the loanTermLeft attribute
    public String getLoanTermLeft() {
        return this.loanTermLeft;
    }
}
