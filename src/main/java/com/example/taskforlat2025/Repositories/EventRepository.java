package com.example.taskforlat2025.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskforlat2025.Entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
