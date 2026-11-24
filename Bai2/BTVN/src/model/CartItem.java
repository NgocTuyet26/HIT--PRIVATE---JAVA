package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {

        if(product == null ){
            throw new IllegalArgumentException("San pham khong duoc null !");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("So luong phai > 0 !");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("So luong phai > 0 !");
        }
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}
