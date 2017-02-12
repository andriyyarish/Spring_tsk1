package com.epamUniversity.dao;

import com.epamUniversity.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andriy_Yarish on 1/24/2017.
 */
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
