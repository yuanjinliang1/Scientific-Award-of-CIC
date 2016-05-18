package com.dicipulus.app.ajax;

import java.util.List;

import com.dicipulus.app.model.Application;
import com.fasterxml.jackson.annotation.JsonView;

public class AjaxResponseBody {

	@JsonView(AjaxViews.Public.class)
	String msg;

	@JsonView(AjaxViews.Public.class)
	String code;

	@JsonView(AjaxViews.Public.class)
	Application result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Application getResult() {
		return result;
	}

	public void setResult(Application result) {
		this.result = result;
	}
	
}
