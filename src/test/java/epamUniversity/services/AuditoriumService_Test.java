package epamUniversity.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Andriy_Yarish on 12/27/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration()
public class AuditoriumService_Test {

    @Test
    public void test(){
        AuditoriumService auditoriumService = new AuditoriumServiceImpl();

        List<String> aLs = auditoriumService.getAll()
                .stream()
                .map(auditorium -> auditorium.getName())
                .collect(Collectors.toList());

        System.out.println(aLs);
    }

}
