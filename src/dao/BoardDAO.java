package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import dto.ReplyDTO;
import mybatis.config.DBService;

public class BoardDAO {

	private SqlSessionFactory factory;
	
	private static BoardDAO instance;
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	//board
	
	public List<BoardDTO> selectBoardList(){
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("dao.board.selectBoardList");
		ss.close();
		return list;
	}
	public BoardDTO selectBoardView(Long no) {
		SqlSession ss = factory.openSession();
		BoardDTO board = ss.selectOne("dao.board.selectBoardView",no);
		ss.close();
		return  board;
	}
	public int updateBoardHit(Long no) {
		SqlSession ss = factory.openSession();
		int result = ss.update("dao.board.updateBoardHit", no);
		if(result >0)ss.commit();
		ss.close();
		return result;
	}
	public int insertBoard(BoardDTO board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertBoard", board);
		if(result>0) ss.commit();
		ss.close();
		return result;
	}
	
	public int deleteBoard(Long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.board.deleteBoard", no);
		if(result >0) ss.commit();
		ss.close();
		return result;
	}
	
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.board.getTotalCount");
		ss.close();
		return totalCount;
	}
	//reply
	
	public int insertReply(ReplyDTO reply) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertReply", reply);
		if(result >0) ss.commit();
		ss.close();
		return result;
	}
	public List<ReplyDTO> selectReplyList(Long no){
		SqlSession ss = factory.openSession();
		List<ReplyDTO> list = ss.selectList("dao.baord.selectReplyList", no);
		ss.close();
		return list;
	}
	
	//batch
	
	public BoardDTO selectMaxHit() {
		SqlSession ss = factory.openSession();
		BoardDTO maxhit = ss.selectOne("dao.baord.selectMaxHit");
		ss.close();
		return maxhit;
	}

	
}