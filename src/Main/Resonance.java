package Main;

import static Main.ButtonsAndContainers.animationBox;
import static Main.ButtonsAndContainers.pause;
import static Main.ButtonsAndContainers.start;
import PropertyBean.ResonanceProperties;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Resonance extends ButtonsAndContainers implements PhysicsInterface {

    static private ResonanceProperties resonanceValues = new ResonanceProperties();

    static private Slider orderOfHarmonicSlider = new Slider();

    static private HBox sliderAndText = new HBox();

    static private Label orderOfHarmonicLabel = new Label("Order of Harmonic");
    static private Label wavelengthLabel = new Label("Wavelength (m)");
    static private Label frequencyLabel = new Label("Frequency (Hz)");

    static private TextField orderOfHarmonicTextField = new TextField();
    static private TextField wavelengthTextField = new TextField();
    static private TextField frequencyTextField = new TextField();

    static private ArrayList<Circle> particles = new ArrayList<Circle>();
    static private ArrayList<Line> lineSlope = new ArrayList<Line>();
    static private ArrayList<PathTransition> pathsList = new ArrayList<PathTransition>();

    static private PathTransition pathTransition;
    static private Path path;
    static private LineChart lineChart;
    static private XYChart.Series series;
    static private Timeline graphTimer;

    protected static ImageView ImageForResonance() {
        ImageView singleClosedPipeImageView = new ImageView();
        Image singleClosedPipeImage = new Image("Main/Image/Soundwave.jpg");
        singleClosedPipeImageView.setImage(singleClosedPipeImage);
        return singleClosedPipeImageView;
    }

    protected static VBox ResonanceSliders() {
        orderOfHarmonicSlider.setMin(one);
        orderOfHarmonicSlider.setMax(five);
        orderOfHarmonicSlider.setValue(one);
        orderOfHarmonicSlider.setShowTickLabels(true);
        orderOfHarmonicSlider.setShowTickMarks(true);
        orderOfHarmonicSlider.setSnapToTicks(true);
        orderOfHarmonicSlider.setMinorTickCount(zero);
        orderOfHarmonicSlider.setMajorTickUnit(one);
        orderOfHarmonicSlider.setBlockIncrement(one);
        orderOfHarmonicSlider.setMinWidth(150);

        VBox resonanceSlidersVBox = new VBox();
        orderOfHarmonicTextField.setEditable(false);
        resonanceSlidersVBox.getChildren().addAll(orderOfHarmonicLabel, orderOfHarmonicTextField, orderOfHarmonicSlider);
        return resonanceSlidersVBox;
    }

    protected static VBox WavelengthTextFieldAndLabel() {
        VBox wavelengthVBox = new VBox();
        wavelengthTextField.setEditable(false);
        wavelengthVBox.getChildren().addAll(wavelengthLabel, wavelengthTextField);

        return wavelengthVBox;
    }

    protected static VBox FrequencyTextFieldAndLabel() {
        VBox frequencyVBox = new VBox();
        frequencyTextField.setEditable(false);
        frequencyVBox.getChildren().addAll(frequencyLabel, frequencyTextField);

        return frequencyVBox;
    }

    protected static void DrawingParticles() {
        for (int x = 180; x <= 1080; x += 60) {
            for (int y = 150; y <= 210; y += 60) {
                particles.add(new Circle(x, y, five, BLUE));
            }
        }
        for (int x = 210; x <= 1050; x += 60) {
            for (int y = 120; y <= 240; y += 60) {
                particles.add(new Circle(x, y, five, BLUE));
            }
        }

    }

    protected static LineChart LineChart() {
        final NumberAxis xAxis = new NumberAxis(zero, ten, 0.5);
        final NumberAxis yAxis = new NumberAxis(-150, 150, 25);
        xAxis.setLabel("Time (s*1000)");
        yAxis.setLabel("Displacement of particles "
                + "\n\tat the amplitude "
                + "\n\t\t(pixels)");
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setPrefWidth(670);
        lineChart.setPrefHeight(265);
        lineChart.setTitle("Time vs. Displacement");
        lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false);

        series = new XYChart.Series();
        lineChart.setCreateSymbols(false);
        lineChart.getData().add(series);

        return lineChart;
    }

    protected static void AnimatedGraph() {
        graphTimer = new Timeline();
        graphTimer.getKeyFrames().add(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {

                series.getData().add(new XYChart.Data(resonanceValues.getTime(), particles.get(14).getTranslateX()));
                resonanceValues.setTime(resonanceValues.getTime() + 0.1);

                if (resonanceValues.getTime() > ten) {
                    graphTimer.stop();
                }

            }
        }));
        graphTimer.setCycleCount(Timeline.INDEFINITE);
    }

    protected static void Animation() {
        int index = zero;
        for (Circle circle : particles) {
            path = new Path();

            resonanceValues.setAmplitudeMultiple((particles.get(index).getCenterX() - 180) * resonanceValues.kProperty().getValue());
            resonanceValues.setAmplitude(resonanceValues.amplitudeMaxProperty().getValue() * (Math.sin(resonanceValues.getAmplitudeMultiple())));
            resonanceValues.setPositiveCosine(particles.get(index).getCenterX() + resonanceValues.getAmplitude() * 1);
            resonanceValues.setNegativeCosine(particles.get(index).getCenterX() + resonanceValues.getAmplitude() * -1);

            path.getElements().add(new MoveTo(resonanceValues.getPositiveCosine(), particles.get(index).getCenterY()));
            path.getElements().add(new HLineTo(resonanceValues.getNegativeCosine()));

            pathTransition = new PathTransition();

            pathTransition.setDuration(Duration.seconds(500 / resonanceValues.frequencyProperty().getValue()));
            pathTransition.setNode(particles.get(index));
            pathTransition.setPath(path);
            pathTransition.setCycleCount(Timeline.INDEFINITE);
            pathTransition.setAutoReverse(true);
            pathsList.add(pathTransition);

            pathTransition.play();
            index++;
        }
    }

    protected void DynamicSliders() {
        resonanceValues.orderOfHarmonicProperty().bind(orderOfHarmonicSlider.valueProperty());

        resonanceValues.orderOfHarmonicProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                orderOfHarmonicTextField.setText(String.format("%.2f", new_val));
            }
        });

        resonanceValues.wavelengthProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                wavelengthTextField.setText(String.format("%.2f", (double) new_val));
            }
        });

        resonanceValues.frequencyProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                frequencyTextField.setText(String.format("%.2f", (double) new_val));
            }
        });
    }

    protected void InitializeContainers() {
        pause.setDisable(true);
        reset.setDisable(true);
        DynamicSliders();
        AnimatedGraph();

        sliderAndText.setSpacing(60);
        sliderAndText.getChildren().addAll(ResonanceSliders(), WavelengthTextFieldAndLabel(), FrequencyTextFieldAndLabel());
        buttonsBox.getChildren().addAll(sliderAndText);
        animationBox.getChildren().add(ImageForResonance());
        graphBox.getChildren().add(LineChart());
        buttonsBox.setPadding(new Insets(15, 20, 15, 20));
        buttonsBox.setVgap(20);

        super.InitializeContainers();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Law Of Ohm");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Resonance in a pipe!\n"
                + "A simple pipe with a closed end with particles moving in certain way are seen in the animation.\n"
                + "The frequency in which the particles move is given by f = v/λ."
                + " The wavelength in which the particles move is given by λ = (4/n)L where L is equals to 1."
                + " Try to change the order of harmonic using the sliders to see how the particles move in different ways."
                + " Press start to begin the animation."
                + " Moreover, try to see what happens to the displacement.\n");

        alert.showAndWait();

    }

    protected static void Buttons() {

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                orderOfHarmonicSlider.setDisable(true);
                start.setDisable(true);
                if (pause.isDisable() == true) {
                    DrawingParticles();
                    Animation();
                    graphTimer.play();
                    animationBox.getChildren().addAll(particles);
                    graphBox.getChildren().addAll(lineSlope);
                    pause.setDisable(false);
                    reset.setDisable(false);
                } else {
                    graphTimer.play();
                    int index = zero;
                    for (PathTransition paths : pathsList) {
                        pathsList.get(index).play();
                        index++;
                    }
                }
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                resonanceValues.setTime(zero);
                orderOfHarmonicSlider.setDisable(false);
                start.setDisable(false);
                pause.setDisable(true);
                reset.setDisable(true);

                animationBox.getChildren().clear();
                buttonsBox.getChildren().clear();
                graphBox.getChildren().clear();
                sliderAndText.getChildren().clear();
                graphTimer.stop();
                series.getData().clear();

                Main.MainMenu.menuBar.setDisable(false);
                Main.MainMenu.root.getChildren().clear();
                Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage);

            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                resonanceValues.setTime(zero);
                reset.setDisable(true);
                pause.setDisable(true);
                start.setDisable(false);
                orderOfHarmonicSlider.setDisable(false);
                animationBox.getChildren().removeAll(particles);
                particles.clear();
                graphTimer.stop();
                series.getData().clear();
            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                graphTimer.stop();
                int index = zero;
                for (PathTransition paths : pathsList) {
                    pathsList.get(index).pause();
                    index++;
                }
                start.setDisable(false);
            }
        });

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Need help?");
                alert.setContentText("Move the sliders to change the value of the order of harmonic. "
                        + "Notice that as the order of harmonic increases, the period decreases. ");

                alert.showAndWait();
            }
        });
    }

}
