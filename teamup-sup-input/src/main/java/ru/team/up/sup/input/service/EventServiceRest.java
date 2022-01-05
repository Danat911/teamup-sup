package ru.team.up.sup.input.service;

import ru.team.up.sup.core.entity.Event;
import ru.team.up.sup.core.entity.EventType;
import ru.team.up.sup.core.entity.User;

import java.util.List;

/**
 * Сервис для поиска, создания, обновления, удаления мероприятий
 *
 * @author Pavel Kondrashov on 23.10.2021
 */
public interface EventServiceRest {

    /**
     * Метод для поиска мероприятия по идентификатору
     *
     * @param id Идентификатор мероприятия
     * @return Мероприятие по заданному идентификатору
     */
    Event getEventById(Long id);

    /**
     * Метод для получния мероприятия по названию
     *
     * @param eventName Название мероприятия
     * @return Мероприятие по заданному названию
     */
    List<Event> getEventByName(String eventName);

    /**
     * Метод для получения списка мероприятий
     *
     * @return Список мероприятий
     */
    List<Event> getAllEvents();

    /**
     * Метод получения мероприятий по автору
     *
     * @param author Автор(создатель) мероприятия
     * @return Список мероприятий по автору
     */
    List<Event> getAllEventsByAuthor(User author);

    /**
     * Метод получения мероприятий по типу
     *
     * @param eventType тип мероприятия
     * @return Список мероприятий
     */
    List<Event> getAllEventsByEventType(EventType eventType);

    /**
     * Метод сохранения\создания мероприятия
     *
     * @param event Мероприятие
     * @return Сохранненное мероприятие
     */
    Event saveEvent(Event event);

    /**
     * Метод обновления мероприятия
     *
     * @param id    Идентификатор мероприятия
     * @param event Мероприятие для изменений
     * @return Обновленное мероприятие
     */
    Event updateEvent(Long id, Event event);

    /**
     * Метод для удаления мероприятия по идентификатору
     *
     * @param id Идентификатор мероприятия
     */
    void deleteEvent(Long id);

    /**
     * Метод добавления участника мероприятия
     *
     * @param eventId Идентификатор мероприятия
     * @param userId  Идентификатор участника
     * @return Обновленное мероприятие
     */
    Event addParticipant(Long eventId, Long userId);

    /**
     * Метод удаления участника мероприятия
     *
     * @param eventId Идентификатор мероприятия
     * @param userId  Идентификатор участника
     * @return Обновленное мероприятие
     */
    Event deleteParticipant(Long eventId, Long userId);
}
