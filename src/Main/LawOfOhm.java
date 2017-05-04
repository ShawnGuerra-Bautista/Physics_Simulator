package Main;

import static Main.ButtonsAndContainers.animationBox;
import static Main.ButtonsAndContainers.buttonsBox;
import static Main.ButtonsAndContainers.start;
import PropertyBean.LawOfOhmProperties;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import static javafx.animation.Interpolator.LINEAR;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

public class LawOfOhm extends ButtonsAndContainers implements PhysicsInterface {

    static LawOfOhmProperties lawOfOhmValues = new LawOfOhmProperties();

    static private Slider resistanceSlider = new Slider();
    static private Slider voltageSlider = new Slider();

    static private HBox sliderAndText = new HBox();

    static private TextField voltageTextField = new TextField();
    static private TextField resistanceTextField = new TextField();
    static private TextField currentTextField = new TextField();

    static private LineChart lineChart;
    static private XYChart.Series series;
    static private PathTransition pathTransition;

    static private ArrayList<Node> particles = new ArrayList<Node>();

    protected static ImageView SparkBackGround() {
        ImageView Space = new ImageView();
        Image SImage = new Image("Main/Image/Spark.jpg");
        Space.setImage(SImage);
        return Space;
    }

    protected static void UnmovingParticlesForResistor() {
        int rowMax = (int) (parseDouble(resistanceTextField.getText()) / ten);
        for (int row = 0; row < rowMax; row++) {
            for (int column = 0; column < 10; column++) {
                particles.add(new Circle(460 + row * 21, 256 + column * ten, two, BLACK));
            }
        }
    }

    protected static VBox resistanceSlider() {
        Label resistanceLabel = new Label("Resistance (Ω)");
        resistanceSlider.setMin(ten);
        resistanceSlider.setMax(oneHundred);
        resistanceSlider.setValue(ten);
        resistanceSlider.setShowTickLabels(true);
        resistanceSlider.setShowTickMarks(true);
        resistanceSlider.setMajorTickUnit(ten);
        resistanceSlider.setBlockIncrement(ten);
        resistanceSlider.setMinWidth(150);
        VBox resistanceVBox = new VBox();
        resistanceTextField.setEditable(false);
        resistanceVBox.getChildren().addAll(resistanceLabel, resistanceTextField, resistanceSlider);
        return resistanceVBox;
    }

    protected static VBox voltageSlider() {
        Label voltageLabel = new Label("Voltage (V)");
        voltageSlider.setMin(one);
        voltageSlider.setMax(ten);
        voltageSlider.setValue(one);
        voltageSlider.setShowTickLabels(true);
        voltageSlider.setShowTickMarks(true);
        voltageSlider.setMajorTickUnit(one);
        voltageSlider.setBlockIncrement(one);
        voltageSlider.setMinWidth(150);
        VBox voltageVBox = new VBox();
        voltageTextField.setEditable(false);
        voltageVBox.getChildren().addAll(voltageLabel, voltageTextField, voltageSlider);
        return voltageVBox;
    }

    protected static VBox currentTextFieldAndLabel() {
        Label currentLabel = new Label("Current (mA)");
        VBox currentVBox = new VBox();
        currentTextField.setEditable(false);
        currentVBox.getChildren().addAll(currentLabel, currentTextField);

        return currentVBox;
    }

