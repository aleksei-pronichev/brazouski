import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameRules rules = new GameRules();

        List<Horse> horses = new ArrayList<Horse>();
        horses.add(new Horse("Black Betty", 11));
        horses.add(new Horse("Delorean", 10));
        horses.add(new Horse("Eleanor", 10));
        horses.add(new Horse("Interceptor", 9));

        Hippodrome game = new Hippodrome(horses, new Player(100), rules);

        HashMap<Integer, String> options = new HashMap<>();

        options.put(1, "Place a bet");
        options.put(2, "Change a road");
        options.put(3, "Start race");
        options.put(4, "Exit");

        Scanner scanner = new Scanner(System.in);
        int option = 1;

        gameLoop:
        while (true) {
            if (game.getPlayer().getCash() <= 0) {
                break;
            } else if (game.getPlayer().getCash() >= game.getRules().getWinCondition()) {
                break;
            }
            printMenu(game, options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        placeBetOption(game, rules, scanner);
                        break;
                    case 2:
                        changeRoadOption(game, scanner);
                        break;
                    case 3:
                        startRaceOption(game);
                        break;
                    case 4:
                        break gameLoop;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value between 1 and " + options.size());
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }
        }
        result(game);
    }

    private static void placeBetOption(Hippodrome hippodrome, GameRules gameRules, Scanner scanner) {
        if (hippodrome.getPlayer().getCash() <= gameRules.getMinBet()) {
            System.out.println("don't waste my time");
            return;
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
    }

    private static void changeRoadOption(Hippodrome hippodrome, Scanner scanner) {
        System.out.println("Which road do you bet on?");
        try {
            Scanner roadScanner = new Scanner(System.in);
            int betRoad = roadScanner.nextInt();
            hippodrome.setBetRoad(betRoad);
        } catch (InputMismatchException ex) {
            System.out.println("Please enter an integer value between 1 and  " + hippodrome.getHorses().size());
            scanner.next();
        } catch (Exception ex) {
            System.out.println("An unexpected error happened. Please try again");
            scanner.next();
        }
    }

    private static void startRaceOption(Hippodrome hippodrome) {
        hippodrome.run();
    }

    private static void printMenu(Hippodrome hippodrome, HashMap<Integer, String> options) {
        System.out.println();
        System.out.println("Cash: " + hippodrome.getPlayer().getCash());
        System.out.println("Bet: " + hippodrome.getCurrentBet());
        System.out.println("Road:" + hippodrome.getBetRoad());
        iterateMenu(options);
        System.out.println("Goal: Earn " + hippodrome.getRules().getWinCondition());
        System.out.println("Input your choice");
    }

    private static void iterateMenu(HashMap<Integer, String> menu) {
        for (Integer name : menu.keySet()) {
            String key = name.toString();
            String value = menu.get(name);
            System.out.println("| " + key + ": " + value);
        }
    }

    private static void result(Hippodrome game) {
        if (game.getPlayer().getCash() >= game.getRules().getWinCondition()) {
            System.out.println("Congratulations!!!");
        } else {
            System.out.println("Looser");
        }
        System.out.println("Your cash -> " + game.getPlayer().getCash());
    }
}
