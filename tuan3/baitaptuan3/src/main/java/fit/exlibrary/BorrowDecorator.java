package fit.exlibrary;

interface Borrow {
    String borrow();
}

class BasicBorrow implements Borrow {
    public String borrow() {
        return "Borrow book";
    }
}

abstract class BorrowDecorator implements Borrow {
    protected Borrow wrappee;

    public BorrowDecorator(Borrow b) {
        wrappee = b;
    }

    public String borrow() {
        return wrappee.borrow();
    }
}

class ExtendTimeDecorator extends BorrowDecorator {
    public ExtendTimeDecorator(Borrow b) { super(b); }

    public String borrow() {
        return super.borrow() + " + Extended time";
    }
}

class SpecialEditionDecorator extends BorrowDecorator {
    public SpecialEditionDecorator(Borrow b) { super(b); }

    public String borrow() {
        return super.borrow() + " + Special edition";
    }
}