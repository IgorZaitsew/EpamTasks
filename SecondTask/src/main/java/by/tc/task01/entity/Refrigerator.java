package main.java.by.tc.task01.entity;

public class Refrigerator extends SocketDependeceAppliance {

    private int weight;
    private double freezerCapacity;
    private double overallCapacity;
    private double height;
    private double width;

    public Refrigerator(int weight, double freezerCapacity, double overallCapacity, int height, int width, int powerConsumption) {
        super(powerConsumption);
        this.weight = weight;
        this.freezerCapacity = freezerCapacity;
        this.overallCapacity = overallCapacity;
        this.height = height;
        this.width = width;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.weight;
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.freezerCapacity) ^ (Double.doubleToLongBits(this.freezerCapacity) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.overallCapacity) ^ (Double.doubleToLongBits(this.overallCapacity) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 13 * hash + this.getPowerConsumption();
        return hash;
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
        final Refrigerator other = (Refrigerator) obj;
        if (this.weight != other.weight) {
            return false;
        }
        if (Double.doubleToLongBits(this.freezerCapacity) != Double.doubleToLongBits(other.freezerCapacity)) {
            return false;
        }
        if (Double.doubleToLongBits(this.overallCapacity) != Double.doubleToLongBits(other.overallCapacity)) {
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

    @Override
    public String toString() {
        return "Refrigerator{" + super.toString() + "weight=" + weight + ", freezerCapacity=" + freezerCapacity + ", overallCapacity=" + overallCapacity + ", height=" + height + ", width=" + width + '}';
    }

}
