package com.zbmatsu.iam.context;

import javax.servlet.http.HttpServletRequest;

import com.zbmatsu.iam.common.ErrorMessage;
import com.zbmatsu.iam.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 自定义异常处理类
 * 针对不同的异常自定义不同的方法
 * 环绕通知
 * 切面:针对所有的controller中抛出的异常
 * 若使用@ControllerAdvice,则不会自动转换为JSON格式
 */
@RestControllerAdvice
public class CustomExceptionHandler {

	/**
	 * 只要抛出该类型异常,则由此方法处理
	 * 并由此方法响应出异常状态码及消息
	 * 如:RoleController.getRoleById(String id)方法
	 * @param request
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<ErrorMessage> handleNotFound(HttpServletRequest request, NotFoundException exception) throws Exception {

		ErrorMessage body = new ErrorMessage();
		body.setCode(HttpStatus.NOT_FOUND.toString());
		body.setMessage(exception.getMessage());

		ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	/**
	 * 参数校验异常处理
	 * @param request
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception {

		ErrorMessage body = new ErrorMessage();
		body.setCode(HttpStatus.BAD_REQUEST.toString());
		//按需重新封装需要返回的错误信息
		List<String> invalidArguments = new ArrayList<>();
		//解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			invalidArguments.add(error.getDefaultMessage());
		}
		body.setMessage(invalidArguments.toString());
		ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}

	@ExceptionHandler(value = TimeoutException.class)
	public ResponseEntity<ErrorMessage> handleTimeout(HttpServletRequest request, TimeoutException exception) throws Exception {

		ErrorMessage body = new ErrorMessage();
		body.setCode(HttpStatus.REQUEST_TIMEOUT.toString());
		body.setMessage(exception.getMessage());

		ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(body, HttpStatus.REQUEST_TIMEOUT);
		return responseEntity;
	}

	@ExceptionHandler(value = {
			NullPointerException.class
	})
	public ResponseEntity<ErrorMessage> handleNullPointer(HttpServletRequest request, Exception exception) throws Exception {

		ErrorMessage body = new ErrorMessage();
		body.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		body.setMessage(exception.getMessage());

		ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}

	@ExceptionHandler(value = {
			HttpMessageNotReadableException.class
	})
	public ResponseEntity<ErrorMessage> handleHttpMessageNotReadable(HttpServletRequest request, HttpMessageNotReadableException exception) throws Exception {

		ErrorMessage body = new ErrorMessage();
		body.setCode(HttpStatus.BAD_REQUEST.toString());
		body.setMessage(exception.getMessage());

		ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
}
