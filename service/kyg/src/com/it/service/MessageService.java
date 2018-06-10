package com.it.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.Util.FastjsonTools;
import com.it.configs.ConfigValues;
import com.it.dao.MessagesManager;
import com.it.domain.Message;

/***
 * 消息服务类
 * 
 * @author Alex
 * 
 */
public class MessageService {

	public MessageService() {

	}

	
	/****************** 单例模式 *******************/
	private static MessageService messageService = new MessageService();

	public  static MessageService getMessageService() {
		return messageService;
	}

	/****************** 单例模式 *******************/

	/***
	 * 根据用户ID获取消息集合
	 * @param userid
	 * @return
	 */
	public String getMessageByUserID(String userid) {
		
		List<Message> mess = MessagesManager.getMessagesManager().getMessageListByuserid(Long.parseLong(userid));
         //包装数据
		Map<String, Object> mapJson=new HashMap<String, Object>();
		if (mess != null&& !mess.isEmpty()) {
			mapJson.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_SUCCESS);
			mapJson.put("messages", mess);
		}else {
			mapJson.put(ConfigValues.RESULT_KEY, ConfigValues.RESPONSE_ERROR);
		}
		return FastjsonTools.CreategsonString(mapJson);
    }
	
	
	/***
	 * 发送一条以管理员名义发出消息
	 * @param title
	 * @param context
	 * @param receiverid
	 */
	public void sendMessageToUser(String title, String context, long receiverid){
		MessagesManager.getMessagesManager().addMessage(title, context, receiverid);
	}
	

}
