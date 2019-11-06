<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TodoForm</title>
<link rel="stylesheet" href="./css/todoForm.css">
</head>
<body>
	<form class="addForm" action="addtodo" method="post">
		<h1 class="formHead">할일 등록</h1>
		<table class="addTable">
			<tr>
				<td class="tableTD">어떤일인가요?</td>
			</tr>
			<tr>
				<td class="tableTR"><input class="titleInput"
					placeholder="swift 공부하기(24자까지)" type="text" name="title"
					maxlength="24" required="required"></td>
			</tr>
			<tr>
				<td class="tableTD">누가 할일인가요?</td>
			</tr>
			<tr>
				<td class="tableTR"><input class="nameInput" placeholder="홍길동"
					type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<td class="tableTD">우선순위를 선택해주세요</td>
			</tr>
			<tr>
				<td class="tableTR"><input type="radio" value="1"
					name="sequence" required="required">1순위 <input
					class="tableRadio" type="radio" value="2" name="sequence"
					required="required">2순위 <input class="tableRadio"
					type="radio" value="3" name="sequence" required="required">3순위
				</td>
			</tr>
			<tr class="tableTR">
				<td><span><input class="formLeftBtn" value="<이전"
						onclick="location.href='todolist'"></span> <span
					class="formRightBtn"><input class="formBtn" type="submit"
						value="제출"> <input class="formBtn" type="reset"
						value="내용지우기"></span></td>
			</tr>
		</table>

	</form>
</body>
</html>