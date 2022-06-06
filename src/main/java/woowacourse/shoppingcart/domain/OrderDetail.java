package woowacourse.shoppingcart.domain;

public class OrderDetail {
    private Long productId;
    private int quantity;
    private int price;
    private String name;
    private String thumbnail;

    public OrderDetail() {
    }

    public OrderDetail(final Long productId, final int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetail(final Product product, final int quantity) {
        this(product.getId(), product.getPrice(), product.getName(), product.getThumbnail(), quantity);
    }

    public OrderDetail(final Long productId, final int price, final String name,
                       final String thumbnail, final int quantity) {
        this.productId = productId;
        this.price = price;
        this.name = name;
        this.thumbnail = thumbnail;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getQuantity() {
        return quantity;
    }
}
