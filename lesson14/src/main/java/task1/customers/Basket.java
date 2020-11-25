package task1.customers;

import java.util.List;

public interface Basket {

    /**
     * This method allows to add product to the basket
     *
     * @param product  The product name
     * @param quantity The product quantity
     */
    void addProduct(String product, int quantity);

    /**
     * This method allows to remove product from the basket
     *
     * @param product The product name
     */
    void removeProduct(String product);

    /**
     * This product allows to update the quantity of the product
     * in the basket
     *
     * @param product  The product name
     * @param quantity The product quantity
     */
    void updateProductQuantity(String product, int quantity);

    /**
     * This method allows to clear the basket
     */
    void clear();

    /**
     * This method allows to get all the List of products
     * lying in the basket
     *
     * @return The list of product names lying in the basket
     */
    List<String> getProducts();

    /**
     * This method allows to get the quantity of the product
     * from the basket
     *
     * @param product The product name
     * @return The product quantity
     */
    int getProductQuantity(String product);
}
