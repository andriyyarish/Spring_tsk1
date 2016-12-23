package springPuzzlers.quoters;

/**
 * Created by Newman on 12/23/16.
 */
public class T1000 extends TerminatorQuoter implements Quoter {
    @Override
    public void sayQuote(){
        System.out.println("Overriden terminator");
    }
}
