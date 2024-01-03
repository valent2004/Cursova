package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private final UserRepository repository;

    public List<User> getUsers()
    {
        return repository.findAll();
    }

    public User getUserById(Long id)
    {
        return repository.getReferenceById(id);
    }

    public List<User> getUserByLogin(String login)
    {
        return repository.findAllByLoginContains(login);
    }

    public void createUser(User user){repository.save(user);}

    public void deleteUser(Long id){repository.deleteById(id);}

    public Page<User> sortUsersByLogin(int pageNo, int pageSize, String sortBy, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return repository.findAll(pageable);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void updateUser(User user, Long id){
        User updatedUser = repository.getReferenceById(id);
        if (!updatedUser.getLogin().equals(user.getLogin())) {
            updatedUser.setLogin(user.getLogin());
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            updatedUser.setPassword(user.getPassword());
        }
    }
}
