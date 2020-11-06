package ru.kpekepsalt.diary;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.entities.IController;
import ru.kpekepsalt.diary.entities.ITask;
import ru.kpekepsalt.diary.entities.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TaskController implements IController {

    /**
     * @param date Дата для показа задач
     * @return Список задач
     */
    @GetMapping("/plan")
    public ResponseEntity<String> getPlan(@PathVariable String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleTaskBuilder taskBuilder = new SimpleTaskBuilder();
        ResponseEntity<String> response;
        try {
            taskBuilder.setStartDate(format.parse(date));
            taskBuilder.setTitle("Test task");
            response = new ResponseEntity<>(
                    taskBuilder.build().toJson(),
                    HttpStatus.OK
            );
        }catch(ParseException parseException){
            response = new ResponseEntity<>("Wrong date!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    /**
     * @return Список задач на текущий день
     */
    @GetMapping("/")
    public ResponseEntity<String> getTodayPlan(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getPlan(
                format.format(new Date())
        );
    }

    /**
     * @param title Название задачи
     * @param dateStart Дата начала задачи
     * @return
     */
    @PostMapping("/add-simple-task")
    public ResponseEntity<String> addSimpleTask(@RequestParam String title, @RequestParam String dateStart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleTaskBuilder taskBuilder = new SimpleTaskBuilder();
        ResponseEntity<String> response;
        try {
            taskBuilder.setStartDate(format.parse(dateStart));
            taskBuilder.setTitle(title);
            response = new ResponseEntity<>(
                    "Added task: "
                    + taskBuilder.build().toJson(),
                    HttpStatus.OK
            );
        }catch(ParseException parseException){
            response = new ResponseEntity<>("Wrong date!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @DeleteMapping("/remove-task")
    public ResponseEntity<String> removeTask(@PathVariable Long id){
        return new ResponseEntity<>("Removed", HttpStatus.OK);
    }

}
