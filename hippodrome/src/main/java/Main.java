import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        int winCondition = 1000;
        String[] options = {
                "1: Place a bet",
                "2: Change a road",
                "3: Start race",
                "4: Exit",
                "Goal: Earn 1000$",
                "Input your choice"
        };

        Player player = new Player();
        player.setCash(100);

        List<Horse> horses = new ArrayList<Horse>();
        horses.add(new Horse("Black Betty", 11));
        horses.add(new Horse("Delorean", 10));
        horses.add(new Horse("Eleanor", 10));
        horses.add(new Horse("Interceptor", 9));

        Hippodrome hippodrome = new Hippodrome(horses, player);

        Scanner scanner = new Scanner(System.in);
        int option = 1;

        while (option != 4) {
            System.out.println("Cash: " + player.getCash());
            System.out.println("Bet: " + hippodrome.getCurrentBet());
            System.out.println("Road:" + hippodrome.getBetRoad());
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        if (hippodrome.getCurrentBet() != 30) {
                            System.out.println("Bets are made");
                            break;
                        }
                        if (player.getCash() <= 20) {
                            System.out.println("don't waste my time");
                            break;
                        }
                        System.out.println("How much money you ready to loose?");
                        try {
                            Scanner moneyScanner = new Scanner(System.in);
                            int bet = moneyScanner.nextInt();
                            hippodrome.setCurrentBet(bet);
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter an integer value");
                            scanner.next();
                        } catch (Exception ex) {
                            System.out.println("An unexpected error happened. Please try again");
                            scanner.next();
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Which road do you bet on?");
                        try {
                            Scanner roadScanner = new Scanner(System.in);
                            int betRoad = roadScanner.nextInt();
                            hippodrome.setBetRoad(betRoad);
                        } catch (InputMismatchException ex) {
                            System.out.println("Please enter an integer value between 1 and  " + horses.size());
                            scanner.next();
                        } catch (Exception ex) {
                            System.out.println("An unexpected error happened. Please try again");
                            scanner.next();
                        }
                        break;
                    }
                    case 3:
                        hippodrome.run();
                        if (player.getCash() >= winCondition) {
                            hippodrome.printWinner();
                            System.out.println("Lucky Chak!!!");
                            exit(0);
                        }
                        if (player.getCash() <= 0) {
                            System.out.println("LOOSER");
                        }
                        break;
                    case 4:
                        exit(0);
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }
        }
    }

    private static void printMenu(String[] menu) {
        for (String s : menu) {
            System.out.println("| " + s);
        }
    }
}
