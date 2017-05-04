package Main;

import java.io.File;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application implements PhysicsInterface {

    protected static MenuBar menuBar = new MenuBar();
    protected static Group root = new Group();
    protected static ImageView WallImage, emblem;
    protected static AudioClip mediaPlayer;

    @Override
    public void start(Stage primaryStage) {

        Menu mNYA = new Menu("203-NYA Mechanics");
        Menu mNYB = new Menu("203-NYB Waves, Optics & Modern Physics");
        Menu mNYC = new Menu("203-NYC Electricity & Magnetism");
        Menu option = new Menu("Option");

        MenuItem animationNYA1 = new MenuItem("Newton's Second Law Animation");
        MenuItem animationNYA2 = new MenuItem("Projectile Motion Animation");
        MenuItem inConstruction1 = new MenuItem(underConstruction);

        MenuItem animationNYB1 = new MenuItem("Resonance Animation");
        MenuItem animationNYB2 = new MenuItem("Pendulum Animation");
        MenuItem inConstruction2 = new MenuItem(underConstruction);

        MenuItem animationNYC1 = new MenuItem("Ohm's Law Animation");
        MenuItem animationNYC2 = new MenuItem("Coulombs Law Animation");
        MenuItem inConstruction3 = new MenuItem(underConstruction);
        MenuItem exit = new MenuItem("Exit");
        MenuItem stopMusic = new MenuItem("Mute");
        MenuItem playMusic = new MenuItem("Enable Sound");

        mNYA.getItems().addAll(animationNYA1, animationNYA2, inConstruction1);
        mNYB.getItems().addAll(animationNYB1, animationNYB2, inConstruction2);
        mNYC.getItems().addAll(animationNYC1, animationNYC2, inConstruction3);
        option.getItems().addAll(stopMusic, playMusic, exit);

        menuBar.getMenus().addAll(option, mNYA, mNYB, mNYC);
        menuBar.setPrefSize(1290, 25);
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);

        WallImage = new ImageView();
        Image GImage = new Image("Main/Image/wallpaper.jpg");
        WallImage.setImage(GImage);
        WallImage.setLayoutX(0);
        WallImage.setLayoutY(25);
        emblem = new ImageView();
        Image EImage = new Image("Main/Image/emblem.png");
        emblem.setImage(EImage);
        emblem.setLayoutX(885);
        emblem.setLayoutY(150);
        RotateTransition rotateTransition1 = new RotateTransition(Duration.millis(2500), emblem);
        rotateTransition1.setByAngle(720f);
        rotateTransition1.setCycleCount(INDEFINITE);
        rotateTransition1.setAutoReverse(true);

        String musicFile = "music.mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mp = new MediaPlayer(sound);
        mp.play();

        root.getChildren().addAll(menuBar,WallImage,emblem);
        rotateTransition1.play();
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Physics Simulator 1.121.52");
        primaryStage.getIcons().add(new Image("Main/Image/emblem.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        animationNYA1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                SecondLawOfNewton fmaClass = new SecondLawOfNewton();
                fmaClass.InitializeContainers();
                fmaClass.Buttons();
            }
        });
        animationNYA2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                ProjectileMotion proClass = new ProjectileMotion();
                proClass.InitializeContainers();
                proClass.Buttons();

            }
        });
        inConstruction1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(underConstruction);
                alert.setHeaderText(constructionMessage);
                alert.setContentText(pressOKButton);
                alert.showAndWait();

            }
        });

        animationNYB1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                Resonance resonanceClass = new Resonance();
                resonanceClass.InitializeContainers();
                resonanceClass.Buttons();
            }
        });
        animationNYB2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                Pendulum pendClass = new Pendulum();
                pendClass.InitializeContainers();
                pendClass.Buttons();
            }
        });
        inConstruction2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(underConstruction);
                alert.setHeaderText(constructionMessage);
                alert.setContentText(pressOKButton);

                alert.showAndWait();
            }
        });

        animationNYC1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                LawOfOhm ohmClass = new LawOfOhm();
                ohmClass.InitializeContainers();
                ohmClass.Buttons();
            }
        });
        animationNYC2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                menuBar.setDisable(true);
                LawOfCoulomb couClass = new LawOfCoulomb();
                couClass.InitializeContainers();
                couClass.Buttons();
            }
        });

        inConstruction3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(underConstruction);
                alert.setHeaderText(constructionMessage);
                alert.setContentText(pressOKButton);

                alert.showAndWait();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                System.exit(zero);
            }
        });
        
        stopMusic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                mp.stop();
            }
        });
        
        playMusic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                mp.play();
            }
        });
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}
