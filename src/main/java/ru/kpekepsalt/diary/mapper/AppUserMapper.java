package ru.kpekepsalt.diary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.model.AppUser;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
    AppUser dtoToUser(AppUserDto dto);
    AppUserDto userToDto(AppUser user);
}
