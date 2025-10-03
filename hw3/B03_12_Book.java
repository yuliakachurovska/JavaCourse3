public class B03_12_Book {
    public static void main(String[] args) {
        Book[] books = {
                new Book("Кайдашева сім'я", "Іван Нечуй-Левицький", "Видавництво Знання", 1878),
                new Book("Микола Джеря", "Іван Нечуй-Левицький", "Книголав", 1878),
                new Book("Хмари", "Іван Нечуй-Левицький", "Фоліо", 1874),

                new Book("Хіба ревуть воли, як ясла повні?", "Панас Мирний", "Фоліо", 1880),
                new Book("Лимерівна", "Панас Мирний", "А-БА-БА-ГА-ЛА-МА-ГА", 1882),
                new Book("Повія", "Панас Мирний", "Фоліо", 1883),

                new Book("Чорна рада", "Пантелеймон Куліш", "Навчальна книга - Богдан", 1857),
                new Book("Досвітки", "Пантелеймон Куліш", "Веселка", 1862),

                new Book("Катерина", "Тарас Шевченко", "Основи", 1838),
                new Book("Гайдамаки", "Тарас Шевченко", "Фоліо", 1841),
                new Book("Кобзар", "Тарас Шевченко", "Веселка", 1840),

                new Book("Захар Беркут", "Іван Франко", "Фоліо", 1883),
                new Book("Борислав сміється", "Іван Франко", "Видавництво Знання", 1881),
                new Book("Мойсей", "Іван Франко", "Основи", 1905),

                new Book("Лісова пісня", "Леся Українка", "Книголав", 1911),
                new Book("Бояриня", "Леся Українка", "Веселка", 1910),
                new Book("На крилах пісень", "Леся Українка", "Фоліо", 1893)
        };


        System.out.println("Книги Івана Нечуя-Левицького:");
        Book[] levBooks = findByAuthor(books, "Іван Нечуй-Левицький");
        for (Book b : levBooks) {
            System.out.println(b);
        }

        System.out.println("\nКниги видавництва 'Фоліо' (відсортовані):");
        Book[] folioBooks = findByPublisherSorted(books, "Фоліо");
        for (Book b : folioBooks) {
            System.out.println(b);
        }
    }

    public static Book[] findByAuthor(Book[] books, String author) {
        int count = 0;
        for (Book b : books) {
            if (b.getAuthor().equals(author)) {
                count++;
            }
        }

        Book[] result = new Book[count];
        int index = 0;
        for (Book b : books) {
            if (b.getAuthor().equals(author)) {
                result[index++] = b;
            }
        }
        return result;
    }

    public static Book[] findByPublisherSorted(Book[] books, String publisher) {
        int count = 0;
        for (Book b : books) {
            if (b.getPublisher().equals(publisher)) {
                count++;
            }
        }

        Book[] result = new Book[count];
        int index = 0;
        for (Book b : books) {
            if (b.getPublisher().equals(publisher)) {
                result[index++] = b;
            }
        }

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].getTitle().compareTo(result[j + 1].getTitle()) > 0) {
                    Book temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }
}

class Book {
    private String title;
    private String author;
    private String publisher;
    private int year;

    public Book(String title, String author, String publisher, int year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + author + ", " + publisher + ", " + year + ")";
    }
}
