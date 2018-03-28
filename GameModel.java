import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
    private int width;
    private int heigth;
    private int numberOfMines;
    private int numberOfSteps;
    private DotInfo[][] grid;

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE
        this.width = width;
        this.heigth = heigth;
        this.numberOfMines = numberOfMines;
        this.numberOfSteps = 0;
        grid = new DotInfo[width][heigth];

        Random generator = new Random();

        //init each coordinate with a DotInfo object
        for (int i = 0; i < width; i++){
            for (int j = 0; j < heigth; j++){
                grid[i][j] = new DotInfo(i, j);
            }
        }

        //randomly populates the grid with numberOfMines mines in unique locations
        for (int i = 0; i < numberOfMines; i++){
            boolean set = false;
            while (!set){
                int location = generator.nextInt(heigth*width);
                int tempX = location/heigth;
                int tempY = location - heigth*tempX;
                if (!grid[tempX][tempY].isMined()){
                    set = true;
                    grid[tempX][tempY].setMined();
                }
            }
        }

        //adds the number of neighbooring mines in each DotInfo if it is not a mine itself
        
        for (int i = 0; i < width; i++){
            for (int j = 0; j < heigth; j++){
                int tempMines = 0;
                if (i > 0 && j > 0 && grid[i-1][j-1].isMined()){
                    tempMines ++;
                }
                if (j > 0 && grid[i][j-1].isMined()){
                    tempMines ++;
                }
                if (j > 0 && i < width - 1 && grid[i+1][j-1].isMined()){
                    tempMines ++;
                }
                if (i > 0 && j < heigth -1 && grid[i-1][j+1].isMined()){
                    tempMines++;
                }
                if (j < heigth -1 && grid[i][j+1].isMined()){
                    tempMines++;
                }
                if (i < width -1 && j < heigth -1 && grid[i+1][j+1].isMined()){
                    tempMines++;
                }
                if (i > 0 && grid[i-1][j].isMined()){
                    tempMines++;
                }
                if (i < width -1 && grid[i+1][j].isMined()){
                    tempMines++;
                }
                grid[i][j].setNeighbooringMines(tempMines);
            }
        }
        
    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){
    // ADD YOU CODE HERE
        GameModel reset = new GameModel(width, heigth, numberOfMines);
        for (int i = 0; i < width; i++){
            for (int j = 0; j < heigth; j++){
                this.grid[i][j] = reset.grid[i][j];
            }
        }

    }

    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE
        return heigth;

    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE
        return width;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE
        return grid[i][j].isMined();
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
        return grid[i][j].hasBeenClicked();
    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE
        return grid[i][j].getNeighbooringMines() == 0;
    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
        return grid[i][j].isCovered();
    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
        return grid[i][j].getNeighbooringMines();
    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
        
        grid[i][j].uncover();
    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
        grid[i][j].click();
    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
        for(int i = 0; i < width; i++){
            for(int j = 0; j < heigth; j++){
                if (grid[i][j].isCovered()){
                    grid[i][j].uncover();
                }
            }
        }
    }


    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
        return numberOfSteps;
    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
        return grid[i][j];
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the player selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE
        numberOfSteps ++;
    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
         for(int i = 0; i < width; i++){
            for(int j = 0; j < heigth; j++){
                if (grid[i][j].isCovered() && !grid[i][j].isMined()){
                    return false;
                }
            }
        }
        return true;
    }

   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
        String repr = new String("");
        for(int i = 0; i < heigth; i++){
            repr += "[";
            for(int j = 0; j < width; j++){
                if(grid[j][i].isMined()){
                    repr += " X ";
                }
                else{
                    repr += " " + grid[j][i].getNeighbooringMines() + " ";
                }
            }
            repr += "]\n";
        }
        return repr;
    }
}
