package ru.kpekepsalt.diary;

import com.google.gson.Gson;
import ru.kpekepsalt.diary.entities.Task;

import java.util.Date;

public class SimpleTask extends Task {

    private Date dateEnd;

    public SimpleTask(String title, Date dateStart, Date dateEnd) {
        super(title, dateStart);
        this.dateEnd = dateEnd;
    }

    public SimpleTask() {}

    /**
     * @return Дата окончания задачи
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
