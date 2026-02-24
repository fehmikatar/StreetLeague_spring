package tn.esprit._4se2.pi.Service;

import tn.esprit._4se2.pi.entities.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);
}
