package ru.team.up.sup.input.controller.privateController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.up.sup.core.entity.User;
import ru.team.up.sup.core.service.UserService;

import javax.persistence.PersistenceException;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alexey Tkachenko
 *
 * @link localhost:8080/swagger-ui.html
 * Документация API
 */

@Slf4j
@RestController
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "User Private Controller", description = "User API")
@RequestMapping(value = "/private/account/user")
public class UserController { // под снос
    private UserService userService;

    /**
     * @return Результат работы метода userService.getAllUsers() в виде коллекции юзеров
     * в теле ResponseEntity
     */
    @GetMapping
    @Operation(summary ="Получение списка всех юзеров")
    public ResponseEntity<List<User>> getAllUsers() {
        log.debug("Старт метода ResponseEntity<List<User>> getAllUsers()");

        ResponseEntity<List<User>> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(userService.getAllUsers());
        } catch (PersistenceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Получили ответ {}", responseEntity);

        return responseEntity;
    }

    /**
     * @param id Значение ID юзера
     * @return Результат работы метода userService.getOneUser(id) в виде объекта User
     * в теле ResponseEntity
     */
    @GetMapping("/{id}")
    @Operation(summary ="Получение юзера по id")
    public ResponseEntity<User> getOneUser(@PathVariable Long id) {
        log.debug("Старт метода ResponseEntity<User> getOneUser(@PathVariable Long id) с параметром {}", id);

        ResponseEntity<User> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(userService.getOneUser(id));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Получили ответ {}", responseEntity);

        return responseEntity;
    }

    /**
     * @param user Создаваемый объект класса User
     * @return Результат работы метода userService.saveUser(user) в виде объекта User
     * в теле ResponseEntity
     */
    @PostMapping
    @Operation(summary ="Создание юзера")
    public ResponseEntity<User> createUser(@RequestParam String user, @RequestBody @NotNull User userCreate) {
        log.debug("Старт метода ResponseEntity<User> createUser(@RequestBody @NotNull User user) с параметром {}", userCreate);

        ResponseEntity<User> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.saveUser(userCreate), HttpStatus.CREATED);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Получили ответ {}", responseEntity);

        return responseEntity;
    }

    /**
     * @param user Обновляемый объект класса User
     * @return Результат работы метода userService.saveUser(user) в виде объекта User
     * в теле ResponseEntity
     */
    @PatchMapping
    @Operation(summary ="Обновление юзера")
    public ResponseEntity<User> updateUser(@RequestBody @NotNull User user) {
        log.debug("Старт метода ResponseEntity<User> updateUser(@RequestBody @NotNull User user) с параметром {}", user);

        ResponseEntity<User> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(userService.saveUser(user));
        } catch (PersistenceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Получили ответ {}", responseEntity);

        return responseEntity;
    }

    /**
     * @param id Удаляемого объекта класса User
     * @return Объект ResponseEntity со статусом OK
     */
    @DeleteMapping("/{id}")
    @Operation(summary ="Удаление юзера")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        log.debug("Старт метода ResponseEntity<User> deleteUser(@PathVariable Long id) с параметром {}", id);

        ResponseEntity<User> responseEntity;
        try {
            userService.deleteUser(id);
            responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.debug("Получили ответ {}", responseEntity);

        return responseEntity;
    }
}
