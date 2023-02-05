package cn.com.hyun.framework.utils;

/**
 * Created by hyunwoo
 */
public final class PasswordStrengthUtils {
    private PasswordStrengthUtils() {
    }

    public static Level getStrength(String password) {
        if (StringUtils.isBlank(password)) {
            throw new NullPointerException();
        }

        int strengthPercentage = 0;
        String[] strengthRegexes = {".*[a-z]+.*", ".*[A-Z]+.*", ".*[\\d]+.*", ".*[@#$%]+.*"};

        for (String regex : strengthRegexes) {
            if (password.matches(regex)) {
                strengthPercentage += 1;
            }
        }

        switch (strengthPercentage) {
            case 0:
                return Level.Low;
            case 1:
                return Level.Low;
            case 2:
                return Level.Medium;
            case 3:
                return Level.Good;
            case 4:
                return Level.High;
            default:
                return Level.Low;
        }
    }

    public enum Level {
        Low("low"), Medium("medium"), Good("good"), High("high");

        private String value;

        Level(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
