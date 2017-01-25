package epamUniversity.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andriy_Yarish on 1/25/2017.
 */
public class Main {
    public static void main(String[] args) {
        int l[] = {4,6,2,1};

        for(int i = 0 ; i<l.length-1; i++){

            for (int j=1; j<l.length-i; j++){

                int next = l[j];
                int curent = l[j-1] ;
                int temp;

                if (curent < next){
                    temp = curent;
                    l[j-1] = next;
                    l[j] = temp;
                }
            }
        }

        for (int i: l) {
            System.out.println(i);
        }


    }
}
