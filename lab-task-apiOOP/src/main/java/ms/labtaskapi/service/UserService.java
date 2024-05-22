package ms.labtaskapi.service;

import ms.labtaskapi.dto.UserDto;
import ms.labtaskapi.dto.UserRequestDto;

import java.util.List;

public interface UserService {
    UserDto create(UserRequestDto userRequestDto);
    UserDto getById(Long id);
    UserDto getByUserName( String userName);
    Double getBalanceByUserName(String userName);
    List<UserDto> getActiveUsers();
    List<UserDto> getAllUsers();
    UserDto  update( Long id, UserRequestDto userRequestDto) ;

}
