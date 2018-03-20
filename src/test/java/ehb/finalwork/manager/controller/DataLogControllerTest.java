package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.TestUtil;
import ehb.finalwork.manager.dto.RethinkDataLogDto;
import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.Information;
import ehb.finalwork.manager.service.DataLogService;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.toIntExact;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebSocketConfig.class)
public class DataLogControllerTest {

    private List<Information> infoList;
    private Long timestamp;
    private DataLog dataLog;
    private RethinkDataLogDto dataLogDto;

    // Mock
    private MockMvc mockMvc;

    @Mock
    private DataLogService dataLogService;

    @InjectMocks
    private DataLogController dataLogController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dataLogController).build();

        infoList = new ArrayList<Information>();
        infoList.add(new Information("name_1", "data_1", 1L));
        infoList.add(new Information("name_2", "data_2", 2L));

        timestamp = Instant.now().getEpochSecond();
        dataLog = new DataLog("id_1", "item_id_1", infoList, timestamp);
        dataLogDto = new RethinkDataLogDto("item_id_1", infoList, timestamp);
    }

    @Test
    public void getDataLogsTest() throws Exception {

        List<DataLog> allDataLogs = Collections.singletonList(dataLog);

        when(dataLogService.getAll()).thenReturn(allDataLogs);

        mockMvc.perform(get("/v1/dataLog"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].id", is("id_1")))
               .andExpect(jsonPath("$[0].itemId", is("item_id_1")))
               .andExpect(jsonPath("$[0].information", hasSize(2)))
               .andExpect(jsonPath("$[0].timestamp", is(toIntExact(timestamp))));

        verify(dataLogService, times(1)).getAll();
        verifyNoMoreInteractions(dataLogService);
    }

    @Test
    public void getDataLogByIdTest() throws Exception {

        when(dataLogService.getById(dataLog.getId())).thenReturn(dataLog);

        mockMvc.perform(get("/v1/dataLog/byId/{id}", dataLog.getId()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))

               .andExpect(jsonPath("$.id", is("id_1")))
               .andExpect(jsonPath("$.itemId", is("item_id_1")))
               .andExpect(jsonPath("$.information", hasSize(2)))
               .andExpect(jsonPath("$.information[0].name", is("name_1")))
               .andExpect(jsonPath("$.information[0].data", is("data_1")))
               .andExpect(jsonPath("$.information[0].index", is(1)))
               .andExpect(jsonPath("$.timestamp", is(toIntExact(timestamp))));

        verify(dataLogService, times(1)).getById(dataLog.getId());
        verifyNoMoreInteractions(dataLogService);
    }

    @Test
    public void getDataLogByItemIdTest() throws Exception {

        List<DataLog> dataLogList = Collections.singletonList(dataLog);

        when(dataLogService.getByItemId(dataLog.getItemId())).thenReturn(dataLogList);

        mockMvc.perform(get("/v1/dataLog/byItemId/{id}", dataLog.getItemId()))
               .andExpect(status().isOk())
               .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))

               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].id", is("id_1")))
               .andExpect(jsonPath("$[0].itemId", is("item_id_1")))
               .andExpect(jsonPath("$[0].information", hasSize(2)))
               .andExpect(jsonPath("$[0].timestamp", is(toIntExact(timestamp))));

        verify(dataLogService, times(1)).getByItemId(eq(dataLog.getItemId()));
        verifyNoMoreInteractions(dataLogService);
    }

    @Test
    public void addDataLogTest() throws Exception {

        when(dataLogService.create(any(RethinkDataLogDto.class))).thenReturn(dataLog);

        mockMvc.perform(post("/v1/dataLog")
                            .content(TestUtil.convertObjectToJsonBytes(dataLogDto))
                            .contentType(MediaType.APPLICATION_JSON)
        )
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

               .andExpect(jsonPath("$.id", is("id_1")))
               .andExpect(jsonPath("$.itemId", is("item_id_1")))
               .andExpect(jsonPath("$.information", hasSize(2)))
               .andExpect(jsonPath("$.information[0].name", is("name_1")))
               .andExpect(jsonPath("$.information[0].data", is("data_1")))
               .andExpect(jsonPath("$.information[0].index", is(1)))
               .andExpect(jsonPath("$.timestamp", is(toIntExact(timestamp))));

        ArgumentCaptor<RethinkDataLogDto> dtoCaptor = ArgumentCaptor.forClass(RethinkDataLogDto.class);
        verify(dataLogService, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(dataLogService);

        RethinkDataLogDto dtoArgument = dtoCaptor.getValue();
        assertThat(dtoArgument.getItemId(), is("item_id_1"));
        assertThat(dtoArgument.getTimestamp(), is(timestamp));
        assertThat(dtoArgument.getInformation(), hasSize(2));
        assertThat(dtoArgument.getInformation().get(0).getIndex(), is(1L));
        assertThat(dtoArgument.getInformation().get(0).getData(), is("data_1"));
        assertThat(dtoArgument.getInformation().get(0).getName(), is("name_1"));
    }
}
