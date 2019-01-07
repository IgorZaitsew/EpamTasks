/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.tc.task01.entity;

public class Gadget extends Appliance {

    private int displayInches;
    private int batteryCapacity;
    private int memoryRom;
    private int memorySystem;

    public Gadget(int displayInches, int batteryCapacity, int memoryRom, int memorySystem) {
        this.displayInches = displayInches;
        this.batteryCapacity = batteryCapacity;
        this.memoryRom = memoryRom;
        this.memorySystem = memorySystem;
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
    public String toString() {
        return "displayInches=" + displayInches + ", batteryCapacity=" + batteryCapacity + ", memoryRom=" + memoryRom + ", memotySystem=" + memorySystem;
    }

}
