import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        String[] values = new String[] { "Error", "Rock", "Paper", "Scissors"};
        boolean isPlaying = true;
        Scanner scanner = new Scanner(System.in);

        while (isPlaying) {
            int userWon = 0;
            int userLost = 0;
            int userDraw = 0;

            System.out.println("Let us play Rock, Paper Scissors!");
            System.out.print("How many rounds do you want to play? (Minimum: 1, Maximum: 10) ");

            int rounds = scanner.nextInt();

            if (rounds < 1 || rounds > 10) {
                System.out.println("Sorry, the number of rounds are invalid");
                return;
            }

            while (rounds > 0) {
                System.out.print("Input your choice (Rock: 1, Paper: 2, Scissors: 3) ");
                int userInput = scanner.nextInt();
                int computerInput = (int) Math.ceil(Math.random() * 3);
                System.out.println("You have chosen " + values[userInput] + " and the computer chose " + values[computerInput]);

                if (userInput == computerInput) {
                    userDraw++;
                    System.out.println("This round is a draw!");
                } else if (userInput == (computerInput % 3) + 1) {
                    userWon++;
                    System.out.println("You won this round!");
                } else {
                    userLost++;
                    System.out.println("You lost this round!");
                }

                rounds--;
            }

            System.out.printf("You have won %d rounds, lost %d rounds, and had %d ties.\n", userWon, userLost, userDraw);
            if (userWon > userLost) {
                System.out.println("You have won the game!");
            } else if (userWon < userLost) {
                System.out.println("You have lost the game!");
            } else {
                System.out.println("The game is a draw!");
            }

            System.out.print("Would you like to play again? (Quit: 0, Play again: 1) ");
            if (scanner.nextInt() == 0) isPlaying = false;
        }

    }

}
