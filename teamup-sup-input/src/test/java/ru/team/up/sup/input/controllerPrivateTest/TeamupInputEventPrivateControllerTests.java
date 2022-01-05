package ru.team.up.sup.input.controllerPrivateTest;

import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.team.up.sup.core.entity.*;
import ru.team.up.sup.core.service.EventService;
import ru.team.up.sup.input.controller.privateController.EventController;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TeamupInputEventPrivateControllerTests {

    @Mock
    private EventService eventService;

    @Autowired
    @InjectMocks
    private EventController eventController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    EventType eventType = EventType.builder()
            .type("type")
            .build();

    Interests programming = Interests.builder()
            .title("Programming")
            .shortDescription("Like to write programs in java")
            .build();

    User testUser = User.builder()
            .name("Aleksey")
            .lastName("Tkachenko")
            .middleName("Petrovich")
            .login("alextk")
            .email("alextk@bk.ru")
            .password("1234")
            .accountCreatedTime(LocalDate.now())
            .lastAccountActivity(LocalDateTime.now())
            .city("Moscow")
            .age(43)
            .aboutUser("Studying to be a programmer.")
            .userInterests(Collections.singleton(programming))
            .build();


    Status status = Status.builder()
            .status("status")
            .build();

    Event event = Event.builder()
            .eventName("eventName")
            .descriptionEvent("descriptionEvent")
            .placeEvent("placeEvent")
            .timeEvent(LocalDateTime.now())
            .eventUpdateDate(LocalDate.now())
            .participantsEvent(Set.of(testUser))
            .eventType(eventType)
            .authorId(testUser)
            .eventInterests(Collections.singleton(programming))
            .status(status)
            .build();
    Event emptyEvent = Event.builder()
            .eventName("")
            .descriptionEvent("")
            .placeEvent("")
            .timeEvent(LocalDateTime.now())
            .eventUpdateDate(LocalDate.now())
            .participantsEvent(Set.of(testUser))
            .eventType(eventType)
            .authorId(testUser)
            .eventInterests(Collections.singleton(programming))
            .status(status)
            .build();


    ArrayList<Event> listEvent = new ArrayList<>();

    @Test
    public void testCreateEvents() {
        when(eventService.saveEvent(event)).thenReturn(event);
        Assert.assertEquals(201, eventController.createEvent(event).getStatusCodeValue());
    }
    @Test
    public void testCreateEmptyEvents() {
        when(eventService.saveEvent(emptyEvent)).thenThrow(new PersistenceException());
        Assert.assertEquals(400, eventController.createEvent( emptyEvent).getStatusCodeValue());
    }

    @Test
    public void testGetOneById() {
        when(eventService.getOneEvent(event.getId())).thenReturn(event);
        Assert.assertEquals(200, eventController.getOneEvent(event.getId()).getStatusCodeValue());
    }

    @Test
    public void testGetAllEvents(){
        listEvent.add(event);
        when(eventService.getAllEvents()).thenReturn(listEvent);
        Assert.assertEquals(200, eventController.getAllEvents().getStatusCodeValue());
    }

    @Test
    public void testUpdateEvents() {
        when(eventService.saveEvent(event)).thenReturn(event);
        Assert.assertEquals(200, eventController.updateEvent(event).getStatusCodeValue());
    }

    @Test
    public void testUpdateEmptyEvents() {
        when(eventService.saveEvent(emptyEvent)).thenThrow(new PersistenceException());
        Assert.assertEquals(400, eventController.updateEvent(emptyEvent).getStatusCodeValue());
    }
    @Test
    public void testDeleteEvents() {
        Assert.assertEquals(202, eventController.deleteAdmin(event.getId()).getStatusCodeValue());
    }
}
