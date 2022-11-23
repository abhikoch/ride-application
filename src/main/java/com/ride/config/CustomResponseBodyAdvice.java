package com.ride.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ride.controller.RequestController;
import com.ride.model.PaginationModel;
import com.ride.model.ResponseModel;

/**
 * @author Kochhar, Abhinav
 *
 */
@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private static List<Class<?>> supportingControllerClasses = Arrays.asList(RequestController.class);
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return supportingControllerClasses.contains(returnType.getContainingClass());
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof ResponseEntity<?> || body instanceof ResponseModel) {
			return body;
		}
		if(request.getMethod() == HttpMethod.POST) {
			response.setStatusCode(HttpStatus.OK);
		}
		ResponseModel returnResponse = createResponseModel(body);
		return body instanceof String? asJsonString(returnResponse) : returnResponse;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private ResponseModel createResponseModel(Object body) {
		Long count = null;
		if(body instanceof PaginationModel<?>) {
			PaginationModel<?> page = (PaginationModel<?>) body;
			count = page.getCount();
			body = page.getValues();
		}
		
		return ResponseModel.builder()
				.apiVersion(1D)
				//.requestedBy("Requestor")
				.requestedTime(LocalDateTime.now())
				.totalRecords(count)
				.status(ResponseModel.Status.SUCCESS)
				.data(body)
				.build();
	}
}