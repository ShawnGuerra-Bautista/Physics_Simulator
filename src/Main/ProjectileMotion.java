package Main;

import static Main.ButtonsAndContainers.buttonsBox;
import PropertyBean.ProjectileBean;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class ProjectileMotion extends ButtonsAndContainers implements PhysicsInterface {

    private static ImageView FootballImage, ReceiverImage;
    static Timeline gTimer;
    private static TextField speedtf, angletf;
    private static Double xmax, yv, xv, txmax, y, x;
    private static LineChart lineChart;
    private static XYChart.Series seriesF;
    static double time = z;
    static ProjectileBean app = new ProjectileBean();

    protected static ImageView FootballField() {
        ImageView FootballFieldImage = new ImageView();
        Image FfImage = new Image("Main/Image/FootballField.jpg");
        FootballFieldImage.setImage(FfImage);
        return FootballFieldImage;
    }

    protected static ImageView Football() {
        FootballImage = new ImageView();
        Image FImage = new Image("Main/Image/Football.png");
        FootballImage.setImage(FImage);
        FootballImage.setLayoutX(50);
        FootballImage.setLayoutY(300);
        return FootballImage;
    }

    protected static ImageView QuarterBack() {
        ImageView QuarterBackImage = new ImageView();
        Image QImage = new Image("Main/Image/QuarterBack.png");
        QuarterBackImage.setImage(QImage);
        QuarterBackImage.setLayoutX(25);
        QuarterBackImage.setLayoutY(305);
        return QuarterBackImage;
    }

    protected static ImageView Receiver() {
        ReceiverImage = new ImageView();
        Image RImage = new Image("Main/Image/Receiver.png");
        ReceiverImage.setImage(RImage);
        ReceiverImage.setLayoutX(0);
        ReceiverImage.setLayoutY(300);
        return ReceiverImage;
    }

    protected static LineChart LineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Position");
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false);
        seriesF = new XYChart.Series();
        seriesF.setName("Time vs Position");
        lineChart.setPrefSize(640, 255);
        lineChart.getData().add(seriesF);
        return lineChart;
    }

    protected static void Calculate() {
        y = z;
        x = z;
        xmax = z;
        yv = z;
        txmax = z;
        app.setxVelocity(Double.parseDouble(speedtf.getText()) * Math.cos(Math.toRadians(Double.parseDouble(angletf.getText()))));
        app.setyVelocity(Double.parseDouble(speedtf.getText()) * Math.sin(Math.toRadians(Double.parseDouble(angletf.getText()))));
        xv = app.getxVelocity();
        yv = app.getyVelocity();
        app.setxMaximum((2 * xv * yv) / -yAcceleration);
        xmax = app.getxMaximum();
        app.setTimeMaximum(xmax / xv);
        txmax = app.getTimeMaximum();
        /*     app.setxPosition(xv * time);
        x = app.getxPosition();
        app.setyPosition(yv * time + ((half * yAcceleration) * Math.pow(time, 2)));
        y = app.getyPosition();
        
        
        app.setAngle(Double.parseDouble(angletf.getText()));
        app.setSpeed(Double.parseDouble(speedtf.getText()));
        app.setXv(xv);
        app.setYv(yv);
        app.setYmax(ymax);
        app.setXmax(xmax);
        app.setTxmax(txmax);
        app.setTymax(tymax);
        xv = app.getXv();
        yv = app.getYv();
        txmax = app.getTxmax();
        xmax = app.getXmax();
        ymax = app.getYmax();
        tymax = app.getTymax(); */

    }

    /**
     *
     */
    @Override
    protected void InitializeContainers() {
        speedtf = new TextField("25");
        speedtf.setPrefSize(50, 15);
        angletf = new TextField();
        angletf.setText("45");
        angletf.setPrefSize(50, 15);
        Text speedtxt = new Text("Speed (m/s):");
        Text angletxt = new Text("Angle (degrees):");
        animationBox.getChildren().add(0, FootballField());
        animationBox.getChildren().add(1, Football());
        animationBox.getChildren().add(2, QuarterBack());
        animationBox.getChildren().add(3, Receiver());
        VBox textFieldBox = new VBox();
        textFieldBox.setSpacing(10);
        textFieldBox.getChildren().addAll(speedtxt, speedtf, angletxt, angletf);
        pause.setDisable(true);
        reset.setDisable(true);
        buttonsBox.getChildren().addAll(textFieldBox);
        buttonsBox.setPadding(new Insets(15, 20, 15, 20));
        buttonsBox.setHgap(200);
        super.InitializeContainers();
        graphBox.getChildren().add(LineChart());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Projectile Motion");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Projectile Motion!\n"
                + "Projectile motion is a form of motion in which an object or particle (called a projectile) is thrown near the earth's surface, and it moves along a curved path under the action of gravity only. The only force of significance that acts on the object is gravity, which acts downward to cause a downward acceleration. Because of the object's inertia, no external horizontal force is needed to maintain the horizontal motion."
                + "Since there is only acceleration in the vertical direction, the velocity in the horizontal direction is constant, being equal to Vo*cosθ . The vertical motion of the projectile is the motion of a particle during its free fall. Here the acceleration is constant, being equal to  g.\n"
                + "ax = 0\n"
                + "ay = -g = -9.8 m/s^2\n"
                + "The projectile is launched with an initial velocity  Vo . which can be expressed as the sum of horizontal and vertical components.\n"
                + "Vox = Vo*cosθ\n"
                + "Voy = Vo*sinθ\n"
                + "The downward vertical component of the velocity increases linearly, because the acceleration due to gravity is constant.\n"
                + "Vy = Vo*sinθ-gt\n"
                + "At any time  t , the projectile's horizontal and vertical displacement are:\n"
                + "X = Vo*cosθ*t\n"
                + "Y = Vo*sinθ*t - 1/2*g*t^2\n"
                + "In projectile motion, the horizontal motion and the vertical motion are independent of each other; that is, neither motion affects the other. This is the principle of compound motion established by Galileo in 1638.");

        alert.showAndWait();

        gTimer = new Timeline();
    }

    protected static void Animate() {
        Calculate();
        seriesF.getData().add(new XYChart.Data(z, z));
        gTimer.getKeyFrames().add(new KeyFrame(Duration.millis(txmax * px10), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if (time <= txmax && x * px10 <= 1210 && y * px10 < 299) {
                    app.setxPosition(xv * time);
                    x = app.getxPosition();
                    app.setyPosition(yv * time + ((half * yAcceleration) * Math.pow(time, 2)));
                    y = app.getyPosition();
                    FootballImage.setTranslateX(x * px10);
                    FootballImage.setTranslateY(-y * px10);
                    ReceiverImage.setTranslateX(x * px10 - receiverLength);

                    seriesF.getData().add(new XYChart.Data(time, y));
                } else {
                    gTimer.stop();
                }
                time += 0.05;
            }
        }));
        gTimer.setCycleCount(Animation.INDEFINITE);
        gTimer.play();

    }

    protected static void Buttons() {

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                if (Double.parseDouble(angletf.getText()) < 10 || Double.parseDouble(speedtf.getText()) < 10 || Double.parseDouble(speedtf.getText()) > 50 || Double.parseDouble(angletf.getText()) > 80) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Invalid Entry");
                    alert.setHeaderText("The angle or the speed you've entred is invalid");
                    alert.setContentText("Please try again by entering a angle between 10 and 80 degrees and a speed between 10 and 50 m/s.");

                    alert.showAndWait();
                } else {
                    start.setDisable(true);
                    pause.setDisable(false);
                    reset.setDisable(false);
                    Animate();

                }
            }
        });

        done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {

                time = z;
                gTimer.stop();
                start.setDisable(false);
                seriesF.getData().clear();
                seriesF.getData().removeAll();
                FootballImage.setTranslateX(z);
                FootballImage.setTranslateY(z);
                ReceiverImage.setTranslateX(z);
                animationBox.getChildren().clear();
                buttonsBox.getChildren().clear();
                graphBox.getChildren().clear();
                Main.MainMenu.root.getChildren().clear();
                Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage, Main.MainMenu.emblem);
                Main.MainMenu.menuBar.setDisable(false);
            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                time = z;
                gTimer.stop();
                start.setDisable(false);
                seriesF.getData().clear();
                seriesF.getData().removeAll();
                FootballImage.setTranslateX(z);
                FootballImage.setTranslateY(z);
                ReceiverImage.setTranslateX(z);
                pause.setDisable(true);
            }
        });

        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                start.setDisable(false);
                gTimer.stop();
            }
        });

        help.setOnAction((ActionEvent Event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Projectile Motion");
            alert.setContentText("To start the animation of Projectile Motion you first have to write two values for botht the speed and the angle at which the ball will be thrown. The value for the speed should be between 10 and 50 and for the angle 10 and 80."
                    + "Click start to play the animation and the graph that will show you the (x vs t) the path of the ball. The graph is showing the position of the ball in y and the time in the x. "
                    + "To pause the animation press pause. To reset the values or the animation and follow the first steps again. When you are done with the topic press done. "
                    + "If you have any further questions or would like to leave a feedback please email us at questions@physicsimulator.com");

            alert.showAndWait();
        });
    }

}
