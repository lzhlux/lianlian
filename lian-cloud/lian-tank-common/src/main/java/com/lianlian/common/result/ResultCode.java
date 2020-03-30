package com.lianlian.common.result;

public enum  ResultCode {

    SUCCESS(200, "处理成功"),
    DISABLE(401, "您的账号异常，请联系客服"),
    EXCEPTION(406,"数据处理异常" ),
    UNAUTHTOKEN(402, "登入失效，请重新登入！"),
    NOT_FOUND(404, "很抱歉你访问的地址不存在！"),
    NOT_SUPPORTED(405, "请求方式错误"),
    INTERNAL_SERVER_ERROR(500, "您的网络异常，请稍后再试!"),
    NO_OERMISSION(99999, "没有权限"),
    PASSWORD_ERROR(10000, "用户名或密码错误"),
    PARAMETER_ERROR(10001, "参数错误"),
    REGISTER_ERROR(10002, "该手机号已经注册了"),
    VERIFY_CODE_ERROR(10003, "验证码输入有误"),
    SMS_CODE_ERROR(10004, "短信异常"),
    NO_USER_ERROR(10005, "该用户不存在"),
    JSON_RESOLVE_ERROR(10006, "json数据解析错误"),
    PAY_ERROR(10007, "支付相关问题"),
    INVALID_CODE(10008, "invalid code"),
    NOT_AUTH_ERROR(10009, "无效认证"),
    INVALID_RED_ENVELOP(10010, "红包无效"),
    APPLY(10011, "审批中"),
    ACCOUNT_BINDING(10012, "账号绑定"),
     NOT_TEAM(10014, "无效班级码"),
    YES_SIGN(10015, "今日已签名"),
    NOT_EMPOWER(10016, "该用户没有权限"),
    NOT_NULL(10017,"名称不能为空格"),
    NOT_DELETE(10018, "删除失败"),
    QUANTITY_DEFICIENCY(10019,"金额不足"),
    OUTNUMBER(10020,"你已签到,每天只能签到一次哦"),
    NULL_PATH(10021,"请至少上传一张图片"),
    DISTRIBUTIONSTAFF_ERROR(10022,"该用户已注册成为分销商,	请勿重复添加"),
    ROLE_ERROR(10023,"该角色有用户使用,不能删除"),
    COMMPDITYROTATIONPATH_REPEAT(10024,"该商品已添加首页轮播图,不能重复添加"),
    ERR_CODE_DES(10025,"支付单号校验不一致，请核实后再试"),
    COMMODITY_COMMISSION_ERROR(10026,"商品佣金比例已存在,不能重复添加"),
    SQUARE_ERROR(10027,"图片宽高大小比例需要为1:1"),
    SHAREIMG_ERROR(10028,"分享图片宽高大小比例需要为5:4"),
    SUPPLIER_ERROR(10029, "该账号已关联机构,请重新选择账号"),
    HOTSERCH_EXITED(10030,"热门搜索名称已存在"),
    INSUFFICIENT_FUND(10031,"余额不足"),
    STATUS_DISSATISFY(10032,"条件异常"),
    NO_DATA(10033,"条件异常"),
    DRAW_NUM_NOT_ENOUGH(10034,"抽将次数不足"),
    NO_ACTIVITY_COMMODITY_SPECIFICATION(10035,"未配置活动商品规格数量"),
    ACTIVITY_TIME_OUT(10036,"活动开始时间已过期"),
    KUCUNBUZU(10037,"库存不足"),
    GROUP_FINISH(10038,"该团已结束"),
    CHONGFU(10039,"请勿二次提交退申请"),
    HAD_PAY(10040,"该订单已支付"),
    hadJoinGroup(10041,"您已参团"),
    NOSPECIFICATION(10042,"缺少上架规格"),
    CHONGFU_AC(10043,"已存在待上线或上线中的活动商品"),
    WAN_NO_SERIAL(10044,"拼团编号不能为空"),
    NEXT_TIME(10045,"今天已提现过了，请明天再来"),
    INTEGRAL_NOT_ENOUGH(10046,"您的积分不足"),
    GJFSPYXJ(10047,"该积分商品已下架"),
    NYDHG(10048,"该已兑换过该商品"),
    HDWKS(10049,"活动时间未开始"),
    HDYJS(10050,"活动时间已结束"),
    YHSQFX(10051, "您的申请正在审核,请等待"),
    YCWFX(10047, "您已加入糖粉招募计划"),
    JEWMZ(10049, "您的订单金额不满88元"),
    YJRGWC(10050, "该商品已加入购物车"),
    GSPZNMYJ(10060, "新人专享商品只能购买一件"),
    DFKCZ(10061, "订单中已存在新人专享商品"),
    BKTX(10048, "您不可提现");



    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
