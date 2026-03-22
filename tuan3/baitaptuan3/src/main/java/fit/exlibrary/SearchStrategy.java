package fit.exlibrary;

import java.util.*;

interface SearchStrategy {
    List<Book> search(List<Book> books, String keyword);
}

class SearchByTitle implements SearchStrategy {
    public List<Book> search(List<Book> books, String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books)
            if (b.getTitle().contains(keyword)) result.add(b);
        return result;
    }
}

class SearchByAuthor implements SearchStrategy {
    public List<Book> search(List<Book> books, String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books)
            if (b.getAuthor().contains(keyword)) result.add(b);
        return result;
    }
}

class SearchByCategory implements SearchStrategy {
    public List<Book> search(List<Book> books, String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book b : books)
            if (b.getCategory().contains(keyword)) result.add(b);
        return result;
    }
}
