package cloud.truesun.domain;

//状态码
public class Code {
    // 用户信息状态码
    public static final Integer LOGIN_OK = 10001;
    public static final Integer SAVE_OK = 10011;
    public static final Integer DELETE_OK = 10021;
    public static final Integer UPDATE_OK = 10031;
    public static final Integer GET_OK = 10041;
    public static final Integer PAGES_OK = 10051;
    public static final Integer UPLOAD_OK = 10061;

    public static final Integer LOGIN_ERR = 10000;
    public static final Integer PASSWORD_ERR = 11000;
    public static final Integer SAVE_ERR = 10010;
    public static final Integer DELETE_ERR = 10010;
    public static final Integer UPDATE_ERR = 10030;
    public static final Integer GET_ERR = 10040;
    public static final Integer PAGES_ERR = 10050;
    public static final Integer UPLOAD_ERR = 10060;

    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;
    public static final Integer BUSINESS_ERR = 60002;
}
