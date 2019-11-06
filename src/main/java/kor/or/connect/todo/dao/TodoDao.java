package kor.or.connect.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kor.or.connect.todo.dto.TodoDto;

public class TodoDao {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false&characterEncoding=utf8";
	private static final String DB_USER = "connectuser";
	private static final String DB_PASSWORD = "connect123!@#";
	
	private static final String INSERT_SQL = " INSERT INTO todo (title, name, sequence) VALUES(?, ?, ?) ";
	private static final String SELECT_SQL = " SELECT id,name, regdate, sequence, title, type  FROM todo order by regdate";
	private static final String UPDATE_SQL = " UPDATE todo set type = ? WHERE id = ? ";

	public int addTodo(TodoDto dto) {

		int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());

			insertCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertCount;
	}

	public List<TodoDto> getTodos() {

		List<TodoDto> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_SQL);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String regdate = rs.getString(3);
				int sequence = rs.getInt(4);
				String title = rs.getString(5);
				String type = rs.getString(6);

				TodoDto dto = new TodoDto();
				dto.setId(id);
				dto.setName(name);
				dto.setRegdate(regdate);
				dto.setSequence(sequence);
				dto.setTitle(title);
				dto.setType(type);

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

	public int updateToDo(TodoDto dto) {

		int updateCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, dto.getType());
			ps.setLong(2, dto.getId());
			updateCount = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return updateCount;
	}

}
