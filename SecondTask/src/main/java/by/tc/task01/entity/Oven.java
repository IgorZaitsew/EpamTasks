package main.java.by.tc.task01.entity;

public class Oven extends SocketDependeceAppliance {

    private int weight;
    private int capacity;
    private double depth;
    private double height;
    private double width;

    public Oven(int weight, int capacity, int depth, int height, int width, int powerConsumption) {
        super(powerConsumption);
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.weight;
        hash = 53 * hash + this.capacity;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.depth) ^ (Double.doubleToLongBits(this.depth) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 53 * hash + this.getPowerConsumption();
        return hash;
    }

    @Override
    public String toString() {
        return "Oven{" + super.toString() + "weight=" + weight + ", capacity=" + capacity + ", depth=" + depth + ", height=" + height + ", width=" + width + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oven other = (Oven) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (this.capacity != other.capacity) {
            return false;
        }
        if (this.depth != other.depth) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.getPowerConsumption() != other.getPowerConsumption()) {
            return false;
        }
        return true;
    }

}
