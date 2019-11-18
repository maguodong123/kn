package cn.kn.utility.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonResult<T> {
	private Integer code;// 错误码
	private String msg;// 提示信息
	private T data;// 具体内容

	public JsonResult() {
		super();
	}

	public JsonResult(ResultEnum resultEnum) {
		super();
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	public JsonResult(ResultEnum resultEnum, T data) {
		super();
		this.data = data;
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
