package ru.team.up.sup.input.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.team.up.sup.core.entity.Event;
import ru.team.up.sup.core.entity.EventType;
import ru.team.up.sup.core.entity.User;
import ru.team.up.sup.core.repositories.EventRepository;
import ru.team.up.sup.core.repositories.UserRepository;
import ru.team.up.sup.input.service.EventServiceRest;

import java.util.List;

/**
 * @author Pavel Kondrashov
 */

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventServiceRestImpl implements EventServiceRest {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Event getEventById(Long id) {
        return eventRepository.getOne(id);
    }

    @Override
    public List<Event> getEventByName(String eventName) {
        return eventRepository.findByEventNameContaining(eventName);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllEventsByAuthor(User author) {
        return eventRepository.findAllByAuthorId(author);
    }

    @Override
    public List<Event> getAllEventsByEventType(EventType eventType) {
        return eventRepository.findAllByEventType(eventType);
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event addParticipant(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User participant = userRepository.getOne(userId);
//        List<User> participants = event.getParticipantsEvent();
//        participants.add(participant);
//        event.setParticipantsEvent(participants);
        return updateEvent(eventId, event);
    }

    @Override
    public Event deleteParticipant(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User participant = userRepository.getOne(userId);
//        List<User> participants = event.getParticipantsEvent();
//        participants.remove(participant);
//        event.setParticipantsEvent(participants);
        return updateEvent(eventId, event);
    }
}
