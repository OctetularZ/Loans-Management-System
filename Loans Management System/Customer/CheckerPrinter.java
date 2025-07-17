package Customer;

public interface CheckerPrinter { // An interface inherited by the Customer class
    boolean checkEligibility(String amountToPay, String annualIncome);
    void printDetails(Customer customer);
}
