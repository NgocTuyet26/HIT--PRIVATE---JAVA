package model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        if(id == null || id.trim().isEmpty()){
            throw new IllegalArgumentException("ID khong duoc de trong! ");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ten khong duoc de trong !");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Gia phai > 0 !");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Khong ton tai hang ton kho !");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Gia phai > 0 !");
        }
        this.price = price;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0 || quantity > stock) {
            throw new IllegalArgumentException("So luong khong hop le !");
        }
        stock -= quantity;
    }

    public String getDescription() {
        return name;
    }
}



