package cn.xj.common.exception;

/**
 * Created by Welink on 2017/4/26.
 */
public class SessionTimeoutException extends Exception {

    public SessionTimeoutException() {
    }

    public SessionTimeoutException(String message) {
        super(message);
    }

}
