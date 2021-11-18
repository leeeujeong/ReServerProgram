<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>게시글 작성하기</h3>
	
	<form action=insert.do method="get">
		<label for="author">작성자</label><br>
		<input type="text" name="author" id="author">
		<br>
		<label for="title">제목</label><br>
		<input type="text" name="title" id="title">
		<br>
		내용<br>
		<textarea rows="5" cols="30" name="content"></textarea><br>
		<button>저장하기</button>
		<input type="reset" value="작성 초기화">
		<input type="button" value="목록보기" onclick="location.href='boardList.do'">
	</form>
</body>
</html>