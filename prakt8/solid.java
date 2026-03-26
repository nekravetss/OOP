import java.util.ArrayList;
import java.util.List;

// --- Допоміжний клас даних ---
class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

// ---------------------------------------------------------
// 1. Принцип відкритості/закритості (OCP) та 
// 2. Принцип підстановки Барбари Лісков (LSP)
// ---------------------------------------------------------
// Інтерфейс знижки (абстракція)
interface DiscountStrategy {
    double applyDiscount(double totalAmount, int itemsCount);
}

// Реалізація 1: Знижка відсутня
class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount, int itemsCount) {
        return totalAmount;
    }
}

// Реалізація 2: Святкова знижка 10%
class HolidayDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount, int itemsCount) {
        return totalAmount * 0.9;
    }
}

// Реалізація 3: Знижка за обсяг (15% якщо більше 5 страв)
class VolumeDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount, int itemsCount) {
        return itemsCount > 5 ? totalAmount * 0.85 : totalAmount;
    }
}

// ---------------------------------------------------------
// 3. Принцип розділення інтерфейсів (ISP)
// ---------------------------------------------------------
// Замість одного великого інтерфейсу, створюємо вузькоспеціалізований для чеків
interface ReceiptGenerator {
    void generateReceipt(List<MenuItem> items, double finalTotal);
}

class ConsoleReceiptGenerator implements ReceiptGenerator {
    @Override
    public void generateReceipt(List<MenuItem> items, double finalTotal) {
        System.out.println("\n=== Офіційний Чек ===");
        for (MenuItem item : items) {
            System.out.println(item.getName() + " ..... " + item.getPrice() + " грн");
        }
        System.out.println("Разом до сплати: " + finalTotal + " грн");
        System.out.println("=====================");
    }
}

// ---------------------------------------------------------
// 4. Принцип єдиної відповідальності (SRP) та 
// 5. Принцип інверсії залежностей (DIP)
// ---------------------------------------------------------
// Клас відповідає ТІЛЬКИ за управління життєвим циклом замовлення.
// Він залежить від абстракцій (DiscountStrategy, ReceiptGenerator), а не від конкретних класів.
class OrderManager {
    private List<MenuItem> items = new ArrayList<>();
    private DiscountStrategy discountStrategy;
    private ReceiptGenerator receiptGenerator;

    // Впровадження залежностей (Dependency Injection) через конструктор
    public OrderManager(DiscountStrategy discountStrategy, ReceiptGenerator receiptGenerator) {
        this.discountStrategy = discountStrategy;
        this.receiptGenerator = receiptGenerator;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void processOrder() {
        double rawTotal = 0;
        for (MenuItem item : items) {
            rawTotal += item.getPrice();
        }

        // Застосування знижки через абстракцію
        double finalTotal = discountStrategy.applyDiscount(rawTotal, items.size());

        // Генерація чека через абстракцію
        receiptGenerator.generateReceipt(items, finalTotal);
    }
}

// --- Головний клас для запуску ---
public class SolidCafeSystem {
    public static void main(String[] args) {
        // Ми можемо легко підставити будь-яку стратегію та генератор, не змінюючи OrderManager
        OrderManager order = new OrderManager(new HolidayDiscount(), new ConsoleReceiptGenerator());
        
        order.addItem(new MenuItem("Кава Американо", 45.0));
        order.addItem(new MenuItem("Чизкейк", 85.0));
        
        order.processOrder();
    }
}
