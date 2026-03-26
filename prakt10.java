import java.util.ArrayList;
import java.util.List;

// --- 1. Інтерфейси ---

// Інтерфейс для спостерігачів (підписників)
interface Observer {
    void update(String stockName, double newPrice);
}

// Інтерфейс для суб'єкта (біржі)
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// --- 2. Реалізація Суб'єкта ---

class StockExchange implements Subject {
    private List<Observer> observers;
    private String currentStockName;
    private double currentStockPrice;

    public StockExchange() {
        this.observers = new ArrayList<>();
    }

    // Метод для зміни курсу, який автоматично запускає розсилку
    public void setStockPrice(String stockName, double newPrice) {
        this.currentStockName = stockName;
        this.currentStockPrice = newPrice;
        System.out.println("\n[Біржа] Оновлення: " + stockName + " тепер коштує $" + newPrice);
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("[Біржа] Підписника видалено зі списку розсилки.");
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentStockName, currentStockPrice);
        }
    }
}

// --- 3. Реалізація Спостерігачів ---

class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double newPrice) {
        System.out.println(" -> Інвестор [" + name + "] отримав сповіщення: Акція " + stockName + " = $" + newPrice);
    }
}

class Broker implements Observer {
    private String companyName;

    public Broker(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void update(String stockName, double newPrice) {
        System.out.println(" -> Брокерська компанія [" + companyName + "] фіксує зміну: " + stockName + " = $" + newPrice);
    }
}

// --- 4. Клієнтський код (Тестування) ---

public class Main {
    public static void main(String[] args) {
        // Створюємо суб'єкт (біржу)
        StockExchange nasdaq = new StockExchange();

        // Створюємо підписників
        Observer investor1 = new Investor("Олександр");
        Observer investor2 = new Investor("Марія");
        Observer broker1 = new Broker("ТрейдМакс");

        // Підписуємо їх на оновлення
        nasdaq.attach(investor1);
        nasdaq.attach(investor2);
        nasdaq.attach(broker1);

        // Імітуємо зміну курсів
        nasdaq.setStockPrice("Apple", 150.25);
        nasdaq.setStockPrice("Google", 2800.10);

        // Видаляємо одного підписника
        nasdaq.detach(investor2);

        // Знову змінюємо курс (Марія вже не отримає сповіщення)
        nasdaq.setStockPrice("Microsoft", 315.50);
    }
}
