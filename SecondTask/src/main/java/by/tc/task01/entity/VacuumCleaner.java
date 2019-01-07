package main.java.by.tc.task01.entity;

import java.util.Objects;

public class VacuumCleaner extends SocketDependeceAppliance {

    private char filterType;
    private String bagType;
    private String wandType;
    private int motorSpeedRegulation;
    private int cleaningWidth;

    public VacuumCleaner(char filterType, String bagType, String wandType, int motorSpeedRegulation, int cleaningWidth, int powerConsumption) {
        super(powerConsumption);
        this.filterType = filterType;
        this.bagType = bagType;
        this.wandType = wandType;
        this.motorSpeedRegulation = motorSpeedRegulation;
        this.cleaningWidth = cleaningWidth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.filterType;
        hash = 73 * hash + Objects.hashCode(this.bagType);
        hash = 73 * hash + Objects.hashCode(this.wandType);
        hash = 73 * hash + this.motorSpeedRegulation;
        hash = 73 * hash + this.cleaningWidth;
        hash = 73 * hash + this.getPowerConsumption();
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
        final VacuumCleaner other = (VacuumCleaner) obj;
        if (this.filterType != other.filterType) {
            return false;
        }
        if (this.motorSpeedRegulation != other.motorSpeedRegulation) {
            return false;
        }
        if (this.cleaningWidth != other.cleaningWidth) {
            return false;
        }
        if (!Objects.equals(this.bagType, other.bagType)) {
            return false;
        }
        if (!Objects.equals(this.wandType, other.wandType)) {
            return false;
        }
        if (this.getPowerConsumption() != other.getPowerConsumption()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" + super.toString() + "filterType=" + filterType + ", bagType=" + bagType + ", wandType=" + wandType + ", motorSpeedRegulation=" + motorSpeedRegulation + ", cleaningWidth=" + cleaningWidth + '}';
    }

}
