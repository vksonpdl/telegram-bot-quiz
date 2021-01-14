package com.vksonpdl.qstnbnk.session.obj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vksonpdl.qstnbnk.model.TriviaQuestion;
import com.vksonpdl.qstnbnk.service.CredentialService;
import com.vksonpdl.qstnbnk.service.TriviaService;


@Component
public class ChatStore {

	private static final Map<Long, ChatSession> CONVERSATION_OBJECT = new HashMap<Long, ChatSession>();

	@Autowired
	CredentialService credentialService;
	
	@Autowired
	TriviaService triviaService;
	

	private void addToChatStore(Long chatId) {
		ChatSession chatSession =new ChatSession();
		chatSession.setTriviaToken(triviaService.getTriviaToken());;
		
		CONVERSATION_OBJECT.put(chatId, chatSession);
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
		if (null!=session && (new Date().getTime() - session.getSessionStartTime()) <= session.getValidity()) {
			returnStatus = true;

		} else  {
			this.startNewSession(chatId,telId);
			returnStatus = false;
		}
		
		return returnStatus;
	}
	public ChatSession getSession(Long chatId, String telId) {


		ChatSession session =null;

		if (CONVERSATION_OBJECT.keySet().contains(chatId)) {
			ChatSession sessionfromObj = CONVERSATION_OBJECT.get(chatId);
			if ((new Date().getTime() - sessionfromObj.getSessionStartTime()) <= sessionfromObj.getValidity()) {
				session = sessionfromObj;

			} else if(null!=telId){
				this.startNewSession(chatId,telId);
				session= this.getFromChatStore(chatId);
			}

		} else if (credentialService.isCredentialExist(telId)) {
			this.addToChatStore(chatId);
			session= this.getFromChatStore(chatId);
		}

		return session;

	}
	
	public void updateSessionWithQuizStatus(Long chatId) {
		QuizStatus quizStatus = new QuizStatus();
		quizStatus.setStartQuiz(true);
		this.getFromChatStore(chatId).setQuizStatus(quizStatus);
	}
	
	public boolean isReadyToUpdateQuestiontype(Long chatId) {
		boolean readyToUpdateQuestiontype = false;
		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();
		if(this.isQuizSessionExists(chatId, null) && null!=quizStatus && quizStatus.isStartQuiz() && null==quizStatus.getQuizType()) {
			readyToUpdateQuestiontype = true;
		}
		return readyToUpdateQuestiontype;
	}
	
	public void updateSessionWithQuizTypeAndQuestions(Long chatId, String quizType,
			List<TriviaQuestion> triviaQuestion) {

		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();

		quizStatus.setQuizType(quizType.replace("/", ""));
		quizStatus.setTriviaQuestion(triviaQuestion);
		quizStatus.setSessionStartTime(new Date().getTime());

		this.getFromChatStore(chatId).setQuizStatus(quizStatus);
	}
	

	
	public boolean isReadyToUpdateAnswer(Long chatId,String telId) {
		boolean readyToUpdateQuestiontype = false;
		QuizStatus quizStatus = this.getFromChatStore(chatId).getQuizStatus();
		if(this.isQuizSessionExists(chatId, null) && quizStatus.getCurrentQId()>0 && this.isQuizSessionExists(chatId, telId)) {
			readyToUpdateQuestiontype = true;
		}
		return readyToUpdateQuestiontype;
	}
	

}
