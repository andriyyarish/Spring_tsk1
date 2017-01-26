package epamUniversity.dao;

import epamUniversity.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andriy_Yarish on 1/26/2017.
 */
public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
