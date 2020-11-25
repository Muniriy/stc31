package task1.customers;

import java.time.LocalDate;

public class PaymentCard {

    private final String cardHolder;
    private final long cardNo;
    private final LocalDate issueDate;
    private final int cvc;

    public PaymentCard(String cardHolder, long cardNo, LocalDate issueDate, int cvc) {
        this.cardHolder = cardHolder;
        this.cardNo = cardNo;
        this.issueDate = issueDate;
        this.cvc = cvc;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public long getCardNo() {
        return cardNo;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public int getCvc() {
        return cvc;
    }
}
