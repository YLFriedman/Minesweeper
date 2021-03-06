

/**
 * The class <b>Mineseeper</b> launches the game
 *
 * @author Yehuda Friedman, YouYang Xu
 */
public class Minesweeper {

    private static int DEFAULT_WIDTH = 20;

    private static int DEFAULT_height = 12;

    private static int DEFAULT_MINES = 36;

   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If three parameters width, height,
     * number of mines are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (minimum 10 for width,
     * 5 for height and 1 for number of mines).
     * Additionally, the maximum number of mines is capped at
     * width*height -1
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_height;
        int numberOfMines = DEFAULT_MINES;
 
        if (args.length == 3) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<10){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height<5){
                    System.out.println("Invalid argument, using default...");
                    height = DEFAULT_height;
                }
                numberOfMines = Integer.parseInt(args[2]);
                if(numberOfMines<1){
                    System.out.println("Invalid argument, using default...");
                    numberOfMines = DEFAULT_MINES;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_height;
                numberOfMines = DEFAULT_MINES;
            }
        }
        if(numberOfMines >= width*height) {
            System.out.println("Too many mines: " + numberOfMines 
                + " mines on " + (width*height) + " spots. Using " 
                + (width*height - 1) + " instead. Good luck!");
            numberOfMines = (width*height - 1);
        }
   
        GameController game = new GameController(width, height,numberOfMines);
    }


}
