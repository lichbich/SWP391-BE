package fpt.demo.service.servceImp;

import fpt.demo.model.User;
import fpt.demo.repository.UserRepository;
import fpt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long accountId) {
        return userRepository.findById(accountId).get();
    }

    @Override
    public List<User> getUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void deleteUser(Long accountId) {
        userRepository.deleteById(accountId);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);

    }
}
