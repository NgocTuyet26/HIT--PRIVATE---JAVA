import model.Product;
import model.Customer;
import model.CartItem;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class HitShop {
    private static final Customer currentCustomer = new Customer("C01", "Linh Nguyễn", "0900000001");
    private static final List<CartItem> cart = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product("P01", "Áo thun", 150000, 20));
        products.add(new Product("P02", "Bình nước", 50000, 10));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    handleAdd(sc);
                    break;
                case 2:
                    handleRemove(sc);
                    break;
                case 3:
                    printItems();
                    break;
                case 4:
                    System.out.printf("Tổng tiền: %,.0f%n", calculateTotal());
                    break;
                case 0:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);

        sc.close();
    }

    static void printMenu() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Thêm mặt hàng");
        System.out.println("2. Xóa mặt hàng");
        System.out.println("3. Xem giỏ hàng");
        System.out.println("4. Xem tổng tiền");
        System.out.println("0. Thoát");
    }

    static void handleAdd(Scanner sc) {
        System.out.println("Danh sách sản phẩm:");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.print("Mã sản phẩm: ");
        String id = sc.nextLine();
        Product product = findProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }

        System.out.print("Số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());
        if (quantity <= 0) {
            System.out.println("Số lượng phải lớn hơn 0.");
            return;
        }
        addItem(product, quantity);
    }

    static void handleRemove(Scanner sc) {
        printItems();
        if (cart.isEmpty()) {
            return;
        }
        System.out.print("Xóa STT: ");
        int index = Integer.parseInt(sc.nextLine());
        removeItem(index - 1);
    }

    static Product findProductById(String id) {
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    static CartItem findCartItem(Product product) {
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                return item;
            }
        }
        return null;
    }

    static void addItem(Product product, int quantity) {
        CartItem existing = findCartItem(product);
        int inCart = existing == null ? 0 : existing.getQuantity();

        if (inCart + quantity > product.getStock()) {
            System.out.println("Không đủ hàng. Tồn kho: " + product.getStock()
                    + ", trong giỏ đã có: " + inCart);
            return;
        }

        if (existing != null) {
            existing.setQuantity(inCart + quantity);
        } else {
            cart.add(new CartItem(product, quantity));
        }
        System.out.println("Đã thêm vào giỏ hàng.");
    }

    static void removeItem(int index) {
        if (index < 0 || index >= cart.size()) {
            System.out.println("Không tìm thấy mặt hàng.");
            return;
        }
        CartItem removed = cart.remove(index);
        System.out.println("Đã xóa: " + removed.getProduct().getName());
    }

    static void printItems() {
        System.out.println("Khách hàng: " + currentCustomer);
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            Product product = item.getProduct();
            System.out.printf("%d. %s | %s | %,.0f | SL: %d | Thành tiền: %,.0f%n",
                    i + 1,
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity(),
                    item.getTotal());
        }
    }

    static double calculateTotal() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotal();
        }
        return total;
    }
}
