package ru.team.up.sup.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

/**
 * Сущность пользователь
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_ACCOUNT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userInterests"})
public class User extends Account {

    /**
     * Город
     */
    @Column(name = "CITY")
    private String city;

    /**
     * Возраст
     */
    @Column(name = "AGE", nullable = false)
    private Integer age;

    /**
     * Информация о пользователе
     */
    @Column(name = "ABOUT_USER")
    private String aboutUser;

    /**
     * Подписчики пользователя
     */
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ACCOUNT_SUBSCRIBERS", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBSCRIBER_ID"))
    @Column(name = "USER_SUBSCRIBERS")
    private Set<User> subscribers;

}
