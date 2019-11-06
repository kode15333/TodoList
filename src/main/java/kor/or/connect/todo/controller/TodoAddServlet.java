package kor.or.connect.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kor.or.connect.todo.dao.TodoDao;
import kor.or.connect.todo.dto.TodoDto;

@WebServlet("/addtodo")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ENCODING_CHAR = "UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding(ENCODING_CHAR);
		
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		TodoDto dto = new TodoDto();
		dto.setName(name);
		dto.setTitle(title);
		dto.setSequence(sequence);
		
		TodoDao dao = new TodoDao();
		
		dao.addTodo(dto);

		response.sendRedirect("/todo/todolist");

	}

}
