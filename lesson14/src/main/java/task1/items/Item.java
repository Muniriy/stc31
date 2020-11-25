package task1.items;

import java.math.BigDecimal;

public abstract class Item {

    private final int itemId;
    private String itemTitle;
    private BigDecimal price;

    public Item(int itemId, String itemTitle, BigDecimal price) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", price=" + price +
                '}';
    }
}
