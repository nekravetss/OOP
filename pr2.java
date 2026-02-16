public class Main {

    // Клас BankAccount
    static class BankAccount {

        // Приховані поля
        private String accountNumber;
        private String ownerName;
        private double balance;

        // Конструктор
        public BankAccount(String accountNumber, String ownerName, double balance) {

            this.accountNumber = accountNumber;

            if (ownerName.length() >= 3) {
                this.ownerName = ownerName;
            } else {
                System.out.println("Помилка: ім'я повинно містити мінімум 3 символи.");
                this.ownerName = "Unknown";
            }

            if (balance >= 0) {
                this.balance = balance;
            } else {
                System.out.println("Помилка: баланс не може бути від'ємним. Встановлено 0.");
                this.balance = 0;
            }
        }

        // Гетери
        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        // Сетер для зміни імені власника
        public void setOwnerName(String ownerName) {
            if (ownerName.length() >= 3) {
                this.ownerName = ownerName;
                System.out.println("Ім'я власника змінено успішно.");
            } else {
                System.out.println("Помилка: ім'я повинно містити мінімум 3 символи.");
            }
        }

        // Поповнення рахунку
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Рахунок поповнено на " + amount);
            } else {
                System.out.println("Помилка: сума поповнення повинна бути більше 0.");
            }
        }

        // Зняття коштів
        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("З рахунку знято " + amount);
            } else {
                System.out.println("Помилка: недостатньо коштів або некоректна сума.");
            }
        }

        // Метод для перегляду балансу
        public double getBalance() {
            return balance;
        }
    }

    // Головний метод
    public static void main(String[] args) {

        // Створення рахунків
        BankAccount acc1 = new BankAccount("UA12345", "Ivan", 1000);
        BankAccount acc2 = new BankAccount("UA54321", "Al", -500);

        System.out.println();

        // Тестування гетерів
        System.out.println("Рахунок: " + acc1.getAccountNumber());
        System.out.println("Власник: " + acc1.getOwnerName());
        System.out.println("Баланс: " + acc1.getBalance());

        System.out.println();

        // Тестування сетера
        acc1.setOwnerName("Petro");
        acc1.setOwnerName("Jo");

        System.out.println();

        // Тестування операцій
        acc1.deposit(500);
        acc1.withdraw(300);
        acc1.withdraw(2000);

        System.out.println("Поточний баланс: " + acc1.getBalance());
    }
}
