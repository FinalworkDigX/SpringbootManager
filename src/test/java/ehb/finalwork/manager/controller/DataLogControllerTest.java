package ehb.finalwork.manager.controller;

import ehb.finalwork.manager.model.DataLog;
import ehb.finalwork.manager.model.Information;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.toIntExact;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DataLogController.class)
public class DataLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataLogController dataLogController;

    @Test
    public void getDataLogsTest() throws Exception {

        List<Information> infoList = new ArrayList<Information>();
        infoList.add(new Information("name_1", "data_1", 1L));
        infoList.add(new Information("name_2", "data_2", 2L));

        Long epoch = Instant.now().getEpochSecond();

        DataLog dl1 = new DataLog("111", "item_id_1", infoList, epoch);
        DataLog dl2 = new DataLog("222", "item_id_2", infoList, epoch);

        List<DataLog> allDataLogs = new ArrayList<DataLog>();
        allDataLogs.add(dl1);
        allDataLogs.add(dl2);

        when(dataLogController.getDataLogs()).thenReturn(allDataLogs);

        mockMvc.perform(get("/v1/dataLog"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is("111")))
                .andExpect(jsonPath("$[0].item_id", is("item_id_1")))
                .andExpect(jsonPath("$[0].information", hasSize(2)))
                .andExpect(jsonPath("$[0].timestamp", is(toIntExact(epoch))));

        verify(dataLogController, times(1)).getDataLogs();
        verifyNoMoreInteractions(dataLogController);
    }
}
