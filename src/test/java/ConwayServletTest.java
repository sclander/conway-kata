import static org.junit.Assert.*;
import org.junit.Test;

public class ConwayServletTest {
    private static final String TEST_POST_DATA = "{\"numRows\":6,\"numCols\":8,\"rows\":[[false,true,false,false,false,false,false,false],[false,true,false,false,false,true,true,true],[false,true,false,false,false,false,false,false],[false,false,false,false,false,false,false,false],[false,false,false,true,true,false,false,false],[false,false,false,true,true,false,false,false]]}";
    private static final String TEST_POST_RESPONSE = "{\"numRows\":6,\"numCols\":8,\"rows\":[[false,false,false,false,false,false,true,false],[true,true,true,false,false,false,true,false],[false,false,false,false,false,false,true,false],[false,false,false,false,false,false,false,false],[false,false,false,true,true,false,false,false],[false,false,false,true,true,false,false,false]]}";
    private static final GridData EMPTY_GRID = new GridData(10, 20);
    private static final String EMPTY_GRID_JSON = "{\"numRows\":10,\"numCols\":20,\"rows\":[[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false]]}";

    private ConwayServlet servlet = new ConwayServlet();

    @Test
    public void grid_data_matches_json_data() {
        GridData grid = servlet.jsonToGridData(TEST_POST_DATA);

        assertEquals(6, grid.getNumRows());
        assertEquals(8, grid.getNumCols());
        assertEquals(false, grid.getRows().get(0).get(0));
        assertEquals(true, grid.getCellValue(0, 1));
    }

    @Test
    public void json_data_matches_grid_data() {
        String jsonStr = servlet.gridDataToJson(EMPTY_GRID);
        System.out.println(jsonStr);
        assertEquals(EMPTY_GRID_JSON, jsonStr);
    }

    @Test
    public void next_round_is_correct() {
        GridData current = servlet.jsonToGridData(TEST_POST_DATA);
        GridData next = servlet.nextRound(current);

        assertEquals(TEST_POST_RESPONSE, servlet.gridDataToJson(next));
    }
}
