package PropertyBean;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Car {

    final DoubleProperty carPositionX = new SimpleDoubleProperty(30);

    public double getCarPositionX() {
        return carPositionX.get();
    }

    public void setCarPositionX(double newValue) {
        carPositionX.set(newValue);
    }

    final DoubleProperty veloc = new SimpleDoubleProperty(0);

    public double getVeloc() {
        return veloc.get();
    }

    public void setVeloc(double newValue) {
        veloc.set(newValue);
    }

    final DoubleProperty accel = new SimpleDoubleProperty(0);

    public double getAccel() {
        return accel.get();
    }

    public void setAccel(double newValue) {
        accel.set(newValue);
    }

    final DoubleBinding carDisplacement = carPositionX.add(veloc).add(accel);

    public double getDisplacement() {
        return carDisplacement.get();
    }
    
    public DoubleBinding getDisplacementProperty(){
        return carDisplacement;
    }
    
    final ImageView carImageView =  new ImageView();
    
    public void setImage(Image image){
        carImageView.setImage(image);
    }
    
    public ImageView getImageView(){
        return carImageView;
    } 
    
    public void setX(double newX){
        carImageView.setTranslateX(newX);
    }
    
    public void setY(double newY) {
        carImageView.setTranslateY(newY);
    }

}
