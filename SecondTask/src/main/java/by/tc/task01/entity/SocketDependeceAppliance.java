/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.by.tc.task01.entity;

public abstract class SocketDependeceAppliance extends Appliance {

    private int powerConsumption;

    SocketDependeceAppliance() {

    }

    SocketDependeceAppliance(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public String toString() {
        return "powerConsumption=" + powerConsumption;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.powerConsumption;
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
        final SocketDependeceAppliance other = (SocketDependeceAppliance) obj;
        if (this.powerConsumption != other.powerConsumption) {
            return false;
        }
        return true;
    }

}
