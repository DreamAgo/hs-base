package cn.loverot.common.exception;

/**
 * 内部异常类
 *
 * @author huise
 */
public class HsException extends RuntimeException  {

    private static final long serialVersionUID = -857186558942275263L;

    public HsException(String message) {
        super(message);
    }
}
