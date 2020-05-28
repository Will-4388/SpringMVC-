package top.undefinded.exception;

/**
 * @author will
 * @date 2020/5/24
 */
public class SysException extends Exception{
    // 异常提示信息
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public SysException(String message) {
        this.message = message;
    }
}
