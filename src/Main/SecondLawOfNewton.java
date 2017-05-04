package Main;

import static Main.ButtonsAndContainers.animationBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static javafx.geometry.Orientation.VERTICAL;
import static javafx.geometry.Pos.TOP_CENTER;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import PropertyBean.Car;
import java.text.NumberFormat;
import java.text.ParseException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.StringConverter;

public class SecondLawOfNewton extends ButtonsAndContainers implements PhysicsInterface {

    private static Timeline timer;
    static Timeline graphTimer;
    static double initVelocity;
    static DoubleProperty velocityIncrement = new SimpleDoubleProperty(zero);
    static DoubleProperty incrementListener = new SimpleDoubleProperty(zero);
    static DoubleBinding incrementBinding; 
    static double accelerationIncrement;
    static Slider forceSlider;
    static Slider massSlider;
    static Slider velocitySlider;
    static TextField positionText;
    static TextField velocityText;
    static XYChart.Series series;
    static LineChart<Number, Number> posChart;
    static double Xindex = zero;
    static Car car = new Car();

    protected static ImageView ImageForSecondLawNewton() {
        ImageView fieldImageView = new ImageView();
        fieldImageView.setImage(new Image("Main/Image/Road.jpg"));
        return fieldImageView;
    }

    protected static double pixelAcceleration() {
        return forceSlider.getValue() / (massSlider.getValue() * oneHundred);
    }

    protected class presetSlider extends Slider {

        presetSlider() {
            setOrientation(VERTICAL);
            setShowTickLabels(true);
            setShowTickMarks(true);
            setSnapToTicks(true);
        }
    }

    protected VBox textFieldBox() {
        VBox textFieldBox = new VBox();
        textFieldBox.setSpacing(ten);
        positionText = new TextField();
        positionText.setText("0");
        Bindings.bindBidirectional(positionText.textProperty(), car.getImageView().translateXProperty(), new displacementToStringConverter());
        Label positionLabel = new Label("Position (m)");
        positionLabel.setLabelFor(positionText);
        velocityText = new TextField();
        velocityText.setText("0");
        Bindings.bindBidirectional(velocityText.textProperty(), velocityIncrement, new velocToStringConverter());
        Label velocityLabel = new Label("Velocity (m/s)");
        velocityLabel.setLabelFor(velocityText);
        Label scaleLabel = new Label("1 m = 120 px");
        textFieldBox.getChildren().addAll(positionLabel, positionText, velocityLabel, velocityText, scaleLabel);
        return textFieldBox;
    }

    protected VBox forceSlider() {
        forceSlider = new presetSlider();
        forceSlider.setMin(zero);
        forceSlider.setMax(60);
        forceSlider.setValue(30);
        forceSlider.setMajorTickUnit(ten);
        forceSlider.setBlockIncrement(ten);
        Label forceSlideLabel = new Label("Applied force (N)");
        VBox forceVBox = new VBox();
        forceVBox.getChildren().addAll(forceSlideLabel, forceSlider);
        return forceVBox;
    }

    protected VBox massSlider() {
        massSlider = new presetSlider();
        massSlider.setMin(one);
        massSlider.setMax(five);
        massSlider.setValue(five);
        massSlider.setMajorTickUnit(one);
        massSlider.setBlockIncrement(one);
        Label massSlideLabel = new Label("Mass (kg)");
        VBox massVBox = new VBox();
        massVBox.getChildren().addAll(massSlideLabel, massSlider);
        return massVBox;
    }

    protected VBox velocitySlider() {
        velocitySlider = new presetSlider();
        velocitySlider.setMin(zero);
        velocitySlider.setMax(2.4);
        velocitySlider.setValue(zero);
        velocitySlider.setMajorTickUnit(0.6);
        velocitySlider.setBlockIncrement(0.6);
        Label velocitySlideLabel = new Label("Initial velocity (m/s)");
        VBox velocityVBox = new VBox();
        velocityVBox.getChildren().addAll(velocitySlideLabel, velocitySlider);
        return velocityVBox;
    }

