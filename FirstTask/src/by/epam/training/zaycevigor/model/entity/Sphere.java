package by.epam.training.zaycevigor.model.entity;

import by.epam.training.zaycevigor.model.logic.SphereMathCalc;
import by.epam.training.zaycevigor.model.util.exceptions.SphereException;
import org.apache.log4j.Logger;

public class Sphere {

    private static Logger log = Logger.getLogger(Sphere.class);

    private Point centralPoint;
    private double rad;
    public static final double BASIC_RAD = 1.5;
    public static final String NULL_COORD_EXC_MESSAGE = "Coordinates is not inialized";
    public static final String WRONG_RAD_EXC_MESSAGE = "Radius is not valid";

    {
        rad = BASIC_RAD;
    }

    public Sphere() {
        centralPoint = new Point();
    }

    public Sphere(double x, double y) {
        centralPoint = new Point(x, y);
    }

    public Sphere(double x, double y, double rad) throws SphereException {
        if (rad <= 0) {
            log.error(WRONG_RAD_EXC_MESSAGE);
            throw new SphereException(WRONG_RAD_EXC_MESSAGE);
        }
        centralPoint = new Point(x, y);
        this.rad = rad;
    }

    public Sphere(double x, double y, double z, double rad) throws SphereException {
        if (rad <= 0) {
            log.error(WRONG_RAD_EXC_MESSAGE);
            throw new SphereException(WRONG_RAD_EXC_MESSAGE);
        }
        centralPoint = new Point(x, y, z);
        this.rad = rad;
    }

    public void setPoint(Point coord) throws SphereException {
        if (coord == null) {
            log.error(WRONG_RAD_EXC_MESSAGE);
            throw new SphereException(NULL_COORD_EXC_MESSAGE);
        }
        centralPoint = coord;
    }

    public double getX() {
        return centralPoint.getX();
    }

    public double getY() {
        return centralPoint.getY();
    }

    public double getZ() {
        return centralPoint.getZ();
    }

    public void setCoord(double x, double y, double z) {
        centralPoint.setCoord(x, y, z);
    }

    public Point getCoord() {
        return centralPoint;
    }

    public void setRad(double rad) throws SphereException {
        if (rad <= 0) {
            log.error(WRONG_RAD_EXC_MESSAGE);
            throw new SphereException(WRONG_RAD_EXC_MESSAGE);

        }
        this.rad = rad;
    }

    public double getRad() {
        return rad;
    }

    @Override
    public String toString() {
        return centralPoint.toString() + ", rad=" + rad;
    }
}
