public class Car {
    private String brand;
    private String model;
    private int year;
    private double mileage;

    // 1. Стандартний конструктор
    public Car() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.mileage = 0.0;
        System.out.println("[System] Викликано стандартний конструктор");
    }

    // 2. Параметризований конструктор
    public Car(String brand, String model, int year, double mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        System.out.println("[System] Викликано параметризований конструктор для " + brand);
    }

    // 3. Копіювальний конструктор
    public Car(Car other) {
        this.brand = other.brand;
        this.model = other.model;
        this.year = other.year;
        this.mileage = other.mileage;
        System.out.println("[System] Викликано копіювальний конструктор для копії " + other.brand);
    }

    // 4. "Деструктор" (імітація через finalize)
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("Автомобіль [" + brand + " " + model + "] видалено з пам'яті.");
        } finally {
            super.finalize();
        }
    }

    // Метод для виводу інформації
    public String getInfo() {
        return String.format("Марка: %s, Модель: %s, Рік: %d, Пробіг: %.1f км.", 
                brand, model, year, mileage);
    }

    public static void main(String[] args) {
        System.out.println("--- Створення об'єктів ---");

        // Об'єкт через стандартний конструктор
        Car car1 = new Car();

        // Об'єкт через параметризований конструктор
        Car car2 = new Car("Toyota", "Camry", 2021, 45000.5);

        // Об'єкт через копіювальний конструктор
        Car car3 = new Car(car2);

        System.out.println("\n--- Інформація про автомобілі ---");
        System.out.println("Авто 1: " + car1.getInfo());
        System.out.println("Авто 2: " + car2.getInfo());
        System.out.println("Авто 3 (копія): " + car3.getInfo());

        System.out.println("\n--- Спроба викликати очищення пам'яті ---");
        
        // Обнуляємо посилання, щоб об'єкти стали кандидатами на видалення
        car1 = null;
        car2 = null;
        car3 = null;

        // Явно просимо JVM запустити Garbage Collector (не гарантує миттєвого виконання)
        System.gc();

        // Невелика пауза, щоб встигнути побачити повідомлення від finalize() в консолі
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        
        System.out.println("Завершення програми.");
    }
}
