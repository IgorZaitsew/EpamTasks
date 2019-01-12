package main.java.by.tc.task01.entity;

import java.util.Objects;

public final class TabletPC extends Gadget {

    private String color;

    public TabletPC() {

    }

    public TabletPC(int displayInches, int batteryCapacity, int memoryRom, int memotySystem, String color) {
        super(displayInches, batteryCapacity, memoryRom, memotySystem);
        this.color = color;
    }

    @Override
    public void setData(String[] values) {
        setBatteryCapacity(Integer.parseInt(values[0]));
        setDisplayInches(Integer.parseInt(values[1]));
        setMemoryRom(Integer.parseInt(values[2]));
        setMemorySystem(Integer.parseInt(values[3]));
        color = values[4];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.color);
        hash = hash + super.hashCode();
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
        final TabletPC other = (TabletPC) obj;
        if (!Objects.equals(this.color, other.color)) {
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
        return "TabletPC{" + super.toString() + ", color=" + color.trim() + '}';
    }

}
