import exception.NoWinnerException;
import exception.UnrealRulesException;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class Hippodrome {
    private List<Horse> horses;
    private Player player;
    private Integer currentBet;
    private Integer betRoad;
    private GameRules rules;

    Hippodrome(List<Horse> horses, Player player, GameRules rules) {
        if (horses.size() < 2) {
            throw new UnrealRulesException("At least two horses must participate in the race");
        } else {
            this.horses = horses;
        }
        if (player.getCash() < rules.getMinBet()) {
            throw new UnrealRulesException("How do you wanna play without money?");
        } else {
            this.player = player;
        }
        this.currentBet = rules.getMinBet();
        this.betRoad = 1;
        this.rules = rules;
    }

    public void run() {
        if (player.getCash() < currentBet) {
            System.out.println("GTFO");
            return;
        }
        player.setCash(player.getCash() - currentBet);
        for (int i = 0; i < rules.getRaceTime(); i++) {
            move();
            print();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Horse winner = getWinner();
            Integer winnerRoad = horses.indexOf(winner) + 1;
            Integer cashMove = currentBet * (horses.size() - 1);
            System.out.println("Winner!!! -> " + winner.getName());
            System.out.println("Road -> " + winnerRoad);
            System.out.println("Previous Cash -> " + (player.getCash() + currentBet));
            System.out.println("Minus Bet -> " + player.getCash());
            System.out.println();
            if ((winnerRoad).equals(betRoad)) {
                System.out.println("You win this race!");
                System.out.println("Cash -> +" + cashMove);
                player.setCash(player.getCash() + cashMove);
            } else {
                System.out.println("You lose this race");
                System.out.println("Cash -> -" + this.getCurrentBet());
            }
        } catch (NoWinnerException ne) {
            System.out.println("No one horse win. Shit happens");
        } finally {
            allToZero();
        }

    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.stream()
                .max(Comparator
                        .comparing(Horse::getDistance)).orElse(null);
        if (winner != null) {
            return winner;
        } else {
            throw new NoWinnerException();
        }
    }

    public void setBetRoad(Integer value) {
        if (value <= 0 || value > horses.size()) {
            System.out.println("Incorrect input");
        } else {
            betRoad = value;
        }
    }

    public void setCurrentBet(Integer value) {
        if (value <= rules.getMinBet()) {
            System.out.println("Don't waste my time");
        } else if (value > player.getCash()) {
            System.out.println("Don't try to lie me fool!1!11!");
        } else {
            currentBet = value;
            System.out.println("Good deal");
        }
    }

    private void allToZero() {
        currentBet = rules.getMinBet();
        for (Horse horse : horses) {
            horse.setDistance(0);
        }
    }
}
