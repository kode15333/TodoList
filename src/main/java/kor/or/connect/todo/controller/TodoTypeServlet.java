package kor.or.connect.todo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kor.or.connect.todo.dao.TodoDao;
import kor.or.connect.todo.dto.TodoDto;

@WebServlet("/todoType")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = null;
		TodoDto dto = new TodoDto();
		TodoDao dao = new TodoDao();

		long id = Long.parseLong(request.getParameter("id"));
		String originType = request.getParameter("type");

		if (originType.equals("TODO")) {
			type = "DOING";
		} else if (originType.equals("DOING")) {
			type = "DONE";
		}

		dto.setId(id);
		dto.setType(type);

		dao.updateToDo(dto);

		String json = objectMapper.writeValueAsString(dto);

		PrintWriter out = response.getWriter();
		out.println(json);
		out.close();

	}
}
