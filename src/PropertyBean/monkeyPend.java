package PropertyBean;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class monkeyPend {

    public final ImageView monkeyImageView = new ImageView();

    public void setImage(Image image) {
        monkeyImageView.setImage(image);
    }

    public ImageView getImageView() {
        return monkeyImageView;
    }

    public void setImageViewX(double newValue) {
        monkeyImageView.setLayoutX(newValue);
    }

    public double getImageViewX() {
        return monkeyImageView.getLayoutX();
    }

    public void setImageViewY(double newValue) {
        monkeyImageView.setLayoutY(newValue);
    }

    public double getImageViewY() {
        return monkeyImageView.getLayoutY();
    }

    public final Line pendLine = new Line();

    public Line getLine() {
        return pendLine;
    }

    public DoubleProperty getLineStartXProperty() {
        return pendLine.startXProperty();
    }

    public DoubleProperty getLineStartYProperty() {
        return pendLine.startYProperty();
    }

    public void setLineStartX(double newValue) {
        pendLine.setStartX(newValue);
    }

    public void setLineStartY(double newValue) {
        pendLine.setStartY(newValue);
    }

    public double getLineStartX() {
        return pendLine.getEndX();
    }

    public double getLineStartY() {
        return pendLine.getEndY();
    }

    public DoubleProperty getLineEndXProperty() {
        return pendLine.endXProperty();
    }

    public DoubleProperty getLineEndYProperty() {
        return pendLine.endYProperty();
    }

    public void setLineEndX(double newValue) {
        pendLine.setEndX(newValue);
    }

    public void setLineEndY(double newValue) {
        pendLine.setEndY(newValue);
    }

    public double getLineEndX() {
        return pendLine.getEndX();
    }

    public double getLineEndY() {
        return pendLine.getEndY();
    }

}
