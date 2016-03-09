import Entities.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andriy_Yarish on 3/9/2016.
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();

        Integer a = new Integer(5);
        list.add(a);
        Integer b=new Integer(555);
        a=b;
        list.stream().forEach(i -> System.out.println(i));

        List<Event> listEvent = new LinkedList<>();
        Event event1 = new Event("initial name",123.56,"initial rate");
        listEvent.add(event1);
        listEvent.stream().forEach(e -> System.out.println(e));
        event1.setName("changed Name");
        event1.setRating("changed rate");
        listEvent.stream().forEach(e -> System.out.println(e));

        System.out.println(list.get(0));

    }
}
