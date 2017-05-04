package PropertyBean;

import Main.PhysicsInterface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ProjectileBean implements PhysicsInterface {

    private final DoubleProperty xVelocity = new SimpleDoubleProperty();

    public double getxVelocity() {
        return xVelocity.get();
    }

    public void setxVelocity(double value) {
        xVelocity.set(value);
    }

    public DoubleProperty xVelocityProperty() {
        return xVelocity;
    }

    private final DoubleProperty yVelocity = new SimpleDoubleProperty();

    public double getyVelocity() {
        return yVelocity.get();
    }

    public void setyVelocity(double value) {
        yVelocity.set(value);
    }

    public DoubleProperty yVelocityProperty() {
        return yVelocity;
    }
    private final DoubleProperty xMaximum = new SimpleDoubleProperty();

    public double getxMaximum() {
        return xMaximum.get();
    }

    public void setxMaximum(double value) {
        xMaximum.set(value);
    }

    public DoubleProperty xMaximumProperty() {
        return xMaximum;
    }
    private final DoubleProperty timeMaximum = new SimpleDoubleProperty();

    public double getTimeMaximum() {
        return timeMaximum.get();
    }

    public void setTimeMaximum(double value) {
        timeMaximum.set(value);
    }

    public DoubleProperty timeMaximumProperty() {
        return timeMaximum;
    }
    private final DoubleProperty xPosition = new SimpleDoubleProperty();

    public double getxPosition() {
        return xPosition.get();
    }

    public void setxPosition(double value) {
        xPosition.set(value);
    }

    public DoubleProperty xPositionProperty() {
        return xPosition;
    }
    private final DoubleProperty yPosition = new SimpleDoubleProperty();

    public double getyPosition() {
        return yPosition.get();
    }

    public void setyPosition(double value) {
        yPosition.set(value);
    }

    public DoubleProperty yPositionProperty() {
        return yPosition;
    }
}