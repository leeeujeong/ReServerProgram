<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="insertForm.do">새글작성</a>
<div>
	전체 게시글 :${totalCount}개
	
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="board" >
				<tr>
					<td>${board.no}</td>
					<td><a href="view.do?no=${board.no}">${board.title}</a></td>
					<td>${board.author}</td>
					<td>${board.postdate}</td>
					<td>${board.hit}</td>
			</c:forEach>
		</tbody>
	</table>
</div>
 
</body>
</html>