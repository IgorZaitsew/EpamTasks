package main.java.by.tc.task01.entity;

import java.util.Objects;

public final class Laptop extends Gadget {

    private String OS;
    private double CPU;

    public Laptop() {

    }

    public Laptop(int batteryCapacity, String OS, int memoryRom, int memotySystem, double CPU, int displayInches) {
        super(displayInches, batteryCapacity, memoryRom, memotySystem);
        this.OS = OS;
        this.CPU = CPU;
    }

    @Override
    public void setData(String[] values) {
        setBatteryCapacity(Integer.parseInt(values[0]));
        setOS(values[1]);
        setMemoryRom(Integer.parseInt(values[2]));
        setMemorySystem(Integer.parseInt(values[3]));
        setCPU(Double.parseDouble(values[4]));
        setDisplayInches(Integer.parseInt(values[5]));
    }

    public void setCPU(double CPU) {
        this.CPU = CPU;
    }

    private void setOS(String OS) {
        if (OS != null) {
            this.OS = OS;
        }
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
        return "Laptop{" + "OS=" + OS + ", " + super.toString() + ", CPU=" + CPU + '}';
    }

}
