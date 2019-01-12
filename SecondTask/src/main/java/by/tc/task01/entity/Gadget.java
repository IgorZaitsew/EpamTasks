/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.tc.task01.entity;

public abstract class Gadget extends Appliance {

    private int displayInches;
    private int batteryCapacity;
    private int memoryRom;
    private int memorySystem;

    public Gadget() {

    }

    public Gadget(int displayInches, int batteryCapacity, int memoryRom, int memorySystem) {
        this.displayInches = displayInches;
        this.batteryCapacity = batteryCapacity;
        this.memoryRom = memoryRom;
        this.memorySystem = memorySystem;
    }

    public int getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(int displayInches) {
        this.displayInches = displayInches;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(int memoryRom) {
        this.memoryRom = memoryRom;
    }

    public int getMemorySystem() {
        return memorySystem;
    }

    public void setMemorySystem(int memotySystem) {
        this.memorySystem = memotySystem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.displayInches;
        hash = 59 * hash + this.batteryCapacity;
        hash = 59 * hash + this.memoryRom;
        hash = 59 * hash + this.memorySystem;
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
        final Gadget other = (Gadget) obj;
        if (this.displayInches != other.displayInches) {
            return false;
        }
        if (this.batteryCapacity != other.batteryCapacity) {
            return false;
        }
        if (this.memoryRom != other.memoryRom) {
            return false;
        }
        if (this.memorySystem != other.memorySystem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " batteryCapacity=" + batteryCapacity + ", displayInches=" + displayInches + ", memoryRom=" + memoryRom + ", memotySystem=" + memorySystem;
    }

}
