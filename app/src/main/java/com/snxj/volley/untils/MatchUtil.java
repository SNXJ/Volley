package com.snxj.volley.untils;


import com.snxj.volley.untils.idcardutil.IdcardValidator;

/**
 * 字符串匹配工具类
 */
public class MatchUtil {
    // 电话号码的正则表达式
    static String NumberRE1 = "(^(0\\d{2,3})?(\\d{7,8})(,\\d{3,})?)$";
    // 手机号码正则表达式
    private static final String PHONEREG = "^(13\\d|147|17\\d|15[0-35-9]|18\\d)\\d{8}$";
    // private static String PHONEREG =
    // "/^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/";

    private static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    // 以字母开头的英文或数字组合3到50位字符串
    private static final String CHACCOUNT = "^\\w{3,20}$";

    // private static String CHACCOUNT = "^[A-Z0-9a-z_]{3,50}$";
    private static final String PASSWORD = "^[a-zA-Z0-9_\\.*\\+*\\**#*@*\\-*%*]{6,18}$";
//	 private static String PASSWORD = "^[\\w\\.\\#\\%\\-\\+\\*\\@]{6,18}$";

    private static final String PASSWORDREG = "^[\\x20-\\x7E]{6,50}$";
    private static final String ALLNUMBER = "^\\d{6,50}";

    /**
     * 验证字符串是否是手机号码
     *
     * @param matchStr 代匹配的字符串
     * @return 如果是返回true否则返回false
     */
    public static boolean isPhoneNum(String matchStr) {

        return matchStr == null ? false : matchStr.matches(PHONEREG);

    }

    /**
     * 验证字符串是否是邮箱
     *
     * @param matchStr 代匹配的字符串
     * @return 如果是返回true否则返回false
     */
    public static boolean isEmail(String matchStr) {

        return matchStr == null ? false : matchStr.matches(EMAIL);

    }

    /**
     * 判断是否为全数字
     *
     * @param matchStr
     * @return
     */
    public static boolean isAllNumber(String matchStr) {
        return matchStr == null ? false : matchStr.matches(ALLNUMBER);

    }

    /**
     * 判断密码是否合法
     *
     * @param matchStr
     * @return
     */
    public static boolean isPassword(String matchStr) {
        return matchStr == null ? false : matchStr.matches(PASSWORD);

    }

    /**
     * 验证字符串是否是电话（包括手机）号码
     *
     * @param matchStr 代匹配的字符串
     * @return 如果是返回true否则返回false
     */
    public static boolean isContactNum(String matchStr) {
        return matchStr == null ? false
                : (matchStr.matches(PHONEREG) || matchStr.matches(NumberRE1));
    }

    /**
     * 判断是否是合法的帐号
     *
     * @param matcherStr 待检验的字符串
     * @return 如果合法返回true 否则返回false
     */
    public static boolean isLicitAccount(String matcherStr) {

        return matcherStr == null ? false
                : (matcherStr.matches(PHONEREG) || matcherStr
                .matches(CHACCOUNT));

    }

    public static boolean isLicitPassword(String matcherStr) {

        return matcherStr == null ? false : matcherStr.matches(PASSWORDREG);

    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }

        }
        return true;
    }

    /**
     * 判断身份证的合法性
     *
     * @param cardNum
     * @return
     */
    public static boolean isCardNumber(String cardNum) {
        IdcardValidator cardValidator = new IdcardValidator();
        if (!cardValidator.isValidatedAllIdcard(cardNum)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {

        if (StringUtils.isEmpty(s)) {
            return "";
        }

        if (s.indexOf('.') > 0) {
            s = s.replaceAll("0+?$", ""); //去掉多余的0
            s = s.replaceAll("[.]$", ""); //如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

}
