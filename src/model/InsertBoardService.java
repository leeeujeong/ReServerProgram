package model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertBoardService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		BoardDTO board = new BoardDTO();
		board.setAuthor(author);
		board.setTitle(title);
		board.setContent(content);
		board.setIp(ip);
		
		int result = BoardDAO.getInstance().insertBoard(board);
		
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='boardList.do'");
			out.println("</script>");
			out.close();
		}else {
			out.println("<script>");
			out.println("alert('게시글 등록이 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}

}
