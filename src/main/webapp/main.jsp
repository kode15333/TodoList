<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html id="root">
<head>
<meta charset="UTF-8">
<title>TodoList</title>
<link rel="stylesheet" href="./css/main.css">
<script type="text/javascript">
    function switchType(a, b) {
        var url = "todoType?id=" + a + "&type=" + b
        var httpRequest = new XMLHttpRequest();
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                var res = JSON.parse(httpRequest.response)
                var id = res.id;
                var type = res.type;
                var content = document.getElementById(id).innerHTML;
                var li = document.createElement("li");
                var target = document.getElementById(id);
                
                li.innerHTML = content;
                li.setAttribute("id", id);
                li.setAttribute("class", "todoContent");

                if (type === "DOING") {
                    li.getElementsByTagName("input")[0].setAttribute("onclick",    "switchType('" + id + "'," + "'DOING')")
                    document.getElementById("DOING").appendChild(li);
                    document.getElementById("TODO").removeChild(target);
                } else if (type === "DONE") {
                    li.removeChild(li.getElementsByTagName("input")[0]);
                    document.getElementById("DONE").appendChild(li);
                    document.getElementById("DOING").removeChild(target);
                }
            }
        };
        httpRequest.open("GET", url, true);
        httpRequest.send();
    }
</script>

</head>

<body>
    <header class="header">
        <div class="headerleft">나의 해야할 일들</div>
        <input type="button" class="addBtn" value="새로운 TODO 등록"
            onclick="location.href='todoform'">
    </header>
    <section class="content">
        <ul id="TODO" class="typeTodo">
            <li class="todoTop"><h3>TODO</h3></li>
            <c:forEach items="${todoList }" var="todoDto">
                <c:if test="${todoDto.type == 'TODO' }">
                    <li class="todoContent" id="${todoDto.id }">
                        <h3 class="todoTitle">${todoDto.title }</h3>
                        <span class="todotab">
                        등록날짜:<fmt:parseDate value="${todoDto.regdate}" var="issueDate"
                                pattern="yyyy-mm-dd" /> <fmt:formatDate value="${issueDate }"
                                pattern="yyyy.mm.dd" /> , ${todoDto.name }, 우선순위
                            ${todoDto.sequence}
                    </span> <input class="changeBtn" type="button" value="→"
                        onclick="switchType('${todoDto.id}', '${todoDto.type}');" />
                    </li>
                </c:if>
            </c:forEach>
        </ul>
        <ul id="DOING" class="typeTodo">
            <li class="todoTop"><h3>DOING</h3></li>
            <c:forEach items="${todoList }" var="todoDto">
                <c:if test="${todoDto.type == 'DOING' }">
                    <li class="todoContent" id="${todoDto.id }">
                        <h3 class="todoTitle">${todoDto.title }</h3> <span class="todotab">등록날짜:
                            <fmt:parseDate value="${todoDto.regdate}" var="issueDate"
                                pattern="yyyy-mm-dd" /> <fmt:formatDate value="${issueDate }"
                                pattern="yyyy.mm.dd" /> , ${todoDto.name }, 우선순위
                            ${todoDto.sequence}
                    </span> <input class="changeBtn" type="button" value="→"
                        onclick="switchType('${todoDto.id}' , '${todoDto.type}');" />
                    </li>
                </c:if>
            </c:forEach>
        </ul>
        <ul id="DONE" class="typeTodo">
            <li class="todoTop"><h3>DONE</h3></li>
            <c:forEach items="${todoList }" var="todoDto">
                <c:if test="${todoDto.type == 'DONE' }">
                    <li class="todoContent" id="${todoDto.id }">
                        <h3 class="todoTitle">${todoDto.title }</h3> <span class="todotab">등록날짜:
                            <fmt:parseDate value="${todoDto.regdate}" var="issueDate"
                                pattern="yyyy-mm-dd" /> <fmt:formatDate value="${issueDate }"
                                pattern="yyyy.mm.dd" /> , ${todoDto.name }, 우선순위
                            ${todoDto.sequence}
                    </span>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </section>
</body>
</html>