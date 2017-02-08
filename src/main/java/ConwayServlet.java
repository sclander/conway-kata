import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ConwayServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("app/conway.html");
        view.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(BufferedReader reader = request.getReader()) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            GridData current = jsonToGridData(sb.toString());
            GridData next = nextRound(current);

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gridDataToJson(next));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methods below this line would normally be private/protected but I am making them
    // public to allow for more useful tests than mocks returning mocks.

    public GridData jsonToGridData(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, GridData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new GridData();
        }
    }

    public String gridDataToJson(GridData data) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public GridData nextRound(GridData current) {
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
