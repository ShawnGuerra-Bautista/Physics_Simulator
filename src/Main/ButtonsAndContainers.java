package Main;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public abstract class ButtonsAndContainers {

    static Button start = new Button("Start");
    static Button done = new Button("Done");
    static Button pause = new Button("Pause");
    static Button reset = new Button("Reset");
    static Button help = new Button("Help");

    static Pane animationBox = new Pane();
        static Pane graphBox = new Pane();
        static FlowPane buttonsBox = new FlowPane();

    void InitializeContainers() {
        animationBox.setLayoutX(0);
        animationBox.setLayoutY(25);
        buttonsBox.setHgap(15);
        buttonsBox.getChildren().addAll(start, done, pause, reset, help);
        buttonsBox.setLayoutX(0);
        buttonsBox.setLayoutY(475);
        buttonsBox.setPrefSize(610, 245);
        graphBox.setLayoutX(610);
        graphBox.setLayoutY(475);
        graphBox.setPrefSize(610, 245);
       
        MainMenu.root.getChildren().addAll(animationBox,buttonsBox,graphBox);
    }

}
