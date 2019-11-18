package cn.kn.utility.exceptionhandling;
/**
 * 自定义统一抛出异常异常：
 */
public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;

	public CustomException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
	}
	public CustomException(String message) {
		super(message);
		this.msg=message;
	}

	public String getMsg() {
		return msg;
	}
	public Integer getCode() {
		return code;
	}
}
