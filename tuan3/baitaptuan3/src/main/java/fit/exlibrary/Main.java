package fit.exlibrary;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library lib = Library.getInstance();

        // Observer
        lib.addObserver(new User("Minh"));

        // Factory
        Book b1 = BookFactory.createBook("paper","Java","James","IT");
        Book b2 = BookFactory.createBook("ebook","Python","Guido","IT");

        lib.addBook(b1);
        lib.addBook(b2);

        // Strategy
        SearchStrategy search = new SearchByTitle();
        List<Book> result = search.search(lib.getBooks(), "Java");

        System.out.println("Search:");
        for (Book b : result)
            System.out.println(b.getTitle());

        // Decorator
        Borrow borrow = new SpecialEditionDecorator(
                new ExtendTimeDecorator(
                        new BasicBorrow()
                )
        );

        System.out.println(borrow.borrow());
    }
}
