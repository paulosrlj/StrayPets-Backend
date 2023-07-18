package com.paulosrlj.straypets.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {

	private Integer status;
	private String detail;
	private String title;
	private String type;
	
	private String userMessage;
	private LocalDateTime timestamp;
	
	private List<Object> objects;
	
	@Getter
	@Builder
	public static class Object {
		
		private String name;
		private String userMessage;
		
	}
}
