package life.majiang.community.exception;

public enum CustomizeErrorCode implements  ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在了"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后再进行操作"),
    SYS_ERROR(2004,"服务器出现问题，稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"你找的评论不存在");

    private Integer code;
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String message ;

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code=code;
    }
}
