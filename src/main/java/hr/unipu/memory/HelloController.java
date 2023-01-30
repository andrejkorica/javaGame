package hr.unipu.memory;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class    HelloController implements Initializable {

    ArrayList<Button> buttons = new ArrayList<>();

    MemoryGame memoryGame = new MemoryGame();

    int player = 1;
    int matchCounter = 0;
    int player1Score = 0;
    int player2Score = 0;
    @FXML
    private Label winner;
    @FXML
    private Label score;
    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> hideButtons()));

    private boolean firstButtonClicked = false;

    private int firstButtonIndex;
    private int secondButtonIndex;
    private boolean match;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memoryGame.setupGame();

        buttons.addAll(Arrays.asList(button0, button1, button2, button3, button4,
                button5, button6, button7));
        winner.setText("Player 1 turn");
    }

    @FXML
    void resetClicked(ActionEvent event){
        System.out.println("aa");
        memoryGame.resetBoard();
        for (int i = 0; i<8; i++){
            buttons.get(i).setText("");
        }
        score.setText("SCORE 0 : 0");
        winner.setText("Player 1 turn");
    }
    @FXML
    void buttonClicked(ActionEvent event) {
        if(!firstButtonClicked){
            //If next turn is started before old buttons are hidden
            if(!match){
                hideButtons();
                timeline.stop();
            }
            match = false;
            firstButtonClicked = true;

            //Get clicked button memory letter
            String buttonId = ((Control)event.getSource()).getId();
            firstButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));

            //Change clicked button text
            buttons.get(firstButtonIndex).setText(memoryGame.getOptionAtIndex(firstButtonIndex));

            return;
        }

        //Get clicked button memory letter
        String buttonId = ((Control)event.getSource()).getId();
        secondButtonIndex = Integer.parseInt(buttonId.substring(buttonId.length() - 1));

        //Change clicked button text
        buttons.get(secondButtonIndex).setText(memoryGame.getOptionAtIndex(secondButtonIndex));

        firstButtonClicked = false;

        //Check if the two clicked button match
        if(memoryGame.checkTwoPositions(firstButtonIndex,secondButtonIndex)){
            System.out.println("Match");
            match = true;
            matchCounter++;
            if(player%2==0)++player2Score;
            else ++player1Score;
            score.setText("SCORE " + player1Score + " : " + player2Score);
            if(matchCounter==4){
                if(player1Score>player2Score) winner.setText("PLAYER 1 WINS");
                else if(player1Score==player2Score) winner.setText("YOU BOTH WIN! IT'S A DRAW");
                else winner.setText("PLAYER 2 WINS");
            }
            return;
        }

        timeline.play();
        ++player;
        if(player%2==0)winner.setText("Player 2 turn");
        else winner.setText("Player 1 turn");

    }

    private void hideButtons(){
        buttons.get(firstButtonIndex).setText("");
        buttons.get(secondButtonIndex).setText("");
    }

}
