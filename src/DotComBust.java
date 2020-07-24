import java.util.ArrayList;

public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    public static void main (String[] args){
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }

    private void setUpGame(){
        // Make some dot coms and give them locations
        DotCom one = new DotCom();
        one.setName("pets.com");

        DotCom two = new DotCom();
        two.setName("etoys.com");

        DotCom tree = new DotCom();
        tree.setName("go2.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(tree);

        // Print brief instructions for user
        System.out.println("Your goal is to sink three dot coms (ships) on the 7 x 7 grid (49 positions)");
        System.out.println("You have to put options between 0123456 - ABCDEFG like 2B or 06");
        System.out.println("These are the 'ships' to sink: pets.com, etoys.com, go2.com");
        System.out.println("Try to sink them all in the fewst number of guesses");

        // Locations
        for (DotCom dotComToSet : dotComsList) {

            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }


    private void startPlaying(){
        while (!dotComsList.isEmpty()){
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }


    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "Miss";

        for (DotCom dotComToTest : dotComsList){
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Hit")){
                break;
            }

            if(result.equals("Kill")){
                dotComsList.remove(dotComToTest);
                break;
            }
        }

        System.out.println(result);
    }


    private void finishGame(){
        System.out.println("All Dot Coms are dead! Your stock is now worthless");

        if(numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses +" guesses");
            System.out.println("You got out before your options sank");
        } else {
            System.out.println("Took you long enought " + numOfGuesses + " guesses");
            System.out.println("Fish are dancing with your options");
        }
    }
}
