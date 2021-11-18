package model;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

public class BoardViewService implements BoardService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		Long no = Long.parseLong(opt.orElse("0"));
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("open") == null) {
			session.setAttribute("open", true);
			BoardDAO.getInstance().updateBoardHit(no);
		}
		
		BoardDTO board = BoardDAO.getInstance().selectBoardView(no);
		session.setAttribute("board", board);
		if(board != null) {
			List<ReplyDTO> replyList = BoardDAO.getInstance().selectReplyList(no);
			request.setAttribute("replyList", replyList);
			request.setAttribute("board", board);
			return new ModelAndView("views/reply.jsp", false);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일치하는 공지사항이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
