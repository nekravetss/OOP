import java.util.ArrayList;
import java.util.List;

// 1. Component (Компонент)
// Загальний інтерфейс для всіх елементів структури (і для окремих працівників, і для відділів)
interface Employee {
    void showDetails(String indent); // Вивід інформації з відступом для імітації дерева
    void add(Employee employee);
    void remove(Employee employee);
    List<Employee> getSubordinates();
}

// 2. Leaf (Листок)
// Окремий співробітник, який не може мати підлеглих
class IndividualEmployee implements Employee {
    private String name;
    private String position;

    public IndividualEmployee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "- " + name + " [" + position + "]");
    }

    // Оскільки це "листок" і він не може мати підлеглих, ці методи кидають виняток 
    // або повертають порожній результат (згідно з підходом прозорості патерна Composite)
    @Override
    public void add(Employee employee) {
        throw new UnsupportedOperationException("Неможливо додати підлеглого до окремого співробітника.");
    }

    @Override
    public void remove(Employee employee) {
        throw new UnsupportedOperationException("Неможливо видалити підлеглого з окремого співробітника.");
    }

    @Override
    public List<Employee> getSubordinates() {
        return new ArrayList<>(); // Повертаємо порожній список
    }
}

// 3. Composite (Композит)
// Відділ, який містить список інших співробітників (або інших відділів)
class Department implements Employee {
    private String name;
    private List<Employee> subordinates;

    public Department(String name) {
        this.name = name;
        this.subordinates = new ArrayList<>();
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 Відділ: " + name);
        // Рекурсивно викликаємо showDetails для всіх підлеглих
        for (Employee employee : subordinates) {
            employee.showDetails(indent + "   ");
        }
    }

    @Override
    public void add(Employee employee) {
        subordinates.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        subordinates.remove(employee);
    }

    @Override
    public List<Employee> getSubordinates() {
        return subordinates;
    }
}

// 4. Клієнтський код
public class CompositePatternDemo {
    public static void main(String[] args) {
        // Створюємо окремих співробітників (Листки)
        Employee dev1 = new IndividualEmployee("Олександр", "Senior Java Developer");
        Employee dev2 = new IndividualEmployee("Марія", "Middle QA Engineer");
        Employee dev3 = new IndividualEmployee("Іван", "Frontend Developer");
        
        Employee hr1 = new IndividualEmployee("Олена", "HR Manager");
        Employee recruiter = new IndividualEmployee("Сергій", "IT Recruiter");

        Employee ceo = new IndividualEmployee("Віктор", "CEO");

        // Створюємо відділи (Композити)
        Employee engineeringDepartment = new Department("Розробка (Engineering)");
        Employee hrDepartment = new Department("Кадри (HR)");
        Employee headOffice = new Department("Головний офіс");

        // Формуємо структуру (додаємо співробітників у відділи)
        engineeringDepartment.add(dev1);
        engineeringDepartment.add(dev2);
        engineeringDepartment.add(dev3);

        hrDepartment.add(hr1);
        hrDepartment.add(recruiter);

        // Формуємо загальну ієрархію компанії
        headOffice.add(ceo); // CEO безпосередньо в головному офісі
        headOffice.add(engineeringDepartment); // Відділ як підлеглий елемент
        headOffice.add(hrDepartment); // Відділ як підлеглий елемент

        // КЛІЄНТСЬКИЙ КОД: Працюємо з усією структурою через один метод
        System.out.println("--- Організаційна структура компанії ---");
        headOffice.showDetails("");

        // Демонстрація того, що ми можемо працювати з окремим відділом так само
        System.out.println("\n--- Структура тільки інженерного відділу ---");
        engineeringDepartment.showDetails("");
    }
}
