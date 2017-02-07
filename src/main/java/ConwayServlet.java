import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConwayServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("app/conway.html");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside do POST");

        try(BufferedReader reader = request.getReader()) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            GridData current = mapData(sb.toString());
            GridData next = nextRound(current);

            System.out.println(next.getRows());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("over");
    }

    private GridData mapData(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, GridData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new GridData();
        }
    }

    private GridData nextRound(GridData current) {
        int numRows = current.getNumRows();
        int numCols = current.getNumCols();
        GridData next = new GridData(numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int startRow, endRow, startCol, endCol;
                startRow = (i == 0) ? 0 : i - 1;
                endRow = (i == numRows - 1) ? numRows - 1 : i + 1;
                startCol = (j == 0) ? 0 : j - 1;
                endCol = (j == numCols - 1) ? numCols - 1 : j + 1;
                next.setCellValue(i, j, isAlive(current, i, j, startRow, endRow, startCol, endCol));
            }
        }

        return next;
    }

    private boolean isAlive(GridData current, int row, int col, int startRow, int endRow, int startCol, int endCol) {
        int livingNeighbors = 0;
        boolean isAlive = false;

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j ++) {
                if (livingNeighbors > 3) {
                    break;
                }
                else if (current.getCellValue(i, j) && !(i == row && j == col)) {
                    livingNeighbors++;
                }
            }
        }

        if (livingNeighbors == 2 && current.getCellValue(row, col) || livingNeighbors == 3) {
            isAlive = true;
        }
        
        return isAlive;
    }
}
