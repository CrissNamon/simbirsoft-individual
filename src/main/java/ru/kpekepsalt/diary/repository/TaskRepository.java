package ru.kpekepsalt.diary.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpekepsalt.diary.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * @param date Date for searching
     * @return List of tasks for given date
     */
    List<Task> findByDate(LocalDate date);

    List<Task> findByUserId(Long id);

    List<Task> findByUserIdAndDate(Long id, LocalDate date);

}