    @Override
    protected void InitializeContainers() {
        pause.setDisable(true);

        car.setImage(new Image("Main/Image/car.png"));
        car.setY(355);
        car.getImageView().translateXProperty().bind(car.getDisplacementProperty());

        HBox carAndPane = new HBox();
        carAndPane.getChildren().add(car.getImageView());
        StackPane boxPane = new StackPane();
        boxPane.setAlignment(TOP_CENTER);
        boxPane.getChildren().addAll(ImageForSecondLawNewton(), carAndPane);
        animationBox.getChildren().add(boxPane);

        HBox sliderAndText = new HBox();
        sliderAndText.setSpacing(60);
        sliderAndText.getChildren().addAll(forceSlider(), massSlider(), velocitySlider(), textFieldBox());
        buttonsBox.getChildren().add(sliderAndText);
        buttonsBox.setPadding(basicInset);
        buttonsBox.setVgap(20);
        super.InitializeContainers();

        graphBox.getChildren().add(LineChart());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Coulomb's Law");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Newton's Second Law of Motion!\n"
                + "Newton's Second Law of Motion is a law of physics that dictates that the net force of an object of a certain mass is directly proportional to its acceleration. \n"
                + "The equation is F = m*a, where F is the net force (in Newtons) on the object, m is the object's mass (in kilograms) and a is its acceleration (in m/s^2).\n");

        alert.showAndWait();

    }
    
    private class displacementToStringConverter extends StringConverter<Number> {

        NumberFormat formatter = NumberFormat.getInstance();

        @Override
        public String toString(Number value) {
            return formatter.format(value.doubleValue()/oneTwenty);
        }

        @Override
        public Number fromString(String value) {
            try {
                return formatter.parse(value);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    
    private class velocToStringConverter extends StringConverter<Number> {

        NumberFormat formatter = NumberFormat.getInstance();
        @Override
        public String toString(Number value) {
            return formatter.format(velocitySlider.getValue() + 120*pixelAcceleration()*value.doubleValue());
        }

        @Override
        public Number fromString(String value) {
            try {
                return formatter.parse(value);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    protected static void Animate() {
        timer = new Timeline();
        graphTimer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.millis(ten), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                velocityIncrement.setValue(velocityIncrement.getValue() + 0.01);
                car.setAccel(car.getAccel() + pixelAcceleration());
                car.setVeloc(car.getVeloc() + (velocitySlider.getValue() * 1.2) + car.getAccel());

                if (car.getDisplacement() >= 1200) {
                    timer.stop();
                    graphTimer.stop();
                }
            }
        }));
        graphTimer.getKeyFrames().add(new KeyFrame(Duration.millis(oneHundred), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                series.getData().add(new XYChart.Data(Xindex, car.getDisplacement() / oneTwenty));
                Xindex += 0.1;
            }
        }));

        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
        graphTimer.setCycleCount(Animation.INDEFINITE);
        graphTimer.play();

    }

    protected static LineChart LineChart() {

        NumberAxis xAxis = new NumberAxis(zero, five, 0.25);
        NumberAxis yAxis = new NumberAxis(zero, ten, one);
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Position (m)");
        posChart = new LineChart<>(xAxis, yAxis);
        posChart.setTitle("Position vs Time");
        posChart.setPrefWidth(670);
        posChart.setPrefHeight(265);
        posChart.setLegendVisible(false);
        posChart.setAnimated(false);
        posChart.setCreateSymbols(false);
        series = new XYChart.Series();
        posChart.getData().add(series);
        return posChart;

    }

    protected static void Buttons() {

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Animate();
                start.setDisable(true);
                pause.setDisable(false);
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                start.setDisable(false);
                animationBox.getChildren().clear();
                buttonsBox.getChildren().clear();
                graphBox.getChildren().clear();
                Main.MainMenu.menuBar.setDisable(false);
                Main.MainMenu.root.getChildren().clear();
                Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage);

            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                velocityIncrement.setValue(zero);
                Xindex = zero;
                timer.stop();
                graphTimer.stop();
                series.getData().clear();
                car.setVeloc(zero);
                car.setAccel(zero);
                start.setDisable(false);

            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                reset.setDisable(false);
                timer.stop();
                graphTimer.stop();
                start.setDisable(false);
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Need help?");
                alert.setContentText("Move the sliders to adjust the values of Force, Mass and Velocity, then press"
                        + " Start and watch the vehicle move. The position will be graphed on the bottom-right chart.");
                alert.showAndWait();
            }
        });
    }

}
