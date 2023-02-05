package cn.com.hyun.framework.web.util.verifycode;


/**
 * Created by hyunwoo
 */
public final class NumberVerifyCode implements VerifyCode {

    private static char[] sequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Override
    public char[] getCharSequence() {
        return sequence;
    }
}
