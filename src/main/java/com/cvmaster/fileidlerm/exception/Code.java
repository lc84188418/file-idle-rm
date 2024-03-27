package com.cvmaster.fileidlerm.exception;

public enum Code {
    /*
    INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
     */
    C400(400, "INVALID REQUEST"),
    /*
   Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）
    */
    C401(401, "Unauthorized"),
    /*
    NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的
     */
    C404(404, "Not Found"),
    /*
    method not allowed：该http方法不被允许
     */
    C405(405, "method not allowed"),
    /*
    Not Acceptable -用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
     */
    C406(406, "Not Acceptable"),
    /*
    参数列表不对，比如某方法参数类型列表所需的请求参数“idList”不存在
     */
    C407(407, "参数列表不对"),
    /*
    重复键异常
     */
    C408(408, "重复键异常，唯一性索引冲突"),
    /*
    二级密码异常
     */
    C410(410, "Secondary password authority verification"),
    /*
    不支持的媒体类型。例如上传文件只允许png图片，上传gif文件时，此时返回415
     */
    C415(415, "Error Type"),
    /*
    Unprocesable entity - [POST/PUT/PATCH]：当创建一个对象时，发生一个验证错误。请求格式正确，但语义错误。此时错误描述信息中最好有错误详情。
     */
    C422(422, "Unprocesable entity "),
    /*
    too many request - 请求过多
     */
    C429(429, "too many request"),
    /*
    call service error - 调用服务出错
     */
    C430(430, "call service error"),
    /*
    request error - 通用错误请求
     */
    C444(444, "request error"),
    /*
    INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功
     */
    C500(500, "Internal Server Error"),
    /*
    RedisConnectionFailureException - [*]：redis连接失败异常
     */
    C6379(6379, "Unable to connect to Redis"),
    /*
    ORDER SERVER ERROR - 订单服务异常
     */
    C1000(1000, "订单服务异常"),
    /*
    STORAGE SERVER ERROR - 库存服务异常
     */
    C2000(2000, "库存服务异常"),
    /*
    ACCOUNT SERVER ERROR 账户服务异常
    */
    C3000(3000, "账户服务异常"),
    /*
    SNOWFLAKE ID SERVER ERROR ID服务异常
    */
    C4000(4000, "ID服务异常"),
    /*
    SYSTEM SERVER ERROR 系统服务异常
    */
    C5000(5000, "系统服务异常"),
    /*
    IM服务异常
    */
    RPC_EXCEPTION(9000, "rpc服务调用异常");
    /**
     * Code 状态码
     */
    private Integer code;
    /**
     * desc 描述
     */
    private String desc;

    Code(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
