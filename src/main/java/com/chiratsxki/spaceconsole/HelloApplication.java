package com.chiratsxki.spaceconsole;

import com.chiratsxki.spaceconsole.model.Horizons;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HelloApplication extends Application {

    private TextArea consoleTextArea;

    @Override
    public void start(Stage primaryStage) {
        consoleTextArea = createConsoleTextArea();

        animateTyping("/sc/> NASA Database Connection --------|\n" +
                "/sc/> Establishing a connection between radars and satellites --------|\n" +
                "/sc/> Data collection --------|", () -> {

            consoleTextArea.appendText("\n/sc/> Type [help] to expand commands\n/sc/> ");
        });

        consoleTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                processCommand();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(consoleTextArea);

        Scene scene = new Scene(root, 650, 400);

        primaryStage.setTitle("Space Console");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private TextArea createConsoleTextArea() {
        TextArea textArea = new TextArea();
        textArea.setStyle("-fx-control-inner-background: black; -fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 14;");
        textArea.setEditable(true);
        Glow glowEffect = new Glow();
        glowEffect.setLevel(0.8);
        textArea.setEffect(glowEffect);
        return textArea;
    }

    private void animateTyping(String textToType, Runnable onFinished) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        for (int i = 0; i < textToType.length(); i++) {
            int finalI = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * 0.05), event -> {
                consoleTextArea.appendText(Character.toString(textToType.charAt(finalI)));
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();

        timeline.setOnFinished(event -> {
            if (onFinished != null) {
                onFinished.run();
            }
            consoleTextArea.setEditable(true);
        });
    }

    private void processCommand() {
        String fullText = consoleTextArea.getText().trim();
        String lastCommand = fullText.substring(fullText.lastIndexOf("/sc/>") + 5).trim();
        String result;

        if (lastCommand.equals("horizon")) {
            result = Horizons.processHorizonCommand();
        } else {
            result = CommandProcessor.processCommand(lastCommand);
        }

        consoleTextArea.appendText("/sc/> " + result + "\n/sc/> ");
        consoleTextArea.positionCaret(consoleTextArea.getText().length());
    }

    public static void main(String[] args) {
        launch();
    }
}