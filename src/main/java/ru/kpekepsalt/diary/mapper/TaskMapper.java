package ru.kpekepsalt.diary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    TaskDto taskToDto(Task task);
    Task dtoToTask(TaskDto dto);
}
