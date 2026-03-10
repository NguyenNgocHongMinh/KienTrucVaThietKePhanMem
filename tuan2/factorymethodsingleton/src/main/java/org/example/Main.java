package org.example;

import org.example.creator.Logistics;
import org.example.concretecreator.RoadLogistics;
import org.example.concretecreator.SeaLogistics;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Logistics roadLogistics = RoadLogistics.getInstance();
        roadLogistics.planDelivery();

        Logistics seaLogistics = SeaLogistics.getInstance();
        seaLogistics.planDelivery();
    }
}