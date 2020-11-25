package task1.customers;

import java.time.LocalDate;
import java.util.Arrays;

public class Customer {

    private final int id;
    private String password;
    private String name;
    private LocalDate birthday;
    private BasketImpl basket;
    private PaymentCard[] paymentCards;

    public Customer(int id, String password, String name, LocalDate birthday,
                    BasketImpl basket, PaymentCard[] paymentCards) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.basket = basket;
        this.paymentCards = paymentCards;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BasketImpl getBasket() {
        return basket;
    }

    public void setBasket(BasketImpl basket) {
        this.basket = basket;
    }

    public PaymentCard[] getPaymentCards() {
        return paymentCards;
    }

    public void setPaymentCards(PaymentCard[] paymentCards) {
        this.paymentCards = paymentCards;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", basket=" + basket +
                ", paymentCards=" + Arrays.toString(paymentCards) +
                '}';
    }
}
