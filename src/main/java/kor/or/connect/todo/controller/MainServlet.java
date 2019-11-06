package kor.or.connect.todo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kor.or.connect.todo.dao.TodoDao;
import kor.or.connect.todo.dto.TodoDto;

@WebServlet("/todolist")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ENCODING_CHAR = "UTF-8";
	public static final String ENCODING_FORMAT = "text/html; charset=UTF-8";
	private TodoDao dao = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding(ENCODING_CHAR);
		response.setContentType(ENCODING_FORMAT);

		List<TodoDto> todoList = dao.getTodos();

		request.setAttribute("todoList", todoList);

		dispatch(request, response, "main.jsp");

	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
