package ru.team.up.sup.core.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.team.up.dto.AppModuleNameDto;
import ru.team.up.sup.core.entity.Parameter;
import ru.team.up.sup.core.repositories.ParameterRepository;
import ru.team.up.sup.core.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.team.up.sup.core.entity.ParameterType.*;

@Component
@Transactional
public class ParameterDefaultCreator {

    private ParameterRepository parameterRepository;

    @Autowired
    public ParameterDefaultCreator(ParameterRepository parameterRepository, UserRepository userRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Bean("ParameterDefaultCreator")
    public void parameterDefaultCreator() {
        parameterRepository.save(Parameter.builder()
                .id(1L)
                .parameterName("testName")
                .parameterType(STRING)
                .parameterValue("testValue")
                .systemName(AppModuleNameDto.TEAMUP_CORE)
                .creationDate(LocalDate.now())
                .updateDate(LocalDateTime.now())
                .build()
        );
        parameterRepository.save(Parameter.builder()
                .id(2L)
                .parameterName("testName2")
                .parameterType(STRING)
                .parameterValue("testValue2")
                .systemName(AppModuleNameDto.TEAMUP_CORE)
                .creationDate(LocalDate.now())
                .updateDate(LocalDateTime.now()
                        //              .userWhoLastChangeParameters(userRepository.getOne(1L)
                ).build()
        );
        parameterRepository.save(Parameter.builder()
                .id(3L)
                .parameterName("СIAMetingFlag")
                .parameterType(STRING)
                .parameterValue("AgentFreed0m")
                .systemName(AppModuleNameDto.TEAMUP_CORE)
                .creationDate(LocalDate.of(2020, 12, 12))
                .updateDate(LocalDateTime.now()
                        //              .userWhoLastChangeParameters(userRepository.getOne(1L)
                ).build()
        );
        parameterRepository.save(Parameter.builder()
                .id(4L)
                .parameterName("MonetizationLevel")
                .parameterType(INTEGER)
                .parameterValue("0")
                .systemName(AppModuleNameDto.TEAMUP_CORE)
                .creationDate(LocalDate.of(2019, 12, 12))
                .updateDate(LocalDate.of(2019, 12, 12).atTime(13, 12)
                        //              .userWhoLastChangeParameters(userRepository.getOne(1L)
                ).build()
        );
        parameterRepository.save(Parameter.builder()
                .id(5L)
                .parameterName("DestroySystem")
                .parameterType(BOOLEAN)
                .parameterValue("false")
                .systemName(AppModuleNameDto.TEAMUP_KAFKA)
                .creationDate(LocalDate.of(2019, 12, 12))
                .updateDate(LocalDate.of(2019, 12, 12).atTime(13, 12)
                        //              .userWhoLastChangeParameters(userRepository.getOne(1L)
                ).build()
        );

    }
}
