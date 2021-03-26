package sample;

import jdk.jfr.Category;

public class Item {
    private String ID;
    private String Name;
    private Category ItemType;
    private int Priority;
    private String Delivered;
    private int DestinationX;
    private int DestinationY;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getDelivered() {
        return Delivered;
    }

    public void setDelivered(String delivered) {
        Delivered = delivered;
    }

    public int getDestinationX() {
        return DestinationX;
    }

    public void setDestinationX(int destinationX) {
        DestinationX = destinationX;
    }

    public int getDestinationY() {
        return DestinationY;
    }

    public void setDestinationY(int destinationY) {
        DestinationY = destinationY;
    }
}

