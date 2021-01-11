package com.vksonpdl.qstnbnk.cntrlr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vksonpdl.qstnbnk.model.CommonResponseModel;
import com.vksonpdl.qstnbnk.model.QuestionModel;
import com.vksonpdl.qstnbnk.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@GetMapping("/count")
	public ResponseEntity<CommonResponseModel> getQuestionCount(){
		
		ResponseEntity<CommonResponseModel> response ;
		
		CommonResponseModel responseModel = new CommonResponseModel();
		
		responseModel.setStatus("success");
		responseModel.setStatusDescription("success");
		
		QuestionModel question = new QuestionModel();
		question.setQuestionsCount(questionService.getQuestionsCount());
		responseModel.setQuestion(question);
		response = new ResponseEntity<CommonResponseModel>(responseModel,HttpStatus.OK);
		
		return response;
		
	}
}
