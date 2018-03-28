 
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbouring mines...) 
 * at the dot position (x,y)
 *
 * @author Yehuda Friedman, YouYang Xu
 */

public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE
    private int x;
    private int y;
    private int neighbooringMines;
    private boolean covered, mined, wasClicked;
    private boolean flagged;


    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){

    // ADD YOU CODE HERE
        this.x = x;
        this.y = y;
        this.flagged = false;
        covered = true;
        wasClicked = false;
        mined = false;
        neighbooringMines = 0;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){

    // ADD YOU CODE HERE
        return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

    // ADD YOU CODE HERE
        return y;
    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

    // ADD YOU CODE HERE
        this.mined = true;
    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

    // ADD YOU CODE HERE
        return mined;
    }


    /**
     * Setter for covered
     */
    public void uncover() {

    // ADD YOU CODE HERE
        if (!this.getFlagged()){
            covered = false;
        }
    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
        return covered;
    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE
        wasClicked = true;
    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

    // ADD YOU CODE HERE
        return wasClicked;
    }


    /**
     * Setter for neighbouringMines
     *
     * @param neighbouringMines
     *          number of neighbouring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE
        this.neighbooringMines = neighbooringMines;
    }

    /**
     * Get for neighbouringMines
     *
     * @return neighbouringMines
     */
    public int getNeighbooringMines() {

    // ADD YOU CODE HERE
        return neighbooringMines;
    }

    /**
     * Returns true if current dot is flagged, false otherwise
     *
     * @return flagged status of the dot
     */
    public boolean getFlagged(){
        return flagged;
    }

    /**
     * Sets of status of the flagged to true for this dot
     */
    public void setFlagged(){
        flagged = true;
    }

    /**
     * Sets of status of the flagged to false for this dot
     */
    public void removeFlagged(){
        flagged = false;
    }

 }
