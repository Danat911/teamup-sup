package ru.team.up.sup.core.initialization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ru.team.up.sup.core.entity.Moderator;
import ru.team.up.sup.core.entity.Role;
import ru.team.up.sup.core.repositories.ModeratorRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Transactional
public class ModeratorsDefaultCreator {

    private final ModeratorRepository moderatorRepository;

    @Autowired
    public ModeratorsDefaultCreator(ModeratorRepository moderatorRepository) {
        this.moderatorRepository = moderatorRepository;
    }


    @Bean("ModeratorsDefaultCreator")
    public void moderatorsDefaultCreator() {
        moderatorRepository.save(Moderator.builder()
                .id(1L)
                .name("Moderator")
                .lastName("DefaultModerator")
                .login("moderator")
                .password(BCrypt.hashpw("moderator", BCrypt.gensalt(10)))
                .accountCreatedTime(LocalDate.now())
                .lastAccountActivity(LocalDateTime.now())
                .email("moderator@gmail.com")
                .role(Role.ROLE_MODERATOR)
                .amountOfCheckedEvents(2L)
                .amountOfClosedRequests(2L)
                .amountOfDeletedEvents(3L)
                .build());
    }
}