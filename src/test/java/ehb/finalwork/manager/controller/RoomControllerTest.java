package ehb.finalwork.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ehb.finalwork.manager.controller.RoomController;
import ehb.finalwork.manager.dto.RethinkRoomDto;

import ehb.finalwork.manager.model.Room;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomController roomController;

    @Test
    public void getRooms() throws Exception {

        RethinkRoomDto r1 = new RethinkRoomDto("1", "test_1", "test_desc_1", "test_loc_1");
        RethinkRoomDto r2 = new RethinkRoomDto("2", "test_2", "test_desc_2", "test_loc_2");

        List<RethinkRoomDto> allRooms = new ArrayList<RethinkRoomDto>();
        allRooms.add(r1);
        allRooms.add(r2);

        given(this.roomController.getRooms()).willReturn(allRooms);

        this.mvc.perform(get("/room").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(r1.getName())))
                .andExpect(jsonPath("$[1].name", is(r2.getName())));
    }

    @Test
    public void addRoom() throws Exception {

        Room r1 = new Room("test_2", "test_desc_2", "test_loc_2");

        given(this.roomController.postRoom(r1)).willReturn(r1);

        String s = asJsonString(r1);

        this.mvc.perform(
                post("/room")
                        .content(asJsonString(r1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
//                .andExpect(jsonPath("name", is(r1.getName())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}