package PropertyBean;

import Main.PhysicsInterface;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CoulombBean implements PhysicsInterface {

    private final DoubleProperty leftCharge = new SimpleDoubleProperty();

    public double getLeftCharge() {
        return leftCharge.get();
    }

    public void setLeftCharge(double value) {
        leftCharge.set(value);
    }

    public DoubleProperty leftChargeProperty() {
        return leftCharge;
    }

    private final DoubleProperty rightCharge = new SimpleDoubleProperty();

    public double getRightCharge() {
        return rightCharge.get();
    }

    public void setRightCharge(double value) {
        rightCharge.set(value);
    }

    public DoubleProperty rightChargeProperty() {
        return rightCharge;
    }
    private final DoubleProperty totalForce = new SimpleDoubleProperty();

    public double getTotalForce() {
        return totalForce.get();
    }

    public void setTotalForce(double value) {
        totalForce.set(value);
    }

    public DoubleProperty totalForceProperty() {
        return totalForce;
    }   
    
}