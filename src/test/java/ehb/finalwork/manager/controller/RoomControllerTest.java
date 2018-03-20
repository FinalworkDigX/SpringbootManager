package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.TestUtil;
import ehb.finalwork.manager.dto.RethinkRoomDto;
import ehb.finalwork.manager.model.Room;
import ehb.finalwork.manager.service.RoomService;
import ehb.finalwork.manager.websockets.WebSocketConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebSocketConfig.class)
public class RoomControllerTest {

    private Room room;
    private RethinkRoomDto roomDto;

    // Mock
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();

        room = new Room("id_1", "name_1", "desc_1", "loc_1");
        roomDto = new RethinkRoomDto("name_1", "desc_1", "loc_1");
    }

    @Test
    public void getRoomsTest() throws Exception {

        List<Room> allRooms = singletonList(room);

        when(roomService.getAll()).thenReturn(allRooms);

        mockMvc.perform(get("/v1/room"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].id", is("id_1")))
               .andExpect(jsonPath("$[0].name", is("name_1")))
               .andExpect(jsonPath("$[0].description", is("desc_1")))
               .andExpect(jsonPath("$[0].location", is("loc_1")));

        verify(roomService, times(1)).getAll();
        verifyNoMoreInteractions(roomService);
    }

    @Test
    public void getRoomByIdTest() throws Exception {

        when(roomService.getById(room.getId())).thenReturn(room);

        mockMvc.perform(get("/v1/room/byId/{id}", room.getId()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

               .andExpect(jsonPath("$.id", is("id_1")))
               .andExpect(jsonPath("$.name", is("name_1")))
               .andExpect(jsonPath("$.description", is("desc_1")))
               .andExpect(jsonPath("$.location", is("loc_1")));

        verify(roomService, times(1)).getById(room.getId());
        verifyNoMoreInteractions(roomService);
    }

    @Test
    public void addRoomTest() throws Exception {

        when(roomService.create(any(RethinkRoomDto.class))).thenReturn(room);

        mockMvc.perform(post("/v1/room")
                .content(TestUtil.convertObjectToJsonBytes(roomDto))
                .contentType(MediaType.APPLICATION_JSON)
        )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

               .andExpect(jsonPath("$.id", is("id_1")))
               .andExpect(jsonPath("$.name", is("name_1")))
               .andExpect(jsonPath("$.description", is("desc_1")))
               .andExpect(jsonPath("$.location", is("loc_1")));

        ArgumentCaptor<RethinkRoomDto> dtoCaptor = ArgumentCaptor.forClass(RethinkRoomDto.class);
        verify(roomService, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(roomService);

        RethinkRoomDto dtoArgument = dtoCaptor.getValue();
        assertThat(dtoArgument.getName(), is("name_1"));
        assertThat(dtoArgument.getDescription(), is("desc_1"));
        assertThat(dtoArgument.getLocation(), is("loc_1"));
    }

    @Test
    public void deleteRoomTest() throws Exception {

        doNothing().when(roomService).delete(room.getId());

        mockMvc.perform(delete("/v1/room/{rid}", room.getId()))
               .andExpect(status().isOk());

        verify(roomService, times(1)).delete(room.getId());
        verifyNoMoreInteractions(roomService);
    }
}
