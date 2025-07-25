package kr.ac.hansung.cse.board_and_chatting.repository;

import kr.ac.hansung.cse.board_and_chatting.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
//@Profile("jpa")
public class UserJpaRepository implements UserRepository {

    private final JpaUserRepository userRepository;

    public UserJpaRepository(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
