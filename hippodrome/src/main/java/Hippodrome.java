import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.Getter;

public class Hippodrome {
    private List<Horse> horses;
    private Player player;
    @Getter
    private Integer currentBet;
    @Getter
    private Integer betRoad;

    Hippodrome(List<Horse> horses, Player player) {
        this.horses = horses;
        this.player = player;
        this.currentBet = 30;
        this.betRoad = 1;
    }

    public void run() {
        if(player.getCash() < currentBet) {
            System.out.println("GTFO");
        }
        player.setCash(player.getCash() - currentBet);
        int raceTime = 40;
        for (int i = 0; i < raceTime; i++) {
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
            if ((winnerRoad).equals(betRoad)) {
                player.setCash(player.getCash() + (currentBet * (horses.size() - 1)));
            }
        } catch (NoSuchElementException ne) {
            System.out.println("Shit happens");
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
        Horse winner = horses.stream().max(Comparator.comparing(Horse::getDistance)).orElse(null);
        if (winner != null) {
            return winner;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void printWinner() {
        System.out.println("Winner !!! -> " + getWinner().name);
    }

    public void setBetRoad(Integer value) {
        if (value <= 0 || value > horses.size()) {
            System.out.println("Incorrect input");
        } else {
            this.betRoad = value;
        }
    }

    public void setCurrentBet(Integer value) {
        if (value <= 20) {
            System.out.println("Don't waste my time");
        } else if (value > player.getCash()) {
            System.out.println("Don't try to lie me fool!1!11!");
        } else {
            currentBet = value;
            System.out.println("Good deal");
        }
    }

    private void allToZero() {
        this.currentBet = 30;
        for (Horse horse : horses) {
            horse.distance = 0;
        }
    }
}
