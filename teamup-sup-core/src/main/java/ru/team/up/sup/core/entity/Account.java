package ru.team.up.sup.core.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.*;

/**
 * Родительская сущность для пользователя, админа, модератора
 */
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
public class Account implements UserDetails {
    /**
     * Уникальный идентификатор
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Фамилия
     */
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    /**
     * Логин
     */
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    /**
     * Роль
     */
    @Column(name = "ROLE")
    private Role role;
    /**
     * Электронная почта
     */
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    /**
     * Пароль
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /**
     * Время создания аккаунта
     */
    @Column(name = "ACCOUNT_CREATED_TIME", nullable = false)
    private LocalDate accountCreatedTime;

    /**
     * Время последней активности
     */
    @Column(name = "LAST_ACCOUNT_ACTIVITY", nullable = false)
    private LocalDateTime lastAccountActivity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
