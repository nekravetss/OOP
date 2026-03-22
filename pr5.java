import java.util.ArrayList;
import java.util.List;

// 1. Клас Author (Автор)
class Author {
    private String name;
    private int birthYear;

    public Author(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return String.format("Ім'я: %s, Рік народження: %d", name, birthYear);
    }
}

// 2. Клас Book (Книга) - демонструє Агрегацію
class Book {
    private String title;
    private Author author; // Книга має автора (агрегація)
    private int year;
    private String annotation;

    public Book(String title, Author author, int year, String annotation) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.annotation = annotation;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getInfo() {
        String info = String.format("Назва: %s, Рік видання: %d, Автор: %s", 
                                    title, year, (author != null ? author.getName() : "Невідомий"));
        if (annotation != null && !annotation.isEmpty()) {
            info += "\nАннотація: " + annotation;
        }
        return info;
    }
}

// 3. Клас Library (Бібліотека) - демонструє Композицію
class Library {
    private String name;
    private List<Book> books; // Бібліотека містить книги (композиція)

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        System.out.println("--- Список книг у бібліотеці \"" + name + "\" ---");
        for (Book book : books) {
            System.out.println(book.getInfo());
            System.out.println("---------------------------");
        }
    }

    public void findBooksByAuthor(String authorName) {
        System.out.println("Пошук книг автора: " + authorName);
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor() != null && book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                System.out.println("- " + book.getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Книг цього автора не знайдено.");
        }
    }
}

// Головний клас для демонстрації
public class Main {
    public static void main(String[] args) {
        // Створюємо авторів (вони існують незалежно від книг)
        Author author1 = new Author("Тарас Шевченко", 1814);
        Author author2 = new Author("Сергій Жадан", 1974);

        // Створюємо книги (агрегація: передаємо об'єкт автора в книгу)
        Book book1 = new Book("Кобзар", author1, 1840, "Збірка поетичних творів.");
        Book book2 = new Book("Інтернат", author2, 2017, "Роман про війну на сході України.");
        Book book3 = new Book("Ворошиловград", author2, 2010, "");

        // Створюємо бібліотеку (композиція)
        Library myLibrary = new Library("Національна бібліотека");

        // Додаємо книги
        myLibrary.addBook(book1);
        myLibrary.addBook(book2);
        myLibrary.addBook(book3);

        // Виводимо список усіх книг
        myLibrary.listBooks();

        // Пошук за автором
        myLibrary.findBooksByAuthor("Сергій Жадан");
    }
}
