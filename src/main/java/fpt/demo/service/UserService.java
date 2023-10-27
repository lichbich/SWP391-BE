package fpt.demo.service;

import fpt.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User addUser(User user);
    public User getUserById(Long accountId);
    public List<User> getUsers();
    public void deleteUser(Long accountId);
    public User updateUser(User user);

}
