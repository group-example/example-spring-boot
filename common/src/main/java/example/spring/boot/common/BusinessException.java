package example.spring.boot.common;


/**
 *
 */
public class BusinessException extends Exception {

	/** 异常代码 */
	private String code ;
	/** 异常消息 */
	private String message ;

	public BusinessException(String code, String message){
		super(message);
		this.code=code;
	}
	public BusinessException(String message){
		super(message);

	}

}
