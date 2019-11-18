package cn.kn.utility.exceptionhandling;
/**
 * 消息结果返回处理工具类
 */
public class ResultUtil {
	private static JsonResult<Void> result = new JsonResult<>();
	
	/**
	 * 错误异常消息
	 * @param code 错误码
	 * @param msg 错误信息
	 * @return
	 */
	public static JsonResult<Void> error(Integer code, String msg) {
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
}
