package com.it.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.it.domain.Message;
/****
 * 管理消息类
 * @author Alex
 *
 */
public class MessagesManager extends BaseDAO {

	public MessagesManager() {
		// TODO Auto-generated constructor stub
	}

	
	/*************单例模式****************/
	private static  MessagesManager messagesManager=new MessagesManager();
	
	public static MessagesManager getMessagesManager() {
		return messagesManager;
	}
/*************单例模式****************/
	
	/***
	 * 获得所有消息
	 * @return
	 */
	public List<Message> getallMessagelist() {
		// TODO Auto-generated method stub
		String sql="select * from message";
		return super.query(sql, Message.class);
	}



/***
 * 分页显示所有消息
 * @param start
 * @param count
 * @return
 */
	public List<Message> getlimitMessagelist(int start, int count) {
		// TODO Auto-generated method stub
		String sql="select * from message limit " + start + "," + count;
		return super.query(sql, Message.class);
	}

	

	/***
	 * 根据消息ID查询消息
	 * @param id
	 * @return
	 */
	public Message getMessageById(long id) {
		String sql="select * from message where id ="+ id;
		
		return (Message) super.get(sql, Message.class);
	}

	/****
	 * 更新消息的状态 是否已读
	 * @param id
	 * @param state
	 * @return
	 */
	public boolean update(long id,int state ) {
		
		String sql="UPDATE  message set state = "+state+" where id ="+ id;
		return  super.update(sql);
	
	}

	/****
	 * 获取用户的所有消息
	 * @param userid
	 * @return
	 */
	public List<Message> getMessageListByuserid(long userid) {

		String sql="select * from message  where receiverid = "+userid;
	
		return super.query(sql,Message.class);
	}
	/***
	 * 分页查询用户的消息
	 * @param start
	 * @param count
	 * @param userid
	 * @return
	 */
	public List<Message> getlimitMessagelist(int start, int count,long userid) {
		String sql="select * from message  where receiverid = "+userid+" limit " + start + "," + count;
	
		
		return super.query(sql, Message.class);
	}

	
	
	/***
	 * 添加一条以管理员发送的消息
	 * @param title
	 * @param context
	 * @param receiverid
	 */
	public void addMessage(String title, String context, long receiverid){
		String sql = "insert into message value"
				+ "("
				+ new Date().getTime()
				+ ",'"
				+ title
				+ "','"
				+ context
				+ "', '"
				+ new Timestamp(System.currentTimeMillis())
				.toString()+ "' , '快易购电子商城 '"
				+ " ," +receiverid +","+ 0+")";
		
		super.update(sql);
	}
	
	
}
