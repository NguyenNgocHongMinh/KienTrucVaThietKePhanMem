package fit.exlibrary;

interface Observer {
    void update(String msg);
}

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String msg) {
        System.out.println(name + " received: " + msg);
    }
}