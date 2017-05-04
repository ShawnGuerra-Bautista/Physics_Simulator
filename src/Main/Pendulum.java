package Main;

import static Main.ButtonsAndContainers.pause;
import static Main.ButtonsAndContainers.start;
import static Main.PhysicsInterface.gAcceleration;
import PropertyBean.monkeyPend;
import java.text.NumberFormat;
import java.text.ParseException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import static javafx.geometry.Orientation.VERTICAL;
import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.shape.StrokeLineCap.ROUND;
import javafx.util.StringConverter;

public class Pendulum extends ButtonsAndContainers implements PhysicsInterface {

    static Timeline timer;
    static Timeline graphTimer;
    static XYChart.Series series;
    private static LineChart<Number, Number> pendChart;
    private static double angle = zero;
    private static double angleIncrement;
    private static Slider angleSlider;
    private static Slider lengthSlider;
    private static double Xindex = zero;
    private static Rotate rotate;
    private static Point2D endPoint;
    private static final monkeyPend pend = new monkeyPend();

    protected static ImageView ImageForSecondLawNewton() {
        ImageView fieldImageView = new ImageView();
        Image fieldImage = new Image("Main/Image/jungle.jpg");
        fieldImageView.setImage(fieldImage);
        return fieldImageView;

    }

    @Override
    protected void InitializeContainers() {
        pause.setDisable(true);

        angleSlider = new angleSlider();
        Label sliderLabel = new Label("Initial angle (degrees)");
        sliderLabel.setLabelFor(angleSlider);
        angleSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rotate.setAngle(-angleSlider.getValue());
                endPoint = rotate.transform(pend.getLineEndX(), pend.getLineEndY());
                updatePosition();

            }
        });

        lengthSlider = new lengthSlider();
        Label sliderLabel2 = new Label("Length of rope (m)");
        sliderLabel2.setLabelFor(lengthSlider);
        lengthSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                endPoint = rotate.transform(pend.getLineEndX(), pend.getLineEndY());
                updatePosition();

            }
        });

        TextField periodTextField = new TextField();
        Label periodLabel = new Label("Period (s)");
        Bindings.bindBidirectional(periodTextField.textProperty(), lengthSlider.valueProperty(), new periodToStringConverter());
        
        TextField angularFreqTextField = new TextField();
        Label angularFreqLabel = new Label("Angular Frequency (rad/s)");
        Bindings.bindBidirectional(angularFreqTextField.textProperty(), lengthSlider.valueProperty(), new angularFreqToStringConverter());

        pend.setImage(new Image("Main/Image/monkey.png"));

        pend.pendLine.setStrokeLineCap(ROUND);
        pend.pendLine.setStrokeWidth(8);
        pend.pendLine.setStroke(Color.OLIVE);
        pend.setLineStartX(midScreen);
        pend.setLineStartY(two);
        pend.setLineEndX(midScreen);
        pend.getLineEndYProperty().bind(lengthSlider.valueProperty().multiply(oneTwenty));

        rotate = new Rotate(-angleSlider.getValue(), midScreen, two);
        pend.getLine().getTransforms().add(rotate);
        endPoint = rotate.transform(pend.getLineEndX(), pend.getLineEndY());
        updatePosition();

        Pane pendAndPane = new Pane();
        pendAndPane.getChildren().addAll(pend.getLine(), pend.getImageView());
        StackPane pendPane = new StackPane();
        pendPane.setAlignment(TOP_CENTER);
        pendPane.getChildren().addAll(ImageForSecondLawNewton(), pendAndPane);
        animationBox.getChildren().add(pendPane);

        VBox sliderAndText1 = new VBox();
        sliderAndText1.setSpacing(five);
        sliderAndText1.getChildren().addAll(sliderLabel, angleSlider);
        VBox sliderAndText2 = new VBox();
        sliderAndText2.setSpacing(five);
        sliderAndText2.getChildren().addAll(sliderLabel2, lengthSlider);
        VBox periodText = new VBox();
        Label pixelToMeter = new Label("120 px = 1 m");
        periodText.setSpacing(15);
        periodText.getChildren().addAll(periodLabel, periodTextField, angularFreqLabel, angularFreqTextField, pixelToMeter);
        HBox slidersAndTextField = new HBox();
        slidersAndTextField.setSpacing(oneHundred);
        slidersAndTextField.getChildren().addAll(sliderAndText1, sliderAndText2, periodText);
        buttonsBox.getChildren().add(slidersAndTextField);
        buttonsBox.setPadding(basicInset);
        buttonsBox.setVgap(15);
        super.InitializeContainers();
        graphBox.getChildren().add(LineChart());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Simple Pendulum");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Simple Pendulum Motion!\n"
                + "A simple pendulum is a point mass suspended from a string of negligible mass with a resonant frequency.\n"
                + "The period of the oscillation is given by T = 2π√(L/g) where T is the period (in seconds), L is the length of the string"
                + " (in meters) and g is the gravitational acceleration constant, 9.8 m/s^2. The angle of the pendulum's motion is determined"
                + " by the equation θ= θ_max*sin〖√(g/L)〗*t where θ is the angle (in degrees) at a certain time t (in seconds), θ_max is the"
                + " maximum angle (as well as the initial angle), g is the gravitational constant and L is the length of the string.\n");

        alert.showAndWait();

    }

    class lengthSlider extends Slider {

        lengthSlider() {
            setMin(oneHalf);
            setMax(3.50);
            setValue(two);
            setMajorTickUnit(oneHalf);
            setBlockIncrement(oneHalf);
            setOrientation(VERTICAL);
            setShowTickLabels(true);
            setShowTickMarks(true);
            setSnapToTicks(true);
        }
    }

    class angleSlider extends Slider {

        angleSlider() {
            setMin(zero);
            setMax(75);
            setValue(40);
            setMajorTickUnit(ten);
            setBlockIncrement(ten);
            setOrientation(VERTICAL);
            setShowTickLabels(true);
            setShowTickMarks(true);
            setSnapToTicks(true);

        }
    }

    protected static double getCurrentAngle() {
        return angleSlider.getValue() * -Math.cos(Math.sqrt(gAcceleration / lengthSlider.getValue()) * angleIncrement);
    }

    protected static void updatePosition() {
        pend.setImageViewX(endPoint.getX() - 40);
        pend.setImageViewY(endPoint.getY() - 60);
    }
    
    private class periodToStringConverter extends StringConverter<Number> {
        NumberFormat formatter = NumberFormat.getInstance();
        @Override
        public String toString(Number value) {
            return formatter.format(two * Math.PI * Math.sqrt(value.doubleValue() / gAcceleration));
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
    
    private class angularFreqToStringConverter extends StringConverter<Number> {

        NumberFormat formatter = NumberFormat.getInstance();
        @Override
        public String toString(Number value) {
            return formatter.format(Math.sqrt(gAcceleration/value.doubleValue()));
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
                endPoint = rotate.transform(pend.getLineEndX(), pend.getLineEndY());
                updatePosition();

                angleIncrement += 0.01;
                rotate.setAngle(getCurrentAngle());

            }
        }));

        graphTimer.getKeyFrames().add(new KeyFrame(Duration.millis(oneHundred), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
                series.getData().add(new XYChart.Data(Xindex, pend.getImageViewX() - 600));
                Xindex += 0.1;

            }
        }));        
        timer.setCycleCount(Animation.INDEFINITE);
        graphTimer.setCycleCount(Animation.INDEFINITE);
        timer.play();
        graphTimer.play();


    }

    protected static LineChart LineChart() {
        NumberAxis xAxis = new NumberAxis(zero, ten, 0.5);
        NumberAxis yAxis = new NumberAxis(-500, 500, oneHundred);
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Position (px)");
        pendChart = new LineChart<>(xAxis, yAxis);
        pendChart.setTitle("Position vs Time");
        pendChart.setPrefWidth(670);
        pendChart.setPrefHeight(265);
        pendChart.setLegendVisible(false);
        pendChart.setAnimated(false);
        pendChart.setCreateSymbols(false);
        series = new XYChart.Series();
        pendChart.getData().add(series);
        return pendChart;

    }

    protected static void Buttons() {

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Animate();
                start.setDisable(true);
                pause.setDisable(false);
                reset.setDisable(false);
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                graphTimer.stop();
                timer.stop();
                Xindex = zero;
                animationBox.getChildren().clear();
                buttonsBox.getChildren().clear();
                graphBox.getChildren().clear();
                start.setDisable(false);
                rotate.setAngle(zero);
                Main.MainMenu.root.getChildren().clear();
                Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage);
                Main.MainMenu.menuBar.setDisable(false);

            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                rotate.setAngle(-angleSlider.getValue());
                endPoint = rotate.transform(pend.getLineEndX(), pend.getLineEndY());
                angleIncrement = zero;
                Xindex = zero;
                updatePosition();
                timer.stop();
                graphTimer.stop();
                series.getData().clear();
                start.setDisable(false);
            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                timer.stop();
                graphTimer.stop();
                start.setDisable(false);
                pause.setDisable(true);
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Need help?");
                alert.setContentText("Move the sliders to change the value of the Angle and Angular Velocity "
                        + "and watch how the pendulum movement changes.");
                alert.showAndWait();
            }
        });
    }

}
