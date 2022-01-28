import exception.UnrealRulesException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GameRules {

    private Integer winCondition = 200;
    private Integer minBet = 20;
    private Integer currentBet = 100;
    private Integer raceTime = 20;

    public GameRules(Integer winCondition, Integer minBet, Integer currentBet, Integer raceTime) {
        if (winCondition <= 0 || winCondition <= minBet) {
            throw new UnrealRulesException("Should be correct cash values");
        } else {
            this.winCondition = winCondition;
        }

        if (minBet <= 0) {
            throw new UnrealRulesException("Sense?");
        } else {
            this.minBet = minBet;
        }

        if (currentBet < minBet || currentBet > winCondition) {
            throw new UnrealRulesException();
        }

        if (raceTime <= 0) {
            throw new UnrealRulesException("How race can end before it started?");
        } else {
            this.raceTime = raceTime;
        }
    }
}
