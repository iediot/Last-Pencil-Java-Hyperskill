package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        int round = 1, pencilsTaken, i, pencils;
        String name, winner = "", playerName, botName;
        boolean botHasFirstMove;
        while (true) {
            String numberOfPencils = scanner.nextLine();
            try {
                pencils = Integer.parseInt(numberOfPencils);
                if (pencils <= 0)
                    System.out.println("The number of pencils should be positive");
                else
                    break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        System.out.println("Who will be the first (John, Jack):");
        while (true) {
            name = scanner.nextLine();
            if (name.equals("John")) {
                playerName = "John";
                botName = "Jack";
                botHasFirstMove = false;
                break;
            }
            else if (name.equals("Jack")) {
                playerName = "Jack";
                botName = "John";
                botHasFirstMove = true;
                break;
            }
            else
                System.out.println("Choose between 'John' and 'Jack'");
        }
        while (pencils > 0) {
            for (i = 0; i < pencils; i++)
                System.out.print("|");
            System.out.println();

            if (round % 2 == 1) {
                System.out.println(playerName + "'s turn!");
                winner = botName;
            } else {
                System.out.println(botName + "'s turn!");
                winner = playerName;
            }

            while (true) {
                if (!botHasFirstMove && round % 2 == 1
                        || botHasFirstMove && round % 2 == 0) {
                    String takeInput = scanner.nextLine();
                    try {
                        pencilsTaken = Integer.parseInt(takeInput);
                        if (pencilsTaken < 1 || pencilsTaken > 3) {
                            System.out.println("Possible values: '1', '2', '3'");
                            continue;
                        }
                        if (pencilsTaken > pencils) {
                            System.out.println("Too many pencils were taken");
                            continue;
                        }
                        pencils -= pencilsTaken;
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2', '3'");
                    }
                } else {
                    pencilsTaken = switch (pencils % 4) {
                        case 0      -> 3;
                        case 1, 2   -> 1;
                        case 3      -> 2;
                        default -> throw new IllegalStateException("Unexpected value: " + (pencils % 4));
                    };
                    System.out.println(pencilsTaken);
                    pencils -= pencilsTaken;
                    break;
                }
            }
            round++;
        }
        System.out.println(winner + " won!");
    }
}
