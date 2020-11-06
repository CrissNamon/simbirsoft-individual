package ru.kpekepsalt.diary.entities;

import java.util.Date;

public interface ITask {

    /**
     * @return Дата начала задачи
     */
    Date getStartDate();

    /**
     * @return Название задачи
     */
    String getTitle();

    /**
     * Устанавливает дату начала для задачи
     * @param date Дата начала задачи
     */
    void setStartDate(Date date);

    /**
     * Устанавливает название задачи
     * @param title Название задачи
     */
    void setTitle(String title);

    /**
     * @return Идентификатор задачи
     */
    Long getId();
    String toJson();

}
