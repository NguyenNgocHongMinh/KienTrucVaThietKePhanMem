package fit.exlibrary;

abstract class Book {
    protected String title, author, category;

    public Book(String t, String a, String c) {
        title = t; author = a; category = c;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }

    public abstract String getType();
}

class PaperBook extends Book {
    public PaperBook(String t, String a, String c) { super(t,a,c); }
    public String getType() { return "Paper"; }
}

class EBook extends Book {
    public EBook(String t, String a, String c) { super(t,a,c); }
    public String getType() { return "EBook"; }
}

class AudioBook extends Book {
    public AudioBook(String t, String a, String c) { super(t,a,c); }
    public String getType() { return "Audio"; }
}

class BookFactory {
    public static Book createBook(String type, String t, String a, String c) {
        switch(type) {
            case "paper": return new PaperBook(t,a,c);
            case "ebook": return new EBook(t,a,c);
            case "audio": return new AudioBook(t,a,c);
        }
        return null;
    }
}