package ru.kpekepsalt.diary.entities;

import java.util.Date;

public interface ITaskBuilder {

    /**
     * Устанавливает дату начала для задачи
     * @param date Дата начала задачи
     */
    ITaskBuilder setStartDate(Date date);

    /**
     * Устанавливает название задачи
     * @param title Название задачи
     */
    ITaskBuilder setTitle(String title);

    /**
     * @return Объект задачи
     */
    ITask build();

}
