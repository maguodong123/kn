package cn.kn.utility.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult<Void> handle(Exception e) {//handle:处理
        if (e instanceof CustomException) {//判断该异常对象是否是由自定义异常创建？
            CustomException customException = (CustomException) e;
            return ResultUtil.error(customException.getCode(), customException.getMessage());
        } else {
            logger.error("【系统异常】", e);
            return ResultUtil.error(-1, "服务器系统未知错误");
        }
    }

}
