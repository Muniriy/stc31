package task1;

import task1.customers.Customer;
import task1.customers.PaymentCard;
import task1.items.Item;
import task1.items.electronics.laptops.Laptop;
import task1.items.electronics.laptops.LaptopOS;
import task1.items.electronics.smartphones.MobileOS;
import task1.items.electronics.smartphones.SimCards;
import task1.items.electronics.smartphones.Smartphone;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OnlineStore {

    private static int itemIdCounter = 100000;
    private static int customerIdCounter = 100000;
    private static final List<Item> storeItems = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        addItemsToStore();
        addCustomers();
        makePurchase();
    }

    private static void makePurchase() {
        Random random = new Random();
        Customer customer = customers.get(random.nextInt(customers.size()));
        Item item = storeItems.get(random.nextInt(storeItems.size()));
        addToCart(customer, item);
        addPaymentCard(customer);
        withdrawMoney(customer.getPaymentCards(), item);
        storeItems.remove(item);
        sendItem(customer, item);
    }

    private static void sendItem(Customer customer, Item item) {

    }

    private static void withdrawMoney(PaymentCard[] paymentCards, Item item) {

    }

    private static void addPaymentCard(Customer customer) {
        if (customer.getPaymentCards() != null) {
            PaymentCard paymentCard = new PaymentCard("Name Surname",
                    12345678, LocalDate.of(2019, 10, 1),
                    123);
            List<PaymentCard> cards = Arrays.asList(customer.getPaymentCards());
            cards.add(paymentCard);
            customer.setPaymentCards(cards.toArray(new PaymentCard[0]));
        }
    }

    private static void addToCart(Customer customer, Item item) {

    }


    private static void addCustomers() {
        Customer vlad = new Customer(++customerIdCounter,"pswrd2020", "Vladislav",
                LocalDate.of(2020, 3, 1), null,null);
        Customer victor = new Customer(++customerIdCounter,"pswrd2017", "Victor",
                LocalDate.of(2017, 4, 1), null,null);
        customers.add(vlad);
        customers.add(victor);
        displayAllItems(customers);
    }

    private static void addItemsToStore() {
        storeItems.addAll(addLaptopsToStore());
        storeItems.addAll(addSmartphonesToStore());
        displayAllItems(storeItems);
    }

    private static void displayAllItems(List<?> elements) {
        System.out.println(elements);
    }

    private static List<Smartphone> addSmartphonesToStore() {
        List<Smartphone> smartphones = new ArrayList<>();
        Smartphone iPhone12mini = new Smartphone(++itemIdCounter, "iPhone 12 mini", new BigDecimal(74999),
                "Apple", "China", MobileOS.IOS13, 5.5F,128,
                new SimCards[]{SimCards.ESIM, SimCards.NANOSIM});
        Smartphone iPhoneSE = new Smartphone(++itemIdCounter, "iPhone SE", new BigDecimal(44999),
                "Apple", "China", MobileOS.IOS13, 5.1F,64,
                new SimCards[]{SimCards.NANOSIM});
        Smartphone samsungS10 = new Smartphone(++itemIdCounter, "Galaxy S10",  new BigDecimal(70999),
                "Samsung", "South Korea", MobileOS.ANDROID10, 6.1F,64,
                new SimCards[]{SimCards.NANOSIM, SimCards.NANOSIM});
        Smartphone xiaomiMiMax3 = new Smartphone(++itemIdCounter, "MiMax 3", new BigDecimal(54999),
                "Xiaomi", "China", MobileOS.ANDROID10, 6.0F,32,
                new SimCards[]{SimCards.NANOSIM, SimCards.NANOSIM});

        smartphones.add(iPhone12mini);
        smartphones.add(iPhoneSE);
        smartphones.add(samsungS10);
        smartphones.add(xiaomiMiMax3);
        return smartphones;
    }

    private static List<Laptop> addLaptopsToStore() {
        List<Laptop> laptops = new ArrayList<>();
        Laptop mac = new Laptop(++itemIdCounter, "MacBook Air",  new BigDecimal(99999),
                "Apple", "China", LaptopOS.MAC_OS11, "Silicon M1",
                8, 13F);
        Laptop windows = new Laptop(++itemIdCounter, "ZenBook", new BigDecimal(54999),
                "Asus", "China", LaptopOS.WINDOWS10, "Intel i7",
                16, 13.3F);
        laptops.add(mac);
        laptops.add(windows);
        return laptops;
    }
}
