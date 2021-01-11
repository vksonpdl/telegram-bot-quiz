package com.vksonpdl.qstnbnk.session.obj;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.service.CredentialService;

@Component
public class ChatStore {

	private static final Map<Long, ChatSession> CONVERSATION_OBJECT = new HashMap<Long, ChatSession>();

	@Autowired
	CredentialService credentialService;

	private void addToChatStore(Long chatId) {
		CONVERSATION_OBJECT.put(chatId, new ChatSession());
	}
	
	public void startNewSession(Long chatId,String telId) {
		if (credentialService.isCredentialExist(telId)) {
			this.removeFromChatStore(chatId);
			this.addToChatStore(chatId);
		}
		
	}

	private void removeFromChatStore(Long chatId) {
		CONVERSATION_OBJECT.remove(chatId);
	}
	
	private ChatSession getFromChatStore(Long chatId) {
		return CONVERSATION_OBJECT.get(chatId);
	
	}

	public boolean isQuizSessionExists(Long chatId, String telId) {
		boolean returnStatus = false;
		QuizStatus session = CONVERSATION_OBJECT.get(chatId).getQuizStatus();
		if ((new Date().getTime() - session.getSessionStartTime()) <= session.getValidity()) {
			returnStatus = true;

		} else  {
			this.startNewSession(chatId,telId);
			returnStatus = false;
		}
		
		return returnStatus;
	}
	public boolean isSessionorUserExist(Long chatId, String telId) {

		boolean returnStatus = false;

		if (CONVERSATION_OBJECT.keySet().contains(chatId)) {
			ChatSession session = CONVERSATION_OBJECT.get(chatId);
			if ((new Date().getTime() - session.getSessionStartTime()) <= session.getValidity()) {
				returnStatus = true;

			} else  {
				this.startNewSession(chatId,telId);
				returnStatus = true;
			}

		} else if (credentialService.isCredentialExist(telId)) {
			this.addToChatStore(chatId);
			returnStatus = true;
		}

		return returnStatus;

	}
	
	public void updateSessionWithQuizStatus(Long chatId) {
		QuizStatus quizStatus = new QuizStatus();
		quizStatus.setStartQuiz(true);
		this.getFromChatStore(chatId).setQuizStatus(quizStatus);
	}
	
	public boolean isReadyToUpdateQuestiontype(Long chatId) {
		boolean readyToUpdateQuestiontype = false;
		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();
		if(null!=quizStatus && quizStatus.isStartQuiz() && null==quizStatus.getQuizType()) {
			readyToUpdateQuestiontype = true;
		}
		return readyToUpdateQuestiontype;
	}
	public void updateSessionWithQuizType(Long chatId,String quizType) {
		
		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();
		quizStatus.setQuizType(quizType.replace("/", ""));
		quizStatus.setSessionStartTime(new Date().getTime());
		
		this.getFromChatStore(chatId).setQuizStatus(quizStatus);
	}
	
	public boolean isReadyToUpdateAnswer(Long chatId,String telId) {
		boolean readyToUpdateQuestiontype = false;
		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();
		if(quizStatus.getCurrentQId()>0 && this.isQuizSessionExists(chatId, telId)) {
			readyToUpdateQuestiontype = true;
		}
		return readyToUpdateQuestiontype;
	}
}
