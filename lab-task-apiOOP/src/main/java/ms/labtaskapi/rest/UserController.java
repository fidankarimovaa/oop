package ms.labtaskapi.rest;


import lombok.RequiredArgsConstructor;
import ms.labtaskapi.dto.UserDto;
import ms.labtaskapi.dto.UserRequestDto;
import ms.labtaskapi.service.impl.UserServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userServiceImp;

    @PostMapping
    public UserDto create(@RequestBody @Validated UserRequestDto userRequestDto) {
        return userServiceImp. create(userRequestDto);
    }

    @GetMapping
    List<UserDto> getAllUsers() {
        return userServiceImp.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        // if(userDtoMap.containsKey(id))
        return userServiceImp.getById(id);
    }
    @GetMapping("/getActive")
    public List<UserDto> getActiveUsers()
    {
        return userServiceImp.getActiveUsers();
    }

    @GetMapping("/username/{userName}")
    public UserDto getByUserName(@PathVariable String userName) {
        // if(userDtoMap.containsKey(id))
        return userServiceImp.getByUserName(userName);
    }

    @GetMapping("/balance/{userName}")
    public Double getBalanceByUserName(@PathVariable String userName) {
        // if(userDtoMap.containsKey(id))
        return userServiceImp.getBalanceByUserName(userName);
    }

    @PutMapping("/{id}")

    public UserDto update(@PathVariable Long id,@RequestBody UserRequestDto userRequestDto) {
        return userServiceImp.update(id, userRequestDto);
    }


}
