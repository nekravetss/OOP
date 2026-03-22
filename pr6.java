import java.util.ArrayList;
import java.util.List;

// --- 1. Клас Автор (Компонент для Книги) ---
class Author {
    private String firstName;
    private String lastName;
    private String birthDate;

    public Author(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName() + " (нар. " + birthDate + ")";
    }
}

// --- 2. Базовий клас Книга (Агрегує Автора) ---
class Book {
    private String title;
    private Author author; // Агрегація
    private int year;

    public Book(String title, Author author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() { return title; }

    public String getInfo() {
        return String.format("Книга: '%s', Автор: %s, Рік: %d", title, author.getFullName(), year);
    }
}

// --- 3. Нащадок: Електронна Книга (Успадкування) ---
class EBook extends Book {
    private String fileFormat;
    private double fileSizeMB;

    public EBook(String title, Author author, int year, String fileFormat, double fileSizeMB) {
        super(title, author, year);
        this.fileFormat = fileFormat;
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + String.format(" [E-Book: %s, %.1f MB]", fileFormat, fileSizeMB);
    }
}

// --- 4. Клас Відділ (Агрегує Книги) ---
class Department {
    private String name;
    private List<Book> books;

    public Department(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
    }

    public String getName() { return name; }

    public void displayBooks() {
        System.out.println("  Відділ: " + name);
        for (Book b : books) {
            System.out.println("    - " + b.getInfo());
        }
    }
}

// --- 5. Клас Бібліотека (Композиція Відділів) ---
class Library {
    private String name;
    private String address;
    private int foundationYear;
    private List<Department> departments; // Композиція

    public Library(String name, String address, int foundationYear) {
        this.name = name;
        this.address = address;
        this.foundationYear = foundationYear;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department dept) {
        departments.add(dept);
    }

    public void showLibraryStatus() {
        System.out.println("=== Бібліотека: " + name + " ===");
        System.out.println("Адреса: " + address + " (заснована " + foundationYear + " р.)");
        for (Department d : departments) {
            d.displayBooks();
        }
    }
}

// --- ГОЛОВНИЙ КЛАС ---
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // 1. Створюємо авторів
        Author king = new Author("Стівен", "Кінг", "21.09.1947");
        Author orwell = new Author("Джордж", "Оруелл", "25.06.1903");

        // 2. Створюємо різні типи книг
        Book paperBook = new Book("1984", orwell, 1949);
        EBook eBook = new EBook("Сяйво", king, 1977, "EPUB", 2.5);

        // 3. Створюємо відділи та додаємо книги
        Department fiction = new Department("Художня література");
        fiction.addBook(paperBook);
        fiction.addBook(eBook);

        Department sciFi = new Department("Наукова фантастика");

        // 4. Створюємо бібліотеку та додаємо відділи
        Library centralLibrary = new Library("Центральна Міська", "вул. Шевченка, 1", 1920);
        centralLibrary.addDepartment(fiction);
        centralLibrary.addDepartment(sciFi);

        // 5. Демонстрація роботи
        centralLibrary.showLibraryStatus();

        // Приклад видалення
        System.out.println("\n... Видаляємо '1984' з відділу ...");
        fiction.removeBook("1984");
        centralLibrary.showLibraryStatus();
    }
}
