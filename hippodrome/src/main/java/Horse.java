import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Horse{

    public String name;
    public Integer speed;
    public Integer distance = 0;

    public Horse(String name, Integer speed) {
        this.name = name;
        this.speed = speed;
    }

    public void move() {
        this.distance += (int) (speed * Math.random());
    }

    public void print() {
        for (int i = 0; i < distance; i++) {
            System.out.print(".");
        }
        System.out.println(name);
    }
}
