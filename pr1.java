public class Main {

    // Клас Book
    static class Book {

        private String title;
        private String author;
        private int year;
        private int pages;

        // Конструктор
        public Book(String title, String author, int year, int pages) {
            this.title = title;
            this.author = author;
            this.year = year;
            this.pages = pages;
        }

        // Метод для отримання інформації
        public String getInfo() {
            return "Назва: " + title +
                    ", Автор: " + author +
                    ", Рік видання: " + year +
                    ", Сторінок: " + pages + ".";
        }

        // Метод перевірки сучасності книги
        public boolean isModern() {
            return year > 2010;
        }
    }

    // Головний метод
    public static void main(String[] args) {

        // Створення об'єктів
        Book book1 = new Book("Harry Potter", "J.K. Rowling", 2005, 607);
        Book book2 = new Book("Clean Code", "Robert Martin", 2012, 464);
        Book book3 = new Book("Atomic Habits", "James Clear", 2018, 320);

        // Вивід інформації
        System.out.println(book1.getInfo());
        System.out.println("Сучасна книга? " + book1.isModern());

        System.out.println();

        System.out.println(book2.getInfo());
        System.out.println("Сучасна книга? " + book2.isModern());

        System.out.println();

        System.out.println(book3.getInfo());
        System.out.println("Сучасна книга? " + book3.isModern());
    }
}
