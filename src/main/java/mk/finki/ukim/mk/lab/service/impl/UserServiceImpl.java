package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.InvalidArgumentException;
import mk.finki.ukim.mk.lab.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
            throw  new UsernameAlreadyExistsException(username);

        User user = new User(username,passwordEncoder.encode(password),name,surname,role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
