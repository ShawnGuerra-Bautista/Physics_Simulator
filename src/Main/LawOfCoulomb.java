package Main;

import static Main.ButtonsAndContainers.*;
import PropertyBean.CoulombBean;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LawOfCoulomb extends ButtonsAndContainers implements PhysicsInterface{

    private static ImageView LeftCharge, RightCharge, LeftArrow, RightArrow;
    private static TranslateTransition cLeft, cRight, aLeftT, aRightT;
    private static ParallelTransition parallelTransition;
    private static FadeTransition aLeftF, aRightF;
    private static double forceunit = z;
    private static TextField lcunit, rcunit;
    private static Image NeuI = new Image("Main/Image/Neutral.png");
    private static Image NegI = new Image("Main/Image/Negative.png");
    private static Image PosI = new Image("Main/Image/Positive.png");
    private static Image LeftA = new Image("Main/Image/LeftArrow.png");
    private static Image RightA = new Image("Main/Image/RightArrow.png");
    private static LineChart lineChartC;
    private static XYChart.Series seriesC;
    private static Timeline cTimer;
    private static Button pos, neg, opp1, opp2;
    private static double distance = iniD;
    private static double lc, rc;
    static CoulombBean Capp = new CoulombBean();

    protected static ImageView Space() {
        ImageView Space = new ImageView();
        Image SImage = new Image("Main/Image/Space.jpg");
        Space.setImage(SImage);
        return Space;
    }

    protected static void Positive() {
        LeftCharge.setImage(PosI);
        RightCharge.setImage(PosI);
        LeftCharge.setLayoutX(iniLpos1);
        RightCharge.setLayoutX(iniRpos1);
        LeftArrow.setImage(LeftA);
        RightArrow.setImage(RightA);
        LeftArrow.setLayoutX(iniLpos1);
        RightArrow.setLayoutX(iniRpos1);
    }

    protected static void Negative() {
        LeftCharge.setImage(NegI);
        RightCharge.setImage(NegI);
        LeftCharge.setLayoutX(iniLpos1);
        RightCharge.setLayoutX(iniRpos1);
        LeftArrow.setImage(LeftA);
        RightArrow.setImage(RightA);
        LeftArrow.setLayoutX(iniLpos1);
        RightArrow.setLayoutX(iniRpos1);
    }

    protected static void Opposite1() {
        LeftCharge.setImage(PosI);
        RightCharge.setImage(NegI);
        LeftCharge.setLayoutX(iniLpos2);
        RightCharge.setLayoutX(iniRpos2);
        LeftArrow.setImage(RightA);
        RightArrow.setImage(LeftA);
        LeftArrow.setLayoutX(iniLpos2);
        RightArrow.setLayoutX(iniRpos2);
    }

    protected static void Opposite2() {
        LeftCharge.setImage(NegI);
        RightCharge.setImage(PosI);
        LeftCharge.setLayoutX(iniLpos2);
        RightCharge.setLayoutX(iniRpos2);
        LeftArrow.setImage(RightA);
        RightArrow.setImage(LeftA);
        LeftArrow.setLayoutX(iniLpos2);
        RightArrow.setLayoutX(iniRpos2);
    }

    /**
     *
     */
    @Override
    protected void InitializeContainers() {
        lcunit = new TextField("3");
        lcunit.setPrefSize(45, 15);
        rcunit = new TextField("5");
        rcunit.setPrefSize(45, 15);
        Text lctxt = new Text("Left Charge(C):");
        Text rctxt = new Text("Right Charge(C):");
        pos = new Button("+ +");
        neg = new Button("- -");
        opp1 = new Button("+ -");
        opp2 = new Button("- +");
        pos.setPrefSize(65, 35);
        neg.setPrefSize(65, 35);
        opp1.setPrefSize(65, 35);
        opp2.setPrefSize(65, 35);
        pos.setLayoutX(445);
        neg.setLayoutX(560);
        opp1.setLayoutX(675);
        opp2.setLayoutX(790);
        pos.setLayoutY(375);
        neg.setLayoutY(375);
        opp1.setLayoutY(375);
        opp2.setLayoutY(375);

        LeftCharge = new ImageView();
        RightCharge = new ImageView();
        LeftArrow = new ImageView();
        RightArrow = new ImageView();
        LeftCharge.setImage(NeuI);
        RightCharge.setImage(NeuI);
        LeftCharge.setLayoutX(575);
        LeftCharge.setLayoutY(222);
        RightCharge.setLayoutX(675);
        RightCharge.setLayoutY(222);
        LeftArrow.setImage(NeuI);
        RightArrow.setImage(NeuI);
        LeftArrow.setLayoutX(595);
        LeftArrow.setLayoutY(150);
        RightArrow.setLayoutX(655);
        RightArrow.setLayoutY(150);
        LeftArrow.setOpacity(0);
        RightArrow.setOpacity(0);
        animationBox.getChildren().add(0, Space());
        animationBox.getChildren().add(1, LeftArrow);
        animationBox.getChildren().add(1, RightArrow);
        animationBox.getChildren().add(3, LeftCharge);
        animationBox.getChildren().add(3, RightCharge);
        animationBox.getChildren().add(3, pos);
        animationBox.getChildren().add(3, neg);
        animationBox.getChildren().add(3, opp1);
        animationBox.getChildren().add(3, opp2);
        VBox textFieldBox = new VBox();
        textFieldBox.setSpacing(10);
        textFieldBox.getChildren().addAll(lctxt, lcunit, rctxt, rcunit);
        start.setDisable(true);
        pause.setDisable(true);
        reset.setDisable(true);
        buttonsBox.getChildren().addAll(textFieldBox);
        buttonsBox.setPadding(new Insets(15, 20, 15, 20));
        buttonsBox.setHgap(200);
        super.InitializeContainers();
        graphBox.getChildren().add(LineChart());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Coulomb's Law");
        alert.setHeaderText(null);
        alert.setContentText("You've selected Coulomb's Law!\n"
                + "Coulomb's law is a law of physics that describes force interacting between static electrically charged particles. In its scalar form the law is:\n"
                + " F = K*|q1q2|\\(r^2)\n"
                + "where K is Coulomb's constant (K = 8.99×109 N m^2 C^−2), q1 and q2 are the signed magnitudes of the charges, the scalar r is the distance between the charges. The force of interaction between the charges is attractive if the charges are opposite signed and repulsive if like signed.");

        alert.showAndWait();

        cTimer = new Timeline();
    }

    protected static void Graph() {
        lineChartC.setLegendVisible(false);
        lineChartC.setAnimated(false);

        cTimer.getKeyFrames().add(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                if (distance < 10000) {
                    Capp.setLeftCharge(Double.parseDouble(lcunit.getText()));
                    Capp.setRightCharge(Double.parseDouble(rcunit.getText()));
                    Capp.setTotalForce((Capp.getLeftCharge()*Capp.getRightCharge()*constantK)/(distance*distance));
                    forceunit = Capp.getTotalForce();
                    seriesC.getData().add(new XYChart.Data(distance, forceunit));
                    distance = distance + 500;
                } else {
                    cTimer.stop();
                }
            }
        }));
        cTimer.setCycleCount(Animation.INDEFINITE);
        cTimer.play();
        lc = Double.parseDouble(lcunit.getText()) + 50;
        rc = Double.parseDouble(rcunit.getText()) + 50;
    }

    protected static LineChart LineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Distance(Km)");
        yAxis.setLabel("Force(N)");
        lineChartC = new LineChart<>(xAxis, yAxis);
        seriesC = new XYChart.Series();
        seriesC.setName("Distance vs Force");
        lineChartC.setCreateSymbols(false);
        lineChartC.setPrefSize(640, 255);
        lineChartC.getData().add(seriesC);
        return lineChartC;

    }

    protected static void AnimateRepulsion() {
        LeftArrow.setOpacity(100);
        RightArrow.setOpacity(100);

        LeftCharge.setLayoutY(VerticalChargeHeight - (Double.parseDouble(lcunit.getText())*half));
        RightCharge.setLayoutY(VerticalChargeHeight - (Double.parseDouble(rcunit.getText())*half));
        LeftArrow.setLayoutY(VerticalArrowHeight - (Double.parseDouble(lcunit.getText())*half));
        RightArrow.setLayoutY(VerticalArrowHeight - (Double.parseDouble(rcunit.getText())*half));
        LeftCharge.setFitWidth(lc);
        LeftCharge.setFitHeight(lc);
        LeftArrow.setFitHeight(lc);
        LeftArrow.setFitWidth(lc);
        RightCharge.setFitHeight(rc);
        RightCharge.setFitWidth(rc);
        RightArrow.setFitHeight(rc);
        RightArrow.setFitWidth(rc);
        cLeft = new TranslateTransition(Duration.millis(ttDur / rc), LeftCharge);
        cLeft.setFromX(0);
        cLeft.setToX(-spaceLength);
        cRight = new TranslateTransition(Duration.millis(ttDur / lc), RightCharge);
        cRight.setFromX(0);
        cRight.setToX(spaceLength - rc);
        aLeftT = new TranslateTransition(Duration.millis(ttDur / rc), LeftArrow);
        aLeftT.setFromX(0);
        aLeftT.setToX(-spaceLength);
        aRightT = new TranslateTransition(Duration.millis(ttDur / lc), RightArrow);
        aRightT.setFromX(0);
        aRightT.setToX(spaceLength);
        aLeftF = new FadeTransition(Duration.millis(ftDur / rc), LeftArrow);
        aLeftF.setFromValue(1.0f);
        aLeftF.setToValue(0.0f);
        aRightF = new FadeTransition(Duration.millis(ftDur / lc), RightArrow);
        aRightF.setFromValue(1.0f);
        aRightF.setToValue(0.0f);
        parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(cLeft, cRight, aLeftT, aRightT, aLeftF, aRightF);
        parallelTransition.play();

    }

    protected static void AnimateAttraction() {
        LeftArrow.setOpacity(100);
        RightArrow.setOpacity(100);
        LeftCharge.setLayoutY(VerticalChargeHeight - (Double.parseDouble(lcunit.getText())*half));
        RightCharge.setLayoutY(VerticalChargeHeight - (Double.parseDouble(rcunit.getText())*half));
        LeftArrow.setLayoutY(VerticalArrowHeight - (Double.parseDouble(lcunit.getText())*half));
        RightArrow.setLayoutY(VerticalArrowHeight - (Double.parseDouble(rcunit.getText())*half));
        LeftCharge.setFitWidth(lc);
        LeftCharge.setFitHeight(lc);
        LeftArrow.setFitHeight(lc);
        LeftArrow.setFitWidth(lc);
        RightCharge.setFitHeight(rc);
        RightCharge.setFitWidth(rc);
        RightArrow.setFitHeight(rc);
        RightArrow.setFitWidth(rc);
        cLeft = new TranslateTransition(Duration.millis(ttDur / rc), LeftCharge);
        cLeft.setFromX(0);
        cLeft.setToX(spaceLength + 5 - Double.parseDouble(lcunit.getText()));
        cRight = new TranslateTransition(Duration.millis(ttDur / lc), RightCharge);
        cRight.setFromX(0);
        cRight.setToX(-spaceLength);
        aLeftT = new TranslateTransition(Duration.millis(ttDur / rc), LeftArrow);
        aLeftT.setFromX(0);
        aLeftT.setToX(spaceLength);
        aRightT = new TranslateTransition(Duration.millis(ttDur / lc), RightArrow);
        aRightT.setFromX(0);
        aRightT.setToX(-spaceLength);
        aLeftF = new FadeTransition(Duration.millis(ftDur / rc), LeftArrow);
        aLeftF.setFromValue(1.0f);
        aLeftF.setToValue(0.0f);
        aRightF = new FadeTransition(Duration.millis(ftDur / lc), RightArrow);
        aRightF.setFromValue(1.0f);
        aRightF.setToValue(0.0f);
        parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(cLeft, cRight, aLeftT, aRightT, aLeftF, aRightF);
        parallelTransition.play();

    }

    protected static void Buttons() {

        pos.setOnAction((ActionEvent Event) -> {
            start.setDisable(false);
            pos.setDisable(true);
            neg.setDisable(false);
            opp1.setDisable(false);
            opp2.setDisable(false);
            Positive();
        });
        neg.setOnAction((ActionEvent Event) -> {
            start.setDisable(false);
            pos.setDisable(false);
            neg.setDisable(true);
            opp1.setDisable(false);
            opp2.setDisable(false);
            Negative();
        });
        opp1.setOnAction((ActionEvent Event) -> {
            start.setDisable(false);
            pos.setDisable(false);
            neg.setDisable(false);
            opp1.setDisable(true);
            opp2.setDisable(false);
            Opposite1();
        });

        opp2.setOnAction((ActionEvent Event) -> {
            start.setDisable(false);
            pos.setDisable(false);
            neg.setDisable(false);
            opp1.setDisable(false);
            opp2.setDisable(true);
            Opposite2();
        });

        start.setOnAction((ActionEvent Event) -> {
            start.setDisable(true);
            reset.setDisable(false);
            if (Double.parseDouble(lcunit.getText()) < 1 || Double.parseDouble(lcunit.getText()) > 150 || Double.parseDouble(rcunit.getText()) < 1 || Double.parseDouble(rcunit.getText()) > 150) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Entry");
                alert.setHeaderText("The charge value you've entred is invalid");
                alert.setContentText("Please try again by entering a charge bigger or equal to 1 and smaller than 150 Coulombs");

                alert.showAndWait();

            }
            if (pause.isDisabled() == false) {
                Graph();
                parallelTransition.play();
            } else {
                Graph();
                if (pos.isDisabled()) {
                    AnimateRepulsion();
                }
                if (neg.isDisabled()) {
                    AnimateRepulsion();
                }
                if (opp1.isDisabled()) {
                    AnimateAttraction();
                }
                if (opp2.isDisabled()) {
                    AnimateAttraction();
                }

                pos.setDisable(true);
                neg.setDisable(true);
                opp1.setDisable(true);
                opp2.setDisable(true);

            }
            pause.setDisable(false);
        });

        done.setOnAction((ActionEvent Event) -> {
            cTimer.stop();
            start.setDisable(false);
            pause.setDisable(true);
            reset.setDisable(true);
            animationBox.getChildren().clear();
            buttonsBox.getChildren().clear();
            graphBox.getChildren().clear();
            Main.MainMenu.root.getChildren().clear();
            Main.MainMenu.root.getChildren().addAll(Main.MainMenu.menuBar, Main.MainMenu.WallImage, Main.MainMenu.emblem);
            Main.MainMenu.menuBar.setDisable(false);
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent Event) {
                distance = iniD;
                cTimer.stop();
                pos.setDisable(false);
                neg.setDisable(false);
                opp1.setDisable(false);
                opp2.setDisable(false);
                start.setDisable(true);
                pause.setDisable(true);
                reset.setDisable(true);
                LeftCharge.setFitWidth(50);
                LeftCharge.setFitHeight(50);
                LeftCharge.setLayoutX(575);
                LeftCharge.setLayoutY(222);
                RightCharge.setFitHeight(50);
                RightCharge.setFitWidth(50);
                RightCharge.setLayoutX(675);
                RightCharge.setLayoutY(222);
                LeftCharge.setImage(NeuI);
                RightCharge.setImage(NeuI);
                parallelTransition.playFromStart();
                parallelTransition.jumpTo(Duration.ZERO);
                parallelTransition.stop();
                seriesC.getData().clear();
                LeftArrow.setOpacity(0);
                RightArrow.setOpacity(0);
            }
        });

        pause.setOnAction((ActionEvent Event) -> {
            start.setDisable(false);
            parallelTransition.pause();
            cTimer.pause();
        });

        help.setOnAction((ActionEvent Event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Coulomb's Law");
            alert.setContentText("To start the animation of Coulomb's Law you first have to pick one of the four situations. Then you enter a value between 1 and 150 in both of the boxes for the charges. "
                    + "Click start to play the animation and the graph that will show you the interaction between the two charges. The graph is showing the force between the two charges when the distance changes. "
                    + "To pause the animation press pause. To reset the values or the animation or the situation press reset and follow the first steps again. When you are done with the topic press done. "
                    + "If you have any further questions or would like to leave a feedback please email us at questions@physicsimulator.com");

            alert.showAndWait();
        });
    }

}
