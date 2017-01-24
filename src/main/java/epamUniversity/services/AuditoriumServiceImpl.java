package epamUniversity.services;

import epamUniversity.dao.AuditoriumRepository;
import epamUniversity.model.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

/**
 * AuditoriumServiceImpl - Returns info about auditoriums and places
 * <p>
 * Since auditorium information is usually static, store it in some property file. The information that needs to be stored is:
 * name
 * number of seats
 * vip seats (comma-separated list of expensive seats)
 * Several auditoriums can be stored in separate property files, information from them could be injected into the AuditoriumServiceImpl
 * <p>
 * getAuditoriums(), getSeatsNumber(), getVipSeats()
 */
@Service
public class AuditoriumServiceImpl implements AuditoriumService {
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    private List<Auditorium> auditoriumList;

    private Map<String, List<Integer>> bookedSeats;

    public AuditoriumServiceImpl() {
        auditoriumList = new LinkedList<>();
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriumList;
    }

    public Auditorium getAuditorium(String name) {
        return auditoriumList.stream()
                .filter(a -> a.getName().contains(name))
                .findFirst()
                .get();
    }

    public void setAuditorium(Auditorium auditorium) {
        auditoriumList.add(auditorium);
        auditoriumRepository.save(auditorium);
    }

    public void bookSeat(Auditorium auditorium, int seat) {
        bookedSeats.get(auditorium.getName()).add(seat);
    }

    public boolean isSeatBooked(Auditorium auditorium, int seat) {
        return bookedSeats.get(auditorium.getName()).contains(seat);
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new LinkedHashSet<>(auditoriumList);
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Auditorium auditorium = null;
        for (Auditorium a : auditoriumList) {
            if (a.getName().equals(name)) {
                auditorium = a;
            }
        }
        return auditorium;
    }
}
