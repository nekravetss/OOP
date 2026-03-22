// Базовий клас
class Transport {
    protected String brand;
    protected String model;
    protected int year;

    public Transport(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getInfo() {
        return "Марка: " + brand + ", Модель: " + model + ", Рік випуску: " + year;
    }
}

// Похідний клас: Автомобіль
class Car extends Transport {
    private int passengerCount;

    public Car(String brand, String model, int year, int passengerCount) {
        super(brand, model, year); // Виклик конструктора базового класу
        this.passengerCount = passengerCount;
    }

    public int getPassengerCapacity() {
        return passengerCount;
    }

    @Override
    public String getInfo() {
        // Використовуємо super.getInfo() для отримання базових даних та додаємо специфічні
        return super.getInfo() + " [Тип: Автомобіль, Пасажирів: " + passengerCount + "]";
    }
}

// Похідний клас: Вантажівка
class Truck extends Transport {
    private double cargoCapacity;

    public Truck(String brand, String model, int year, double cargoCapacity) {
        super(brand, model, year);
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " [Тип: Вантажівка, Вантажопідйомність: " + cargoCapacity + " т]";
    }
}

// Похідний клас: Мотоцикл
class Bike extends Transport {
    private int engineVolume;

    public Bike(String brand, String model, int year, int engineVolume) {
        super(brand, model, year);
        this.engineVolume = engineVolume;
    }

    public int getEngineVolume() {
        return engineVolume;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " [Тип: Мотоцикл, Об'єм двигуна: " + engineVolume + " куб.см]";
    }
}

// Головний клас для запуску програми
public class Main {
    public static void main(String[] args) {
        // 1. Створення об’єктів для кожного типу
        Car car = new Car("Volkswagen", "Golf", 2019, 5);
        Truck truck = new Truck("Scania", "R500", 2021, 25.0);
        Bike bike = new Bike("Kawasaki", "Ninja 400", 2022, 399);

        // 2. Виклик специфічних методів (перевірка можливостей)
        System.out.println("--- Перевірка специфічних методів ---");
        System.out.println("Місткість авто: " + car.getPassengerCapacity());
        System.out.println("Вантажопідйомність: " + truck.getCargoCapacity() + " тонн");
        System.out.println("Об'єм двигуна байка: " + bike.getEngineVolume() + " куб.см");

        System.out.println("\n--- Поліморфний виклик getInfo() ---");

        // 3. Реалізація поліморфного виклику через масив базового класу
        Transport[] vehicles = { car, truck, bike };

        for (Transport vehicle : vehicles) {
            // Завдяки динамічній диспетчеризації Java викличе 
            // відповідний метод getInfo() для кожного об'єкта
            System.out.println(vehicle.getInfo());
        }
    }
}
