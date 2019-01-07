package main.java.by.tc.task01.entity;

import java.util.Objects;

public final class Laptop extends Gadget {

    private String OS;
    private double CPU;

    public Laptop(int displayInches, int batteryCapacity, int memoryRom, int memotySystem, String OS, double CPU) {
        super(displayInches, batteryCapacity, memoryRom, memotySystem);
        this.OS = OS;
        this.CPU = CPU;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.OS);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.CPU) ^ (Double.doubleToLongBits(this.CPU) >>> 32));
        hash = hash + super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laptop other = (Laptop) obj;
        if (Double.doubleToLongBits(this.CPU) != Double.doubleToLongBits(other.CPU)) {
            return false;
        }
        if (!Objects.equals(this.OS, other.OS)) {
            return false;
        }
        if (this.getDisplayInches() != other.getDisplayInches()) {
            return false;
        }
        if (this.getBatteryCapacity() != other.getBatteryCapacity()) {
            return false;
        }
        if (this.getMemoryRom() != other.getMemoryRom()) {
            return false;
        }
        if (this.getMemoryRom() != other.getMemorySystem()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Laptop{" + "OS=" + OS + super.toString() + ", CPU=" + CPU + '}';
    }

}
