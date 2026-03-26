import java.util.ArrayList;
import java.util.List;

// Клас, що порушує майже всі принципи SOLID
class CafeOrder {
    private List<String> items = new ArrayList<>();
    private List<Double> prices = new ArrayList<>();
    private String discountType;

    public CafeOrder(String discountType) {
        this.discountType = discountType;
    }

    // Відповідальність 1: Управління даними замовлення
    public void addItem(String name, double price) {
        items.add(name);
        prices.add(price);
    }

    // Відповідальність 2: Бізнес-логіка розрахунку та ЗНИЖОК (Порушення OCP)
    public double calculateTotal() {
        double total = 0;
        for (double price : prices) {
            total += price;
        }

        // Жорстка прив'язка до типів знижок. 
        // Щоб додати нову знижку, доведеться змінювати цей метод.
        if (discountType.equals("HOLIDAY")) {
            total = total * 0.9; // 10% знижка
        } else if (discountType.equals("VOLUME") && items.size() > 5) {
            total = total * 0.85; // 15% знижка
        }
        return total;
    }

    // Відповідальність 3: Форматування та виведення даних (Порушення SRP)
    public void printReceipt() {
        System.out.println("--- Чек кафе ---");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i) + " - " + prices.get(i) + " грн");
        }
        System.out.println("До сплати (з урахуванням знижки): " + calculateTotal() + " грн");
        System.out.println("----------------");
    }
}
