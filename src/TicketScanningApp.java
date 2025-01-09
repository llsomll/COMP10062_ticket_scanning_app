import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * Ticket Scanning App is an application designed to manage tickets for an event. Users can input ticket codes and the app validates them, indicating whether the ticket is valid, already redeemed, or invalid. It also provides information about the type of show associated with each ticket code.
 *
 * @author Dasom Kim
 * @version v1.00
 *
 */
public class TicketScanningApp extends Application {

    // TODO: Instance Variables for View Components and Model

    private Table t;
    private Label appTitle;
    private TextField inputBox;
    private Button redeemButton;
    private Label ticketValidation;
    private Label typeOfShow;
    private ImageView imageView;


    // TODO: Private Event Handlers and Helper Methods

    /**
     *
     * Handles the action event triggered by the "Redeem" button by calling the redeemTicket method.
     *
     * @param e The ActionEvent object representing the event triggered by the "Redeem" button.
     *
     */
    private void doRedeem(ActionEvent e) {
        String target = inputBox.getText();
        redeemTicket(target);
    }




    /**
     * This method performs the actual redemption process.
     * Redeems a ticket based on the provided target code.
     * If the target code is "reset", resets all ticket codes and displays a message.
     * Updates the GUI components accordingly based on the provided code.
     *
     * @param target The ticket code to redeem or the secret code(reset) to reset all codes.
     */
    private void redeemTicket(String target) {
        String[] ticketInfo = t.getMatches(target);
        int row = t.lookup(target);

        if (target.equalsIgnoreCase("reset")) {
            ticketValidation.setText("All codes have been reset");
            typeOfShow.setText("");
            ticketValidation.setStyle("-fx-text-fill: #eb5656;");
            for (int r = 0; r < t.getNumRows(); r++) {
                t.change(r, 2, "N");
            }
            inputBox.requestFocus();
            inputBox.setText("");
            return;
        }

        if (row != -1) {
            if (ticketInfo[1].equals("Y")) {
                if (ticketInfo[2].equals("Y")) {
                    ticketValidation.setText(target + " is a duplicate");
                    playSound("invalid.wav");
                    typeOfShow.setText("");
                    ticketValidation.setStyle("-fx-text-fill: #eb5656;");
                } else {
                    ticketValidation.setText(target + " - Valid");
                    playSound("valid.wav");
                    t.change(target, 2, "Y");
                    typeOfShow.setText(ticketInfo[3]);
                    typeOfShow.setStyle("-fx-text-fill: #029386;");
                    ticketValidation.setStyle("-fx-text-fill: #029386;");
                }
            } else {
                ticketValidation.setText(target + " not purchased yet");
                playSound("invalid.wav");
                typeOfShow.setText("");
                ticketValidation.setStyle("-fx-text-fill: #eb5656;");
            }
        } else {
            ticketValidation.setText(target + " is invalid");
            playSound("invalid.wav");
            typeOfShow.setText("");
            ticketValidation.setStyle("-fx-text-fill: #eb5656;");
        }

        inputBox.requestFocus();
        inputBox.setText("");
    }




    /**
     * Plays a sound file based on the provided file name.
     *
     * @param soundFile The name of the sound file to play.
     */
    private void playSound(String soundFile) {
        MediaPlayer player = new MediaPlayer(new Media("file:///"+System.getProperty("user.dir").replace('\\','/').replaceAll(" ", "%20")+"/" + "src/" + soundFile));
        player.play();
    }




    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 225); // set the size here
        scene.getRoot().setStyle("-fx-background-color: #fef7d9;");
        stage.setTitle("Ticket Scanning App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        scene.getStylesheets().add("myStyles.css");   // Add custom stylesheets to the scene

        // 1. Create the model
        t = new Table("codes.txt");
        imageView = new ImageView(new Image("file:src/tickets.png"));

        // 2. Create the GUI components
        appTitle = new Label("Ticket Scanning App");
        inputBox = new TextField("");
        redeemButton = new Button("Redeem");
        ticketValidation = new Label("");
        typeOfShow = new Label("");

        // 3. Add components to the root
        root.getChildren().addAll(appTitle,inputBox,redeemButton,ticketValidation,typeOfShow,imageView);

        // 4. Configure the components (colors, fonts, size, location)
        appTitle.relocate(18,22);
        appTitle.setId("title");

        inputBox.relocate(50, 85);
        inputBox.setPrefHeight(50);
        inputBox.setPrefWidth(130);

        redeemButton.relocate(220, 85);

        ticketValidation.relocate(18, 150);
        ticketValidation.setFont(new Font("System", 30));

        typeOfShow.relocate(18,190);
        typeOfShow.setFont(new Font("System", 20));

        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.relocate(300, 6);


        // Set up event handler for keyboard input in inputBox
        inputBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String target = inputBox.getText();
                redeemTicket(target);
            }
        });

        // 5. Add Event Handlers and do final setup
        redeemButton.setOnAction(this::doRedeem);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}