package fit;

import java.util.ArrayList;
import java.util.List;

// Observer
interface Observer {
    void update(String message);
}

// Subject
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class Stock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private double price;
    private String stockName;

    public Stock(String stockName) {
        this.stockName = stockName;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update("Stock " + stockName + " changed price to: " + price);
        }
    }
}

class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class Task implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String status;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update("Task " + taskName + " changed status to: " + status);
        }
    }
}

class TeamMember implements Observer {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " notified: " + message);
    }
}

public class ObserverDemo2 {
    public static void main(String[] args) {

        System.out.println("STOCK SYSTEM");
        Stock appleStock = new Stock("AAPL");

        Observer investor1 = new Investor("Alice");
        Observer investor2 = new Investor("Bob");

        appleStock.registerObserver(investor1);
        appleStock.registerObserver(investor2);

        appleStock.setPrice(150.0);
        appleStock.setPrice(200.0);

        System.out.println("\nTASK SYSTEM");
        Task task = new Task("Design Database");

        Observer dev1 = new TeamMember("Minh");
        Observer dev2 = new TeamMember("An");

        task.registerObserver(dev1);
        task.registerObserver(dev2);

        task.setStatus("In Progress");
        task.setStatus("Completed");
    }
}