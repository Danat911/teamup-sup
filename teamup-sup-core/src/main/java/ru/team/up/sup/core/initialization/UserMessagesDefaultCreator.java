package ru.team.up.sup.core.initialization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.team.up.sup.core.entity.UserMessage;
import ru.team.up.sup.core.entity.UserMessageType;
import ru.team.up.sup.core.repositories.StatusRepository;
import ru.team.up.sup.core.repositories.UserMessageRepository;
import ru.team.up.sup.core.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@Transactional
public class UserMessagesDefaultCreator {

    private final UserMessageRepository userMessageRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public UserMessagesDefaultCreator(UserMessageRepository userMessageRepository,
                                      UserRepository userRepository,
                                      StatusRepository statusRepository) {
        this.userMessageRepository = userMessageRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @Bean("UserMessagesDefaultCreator")
    public void userMessagesDefaultCreator() {
        userMessageRepository.save(UserMessage.builder()
                .id(1L)
                .messageOwner(userRepository.getUserById(5L))
                .message("Приглашаю всех студентов окончивших Java-курс на встречу выпускников, которая состоится " +
                        "22.02.2022 в Санкт-Петербурге")
                .status(statusRepository.getOne(6L))
                .messageType(UserMessageType.NOT_SENT)
                .messageCreationTime(LocalDateTime.of(2021, 12, 20, 17, 0))
                .messageReadTime(LocalDateTime.of(2021, 12, 20, 19, 0))
                .users(Set.of(userRepository.getUserById(6L), userRepository.getUserById(7L)))
                .build());

        userMessageRepository.save(UserMessage.builder()
                .id(2L)
                .messageOwner(userRepository.getUserById(9L))
                .message("Приглашаю всех желающих на мастер-класс по йоге, которая состоится " +
                        "07.06.2022 в г.Сочи")
                .status(statusRepository.getOne(6L))
                .messageType(UserMessageType.NOT_SENT)
                .messageCreationTime(LocalDateTime.of(2021, 12, 22, 8, 30))
                .messageReadTime(LocalDateTime.of(2021, 12, 22, 12, 15))
                .users(Set.of(userRepository.getUserById(2L), userRepository.getUserById(3L),
                        userRepository.getUserById(6L), userRepository.getUserById(8L)))
                .build());
    }
}