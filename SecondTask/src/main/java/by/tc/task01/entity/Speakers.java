package main.java.by.tc.task01.entity;

import java.util.Objects;

public class Speakers extends SocketDependeceAppliance {

    private int numberOfSpeakers;
    private String frequencyRange;
    private int coordLength;

    public Speakers(int numberOfSpeakers, String frequencyRange, int coordLength, int powerConsumption) {
        super(powerConsumption);
        this.numberOfSpeakers = numberOfSpeakers;
        this.frequencyRange = frequencyRange;
        this.coordLength = coordLength;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.numberOfSpeakers;
        hash = 83 * hash + Objects.hashCode(this.frequencyRange);
        hash = 83 * hash + this.coordLength;
        hash = 83 * hash + this.getPowerConsumption();
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
        final Speakers other = (Speakers) obj;
        if (this.numberOfSpeakers != other.numberOfSpeakers) {
            return false;
        }
        if (this.coordLength != other.coordLength) {
            return false;
        }
        if (!Objects.equals(this.frequencyRange, other.frequencyRange)) {
            return false;
        }
        if (this.getPowerConsumption() != other.getPowerConsumption()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Speakers{" + this.toString() + "numberOfSpeakers=" + numberOfSpeakers + ", frequencyRange=" + frequencyRange + ", coordLength=" + coordLength + '}';
    }

}
