package ru.team.up.sup.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Сущность модератор
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MODERATOR_ACCOUNT")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Moderator extends Account {

    /**
     * количество закрытых обращений
     */
    @Column(name = "AMOUNT_OF_CLOSED_REQUESTS")
    private Long amountOfClosedRequests;

    /**
     * количество проверенных мероприятий
     */
    @Column(name = "AMOUNT_OF_CHECKED_EVENTS")
    private Long amountOfCheckedEvents;

    /**
     * количество удалённых мероприятий
     */
    @Column(name = "AMOUNT_OF_DELETED_EVENTS")
    private Long amountOfDeletedEvents;
}
