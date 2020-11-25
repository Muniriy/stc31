package task1.customers;

import java.util.List;

public class BasketImpl implements Basket {
    /**
     * This method allows to add product to the basket
     *
     * @param product  The product name
     * @param quantity The product quantity
     */
    @Override
    public void addProduct(String product, int quantity) {

    }

    /**
     * This method allows to remove product from the basket
     *
     * @param product The product name
     */
    @Override
    public void removeProduct(String product) {

    }

    /**
     * This product allows to update the quantity of the product
     * in the basket
     *
     * @param product  The product name
     * @param quantity The product quantity
     */
    @Override
    public void updateProductQuantity(String product, int quantity) {

    }

    /**
     * This method allows to clear the basket
     */
    @Override
    public void clear() {

    }

    /**
     * This method allows to get all the List of products
     * lying in the basket
     *
     * @return The list of product names lying in the basket
     */
    @Override
    public List<String> getProducts() {
        return null;
    }

    /**
     * This method allows to get the quantity of the product
     * from the basket
     *
     * @param product The product name
     * @return The product quantity
     */
    @Override
    public int getProductQuantity(String product) {
        return 0;
    }
}
