import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.*;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Yehuda Friedman, YouYang Xu
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE

    private int width;
    private int heigth;
    private int numberOfMines;
    private GameModel model;
    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    public GameController(int width, int heigth, int numberOfMines) {

    // ADD YOU CODE HERE
        this.width = width;
        this.heigth = heigth;
        this.numberOfMines = numberOfMines;
        this.model = new GameModel(this.width, this.heigth, this.numberOfMines);
        this.view = new GameView(model, this);


    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        
    // ADD YOU CODE HERE
        if (e.getActionCommand() == "Reset"){
            this.reset();
        }
        else if (e.getActionCommand() == "Quit"){
            view.setVisible(false);
            view.dispose();
            System.exit(0);
        }
        else{
            //If the timer hasnt been turned on, turn it on
            if(!view.timerWasActivated()){
                view.startTimer();
            }
            DotButton clicked = (DotButton)e.getSource();
            int column = clicked.getColumn();
            int row = clicked.getRow();
            play(column, row);
        }

    }


    /**
     * resets the game
     */
    private void reset(){

        GameController reset = new GameController(width, heigth,numberOfMines);
        this.view.dispose();
        this.model = reset.model;
        this.view = reset.view;


    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth){

        if(!model.get(width, heigth).hasBeenClicked() && !model.get(width, heigth).getFlagged()){   //only initiate click if covered and not flagged

            model.get(width,heigth).click();
            if(model.get(width, heigth).isCovered()){
                model.step();
            }
            model.get(width, heigth).uncover();
            
            if(model.get(width, heigth).isMined()){
                model.uncoverAll();
                //Pause the timer
                view.stopTimer();
                view.update();
                String lossMessage = new String("Ouch! you lost in " + model.getNumberOfSteps() + " turns! \n Would you like to try again?");
                int ans = JOptionPane.showOptionDialog(view, lossMessage, "BOOM!!", 0, 1, null, null, null);
                if(ans == 0){
                    this.reset();
                }
                else{
                    ActionEvent quit = new ActionEvent(view, 1, "Quit");
                    this.actionPerformed(quit);
                }
            }

            else{
                if(model.get(width, heigth).getNeighbooringMines() == 0){
                    clearZone(model.get(width, heigth));
                }
                view.update();
            }

            if(!model.get(width, heigth).isMined() && model.isFinished()){
                model.uncoverAll();
                //Pause the timer
                view.stopTimer();
                view.update();
                String winMessage = new String("Board Clear! You won in " + model.getNumberOfSteps() + " turns! \n Would you like to play again?");
                int ans = JOptionPane.showOptionDialog(view, winMessage, "YAY!", 0, 1 , null, null, null);
                if(ans == 0){
                    this.reset();
                }
                else{
                    ActionEvent quit = new ActionEvent(view, 1, "Quit");
                    this.actionPerformed(quit);
                }
            }

    
        }
      
    }
    

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
        if (!initialDot.getFlagged()){
            GenericArrayStack<DotInfo> gridStack = new GenericArrayStack<DotInfo>(240);
            gridStack.push(initialDot);
            
            while(!gridStack.isEmpty()){
                DotInfo tempDot = gridStack.pop();
                int x = tempDot.getX();
                int y = tempDot.getY();
                if(x > 0 && model.get(x-1 , y).isCovered()){
                    model.get(x-1, y).uncover();
                    if(model.get(x-1, y).getNeighbooringMines() == 0){
                        gridStack.push(model.get(x-1,y));
                    }
                }
                if(x > 0 && y < heigth - 1 && model.get(x - 1, y + 1).isCovered()){
                    model.get(x-1, y+1).uncover();
                    if(model.get(x-1, y+1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x -1 , y + 1));
                    }
                }
                if(x > 0 && y > 0 && model.get(x - 1, y - 1).isCovered()){
                    model.get(x-1, y-1).uncover();
                    if(model.get(x-1, y-1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x -1 , y - 1));
                    }
                }
                if(x < width -1 && y > 0 && model.get(x + 1, y - 1).isCovered()){
                    model.get(x+1, y-1).uncover();
                    if(model.get(x+1, y-1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x + 1 , y - 1));
                    }
                }
                if(x < width - 1  && model.get(x + 1, y).isCovered()){
                    model.get(x+1, y).uncover();
                    if(model.get(x+1, y).getNeighbooringMines()==0){
                        gridStack.push(model.get(x +1 , y));
                    }
                }
                if(x < width -1 && y < heigth - 1 && model.get(x + 1, y + 1).isCovered()){
                    model.get(x+1, y+1).uncover();
                    if(model.get(x+1, y+1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x +1 , y + 1));
                    }
                }
                if(y > 0 && model.get(x, y - 1).isCovered()){
                    model.get(x, y-1).uncover();
                    if(model.get(x, y-1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x, y-1));
                    }
                }
                if(y < heigth - 1 && model.get(x, y + 1).isCovered()){
                    model.get(x, y+1).uncover();
                    if(model.get(x, y+1).getNeighbooringMines()==0){
                        gridStack.push(model.get(x, y+1));
                    }
                }
            }
        }
    }
}
