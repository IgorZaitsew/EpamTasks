package main.java.by.tc.task01.entity;

public class Oven extends SocketDependeceAppliance {

    private int weight;
    private int capacity;
    private float depth;
    private float height;
    private float width;

    public Oven(){
        
    }
    
    public Oven(int powerConsumption, int weight, int capacity, float depth, float height, float width) {
        super(powerConsumption);
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    @Override
    public void setData(String[] values) {
        setPowerConsumption(Integer.parseInt(values[0]));
        weight = Integer.parseInt(values[1]);
        capacity = Integer.parseInt(values[2]);
        depth = Float.parseFloat(values[3]);
        height = Float.parseFloat(values[4]);
        width = Float.parseFloat(values[5]);
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

    @Override
    public String toString() {
        return "Oven{" + super.toString() + ", weight=" + weight + ", capacity=" + capacity + ", depth=" + depth + ", height=" + height + ", width=" + width + '}';
    }
}
