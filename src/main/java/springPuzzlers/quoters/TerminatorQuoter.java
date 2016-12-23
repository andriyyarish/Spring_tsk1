package springPuzzlers.quoters;

import javax.annotation.PostConstruct;

import springPuzzlers.quoters.anotations.InjectRandomInt;
import springPuzzlers.quoters.anotations.Profiling;


@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;
    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1 ");
        System.out.println(repeat);
    }

    @PostConstruct
    public void init(){
        System.out.println("Phase 2 ");
        System.out.println(repeat);
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(i + " Message: " + message);
        }
    }

    public String getMessage() {
        return message;
    }

    public TerminatorQuoter setMessage(String message) {
        this.message = message;
        return this;
    }
}
