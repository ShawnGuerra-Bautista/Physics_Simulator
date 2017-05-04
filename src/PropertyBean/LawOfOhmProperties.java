package PropertyBean;

import static javafx.beans.binding.Bindings.divide;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LawOfOhmProperties {

    public final ImageView particleImageView = new ImageView();

    public void setImage(Image image, int x, int y) {
        particleImageView.setImage(image);
        particleImageView.setLayoutX(x);
        particleImageView.setLayoutY(y);
    }

    public ImageView getImageView() {
        return particleImageView;
    }
    
    private final DoubleProperty current = new SimpleDoubleProperty();

    public double getCurrent() {
        return current.get();
    }

    public void setCurrent(double value) {
        current.set(value);
    }

    public DoubleProperty currentProperty() {
        current.bind(divide(voltageProperty(), resistanceProperty()));
        return current;
    }
    private final DoubleProperty speedOfParticle = new SimpleDoubleProperty();

    public double getSpeedOfParticle() {
        return speedOfParticle.get();
    }

    public void setSpeedOfParticle(double value) {
        speedOfParticle.set(value);
    }

    public DoubleProperty speedOfParticleProperty() {
        return speedOfParticle;
    }
    
    private final DoubleProperty voltage = new SimpleDoubleProperty();

    public double getVoltage() {
        return voltage.get();
    }

    public void setVoltage(double value) {
        voltage.set(value);
    }

    public DoubleProperty voltageProperty() {
        return voltage;
    }
    private final DoubleProperty resistance = new SimpleDoubleProperty();

    public double getResistance() {
        return resistance.get();
    }

    public void setResistance(double value) {
        resistance.set(value);
    }

    public DoubleProperty resistanceProperty() {
        return resistance;
    }

}
