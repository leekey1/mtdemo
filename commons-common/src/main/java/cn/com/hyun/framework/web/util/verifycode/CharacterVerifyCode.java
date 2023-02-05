package cn.com.hyun.framework.web.util.verifycode;

/**
 * Created by hyunwoo
 */
public final class CharacterVerifyCode implements VerifyCode {

    private static char[] sequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public char[] getCharSequence() {
        return sequence;
    }
}
