<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		fnBoardDelete();
	});
	function fnBoardDelete(){
		if(confirm('게시글을 삭제할까요?')){
			location.href='delete.do?no=${board.no}';
			}
		}
	</script>
</head>
<body>
	<div>
		<h3>${board.no}번 게시글</h3><br>
		작성자 : ${board.author}<br>
		작성일 : ${board.postdate}<br>
		작성IP : ${board.ip}<br>
		조회수 : ${board.hit}<br>
		제목 :<br>
		<input type="text" name="title" value=" ${board.title}"><br>
		내용 <br>
		<textarea rows="5" cols="30">${board.content}</textarea>
		<br>
		<input type="button" value="삭제하기" onclick="fnBoardDelete()">

		<input type="button" value="목록보기" onclick="location.href='boardList.do'">
	</div>
	<hr>
	<form action="insertReply.do" method="post">	
		<textarea rows="2" cols="30" placeholder="댓글을 입력하세요" name="content">${reply.content}</textarea><br>
		<input type="text" name="author" placeholder="작성자"><button>작성</button>
		<input type="hidden" name="no" value="${reply.no}">
	</form>
	<table>
		<tbody>
			<c:forEach items="${replyList}" var="reply">
				<tr>
					<td>${reply.content}</td>
					<td>${reply.author}</td>
					<td>${reply.ip}</td>
					<td>${reply.postdate}</td>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>