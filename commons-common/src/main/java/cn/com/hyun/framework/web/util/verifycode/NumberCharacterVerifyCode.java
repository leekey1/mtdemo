package cn.com.hyun.framework.web.util.verifycode;

/**
 * Created by hyunwoo
 */
public final class NumberCharacterVerifyCode implements VerifyCode {

    private static char[] sequence = {'2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public char[] getCharSequence() {
        return sequence;
    }
}
