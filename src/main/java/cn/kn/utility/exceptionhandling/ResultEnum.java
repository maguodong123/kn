package cn.kn.utility.exceptionhandling;

public enum ResultEnum {
	EmptySet(1,"集合为空!下标越界"),
	QuantityError(2,"返回值与预期数量不符!"),

	InsertError(90,"插入数据时,发生未知错误,请于管理员联系!"),
	UpdateError(91,"数据修改时,发生未知错误,请于管理员联系!"),
	DeleteError(92,"删除数据时,发生未知异常,请于管理员联系!"),
	DataError(93,"数据库数据异常");
	
	private Integer code;//错误码
	private String msg;//提示信息

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