    protected static void DynamicSliders() {
        
        lawOfOhmValues.resistanceProperty().bind(resistanceSlider.valueProperty());

        lawOfOhmValues.resistanceProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                resistanceTextField.setText(String.format("%.2f", new_val));
            }
        });

        lawOfOhmValues.voltageProperty().bind(voltageSlider.valueProperty());

        lawOfOhmValues.voltageProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                voltageTextField.setText(String.format("%.2f", new_val));
            }
        });

        lawOfOhmValues.currentProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                currentTextField.setText(String.format("%.2f", (double) new_val * 1000));
            }
        });
    }

    protected static void Animation() {
        Path path;
        pathTransition = new PathTransition();

        path = new Path();
        path.getElements().addAll(new MoveTo(zero, zero), new LineTo(410, zero), new VLineTo(250), 
                new LineTo(-85, 250), new LineTo(-85, 250), new VLineTo(zero), new LineTo(zero, zero));

        lawOfOhmValues.setSpeedOfParticle(11-lawOfOhmValues.getCurrent()*ten);

        pathTransition.setDuration(Duration.seconds(lawOfOhmValues.getSpeedOfParticle()));
        pathTransition.setNode(lawOfOhmValues.getImageView());
        pathTransition.setPath(path);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setInterpolator(LINEAR);
        pathTransition.setAutoReverse(false);

        pathTransition.play();

    }

    protected static LineChart LineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Current (mA)");
        yAxis.setLabel("Voltage (V)");
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setPrefWidth(670);
        lineChart.setPrefHeight(265);
        lineChart.setTitle("Current vs. Voltage");
        lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);

        series = new XYChart.Series();
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);

        return lineChart;
    }

    protected static void Graph() {
        series.getData().add(new XYChart.Data(zero, zero));
        series.getData().add(new XYChart.Data(parseDouble(currentTextField.getText()), parseDouble(voltageTextField.getText())));

    }

    protected void InitializeContainers() {
        pause.setDisable(true);
        reset.setDisable(true);

        animationBox.getChildren().add(SparkBackGround());
        animationBox.getChildren().addAll(lawOfOhmValues.getImageView());
        graphBox.getChildren().add(LineChart());

        DynamicSliders();
        lawOfOhmValues.setImage(new Image("Main/Image/Particle.png"), 500, 50);

        sliderAndText.setSpacing(60);
        sliderAndText.getChildren().addAll(voltageSlider(), resistanceSlider(), currentTextFieldAndLabel());
        buttonsBox.getChildren().addAll(sliderAndText);
        buttonsBox.setPadding(new Insets(15, 20, 15, 20));
        buttonsBox.setVgap(20);
        super.InitializeContainers();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Law Of Ohm");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Law Of Ohm!\n"
                + "A simple circuit with a battery and a resistor is seen in the animation.\n"
                + "The current of the circuit is given by I = V/R where I is the current, R is the resistance and V is the voltage of the battery."
                + " Try to change the values of the voltage and the resistance using the sliders provided to see what happens to the current."
                + " Press start to begin the animation."
                + " You'll see a particle moving at certain speeds showing how the circuit is letting current to flow."
                + " Moreover, a circuit is following Ohm's law when V/I is constant; try to see if the graph obeys that.\n");

        alert.showAndWait();
    }

    protected static void Buttons() {

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                resistanceSlider.setDisable(true);
                voltageSlider.setDisable(true);
                start.setDisable(true);

                if (pause.isDisable() == true) {
                    Graph();
                    Animation();
                    pause.setDisable(false);
                    reset.setDisable(false);
                    UnmovingParticlesForResistor();

                    animationBox.getChildren().addAll(particles);

                } else {
                    pathTransition.play();
                }
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                resistanceSlider.setDisable(false);
                voltageSlider.setDisable(false);
                start.setDisable(false);
                pause.setDisable(true);
                reset.setDisable(true);
                particles.clear();
                MainMenu.menuBar.setDisable(false);
                animationBox.getChildren().clear();
                buttonsBox.getChildren().clear();
                graphBox.getChildren().clear();
                sliderAndText.getChildren().clear();
                Main.MainMenu.root.getChildren().clear();
                Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage);
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {

                animationBox.getChildren().removeAll(particles);

                reset.setDisable(true);
                pause.setDisable(true);
                start.setDisable(false);
                resistanceSlider.setDisable(false);
                voltageSlider.setDisable(false);
                voltageSlider.setValue(one);
                resistanceSlider.setValue(ten);

                series.getData().clear();

                pathTransition.jumpTo(Duration.ZERO);
                pathTransition.stop();
                particles.clear();

            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                start.setDisable(false);
                pathTransition.pause();
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Need help?");
                alert.setContentText("Move the sliders to change the value of the Resistance and Voltage. "
                        + "Notice that the graph has a constant slope meaning that the resistance is constant. "
                        + "That is when a circuit obeys Ohm's law. ");

                alert.showAndWait();
            }
        });

    }

}
