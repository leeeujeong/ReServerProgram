package model;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class InsertReplyService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		Long no = Long.parseLong(request.getParameter("no"));
		String ip = request.getRemoteAddr();
		Date postdate = request.getParameter("postdate");
		
		ReplyDTO reply = new ReplyDTO();
		reply.setContent(content);
		reply.setAuthor(author);
		reply.setNo(no);
		reply.setIp(ip);
		reply.setPostdate(postdate);
		
		int result = BoardDAO.getInstance().insertReply(reply);
		
		PrintWriter out = response.getWriter();
		if(result >0) {
			out.println("<script>");
			out.println("alert('댓글이 등록되었습니다.')");
			out.println("</script>");
			out.close();
			return new ModelAndView("view.board?no=?" + no ,true);
			
		}else {
			out.println("<script>");
			out.println("alert('댓글이 등록되지 않았습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}
