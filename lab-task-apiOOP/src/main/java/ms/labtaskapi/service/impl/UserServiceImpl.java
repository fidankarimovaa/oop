package ms.labtaskapi.service.impl;

import lombok.RequiredArgsConstructor;
import ms.labtaskapi.dto.UserDto;
import ms.labtaskapi.dto.UserRequestDto;
import ms.labtaskapi.entity.User;
import ms.labtaskapi.exception.NotFoundException;
import ms.labtaskapi.repo.UserRepository;
import ms.labtaskapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto create(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        User userInRepo = userRepository.save(user);
        return modelMapper.map(userInRepo, UserDto.class);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("user not found with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new NotFoundException("user not found with username: " + userName));
        return modelMapper.map(user, UserDto.class);

    }

    @Override
    public Double getBalanceByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(() ->
                new NotFoundException("user not found with username: " + userName));
        return user.getBalance();
    }

    @Override
    public List<UserDto> getActiveUsers() {
        List<User> allUsers = userRepository.findAll();
        List<User> activeUsers = allUsers.stream()
                .filter(User::isEnable)
                .collect(Collectors.toList());

        return activeUsers.stream()
                .map(activeUser -> modelMapper.map(activeUser, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    @Override
    public UserDto update(Long id, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("user not found with id: " + id));
        existingUser.setUserName(userRequestDto.getUserName());
        existingUser.setAge(userRequestDto.getAge());
        existingUser.setName(userRequestDto.getName());
        existingUser.setBalance(userRequestDto.getBalance());
        existingUser.setEnable(userRequestDto.isEnable());
        existingUser.setSurname(userRequestDto.getSurname());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }
}
