import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Yehuda Friedman, YouYang Xu
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel model;
    private GameController controller;
    private JPanel panel;
    private DotButton[][] buttonArray;
    private int xcord = 10;
    private int ycord = 10;
    private JButton reset;
    private JButton quit;
    private int numButtons = 0;
    private JLabel stepLabel;
    private TimePanel northPanel;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
        model = gameModel;
        controller = gameController;
        numButtons = model.getHeigth() * model.getWidth();

        panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting dimensions of the window
        panel.setPreferredSize(new Dimension(28*model.getWidth(), 28*model.getHeigth()));
        this.setSize(28*model.getWidth()+70, 28*model.getHeigth()+120);
        this.add(panel);
        this.setTitle("MineSweeper");

        buttonArray = new DotButton[model.getWidth()][model.getHeigth()];

        //set minefield matrix
        panel.setLayout(null);

        for (int i = 0; i < model.getWidth(); i++) {
            for (int j = 0; j < model.getHeigth(); j++) {
                buttonArray[i][j] = new DotButton(i,j,11);
                panel.add(buttonArray[i][j]);
                buttonArray[i][j].setBounds(xcord, ycord, 28, 28);
                buttonArray[i][j].addActionListener(controller);

                //RIGHT CLICK BS BEYOND THIS POINT
                final int xpls = i;
                final int ypls = j;

                buttonArray[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == 3 && !model.get(xpls,ypls).getFlagged()) { // if right click
                            model.get(xpls,ypls).setFlagged();
                            buttonArray[xpls][ypls].setIconNumber(12);
                            update();
                        } 
                        else if (e.getButton() == 3 && model.get(xpls,ypls).getFlagged()) {
                            model.get(xpls,ypls).removeFlagged();
                            buttonArray[xpls][ypls].setIconNumber(11);
                            update();
                        }
                    }
                });
                //END OF RIGHT CLICK BS

                ycord += 28;
            }
            ycord = 10;
            xcord += 28;
        }

        //south panel to store the reset and quit buttons
        JPanel southPanel = new JPanel();
        //add the panel to the frame
        this.add(southPanel, BorderLayout.SOUTH);
        stepLabel = new JLabel("Number of Turns: " + model.getNumberOfSteps());
        southPanel.add(stepLabel, BorderLayout.WEST);

        //Start button
        reset = new JButton("Reset");
        reset.setActionCommand("Reset");
        reset.addActionListener(controller);
        southPanel.add(reset);
        //quit button
        quit = new JButton("Quit");
        quit.setActionCommand("Quit");
        quit.addActionListener(controller);
        southPanel.add(quit);

        //north panel to store timer and mine counter
        northPanel = new TimePanel();
        this.add(northPanel, BorderLayout.NORTH);

        this.setVisible(true);

    }


    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){

        stepLabel.setText("Number of Turns: " + model.getNumberOfSteps());
        for(int i = 0; i < model.getWidth(); i++){
            for(int j = 0; j < model.getHeigth(); j++){
                if(!model.get(i,j).isCovered()){
                    if(model.get(i,j).isMined() && model.get(i,j).hasBeenClicked()){
                        buttonArray[i][j].setIconNumber(10);
                    }
                    else if(model.get(i,j).isMined()){
                        buttonArray[i][j].setIconNumber(9);
                    }
                else{
                    buttonArray[i][j].setIconNumber(model.get(i,j).getNeighbooringMines());
                    }
                }
            }
        }
        
    

    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
        return buttonArray[i][j].getIconNumber();
    }
    /**
    * Method that allows the controller to start the timer contained in northPanel
    */
    public void startTimer(){
        northPanel.activate();
    }

    /**
    * Method that pauses the timer (i.e on game loss or victory)
    */

    public void stopTimer(){
        northPanel.pause();
    }

    /**
    * returns the value of the boolean hasBeenActivated, true if the timer has been turned on,
    * false otherwise
    *
    *@return the boolean hasBeenActivated
    */

    public boolean timerWasActivated(){
        return northPanel.hasBeenActivated();
    }


}
