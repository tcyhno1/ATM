package com.yuhao.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuhao.alipay.AlipayConfig;
import com.yuhao.entity.RechargeInfo;
import com.yuhao.entity.Stream;
import com.yuhao.entity.Student;
import com.yuhao.exception.TransforException;
import com.yuhao.mapper.RechargeMapper;
import com.yuhao.service.StreamService;
import com.yuhao.service.StudentService;
import com.yuhao.util.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("balance")
public class BalanceController {

    private static Logger log = LoggerFactory.getLogger(BalanceController.class);


    @Resource
    private StudentService studentService;
    @Resource
    private StreamService streamService;
    @Resource
    private RechargeMapper rechargeMapper;

    @GetMapping(value = "/query.do")
    public String query(HttpSession session,ModelMap modelMap){

        String loginName = (String) session.getAttribute("loginName");
        //log.debug("sTime:{},eTime:{},pageIndex:{}",sTime,eTime,pageIndex);
        log.debug("debug,用户:{},查询余额",loginName); //这里debug在控制台没有输出，是因为日志输出级别设置的是'INFO'
        log.info("info1,用户:{},查询余额",loginName); //这里写法是java的动态参数
        log.info("info2,"+loginName+"进行了余额查询");  //正常拼接字符串写法

        Student student = studentService.selectStudentBySno(loginName);

        modelMap.addAttribute("student", student);
//        request.setAttribute("student", student);
        return "query";
    }


    @GetMapping(value = "/toDraw.do")
    public String toDraw(HttpSession session,ModelMap modelMap) {



        String loginName = (String) session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);
//        request.setAttribute("student", student);
        modelMap.addAttribute("student", student);
        //生成令牌
        session.setAttribute("token", UUID.randomUUID().toString());
        return "draw";
    }


    @PostMapping(value = "/doDraw.do")
    public String doDraw(HttpSession session,ModelMap modelMap,
                         @RequestParam(value = "token")   String c_token,
                         String money

                         ) throws Exception{

        String loginName = (String) session.getAttribute("loginName");


        //客户端传过来的
//        String c_token = request.getParameter("token");
        Object s_token = session.getAttribute("token");
        //校验token是否有效
        if (s_token == null || c_token == null || !c_token.equals(String.valueOf(s_token))) {
//            request.setAttribute("errorMsg", "请不要重复提交");
//            modelMap.addAttribute("errorMsg", "请不要重复提交");
//            return "exception";
            throw new TransforException("请不要重复提交");
        }

        //令牌清除
        session.removeAttribute("token");

//        String money = request.getParameter("money");
        //后端验证
        if (money == null || "".equals(money)) {
            return "fail";
        }

        for (int i = money.length(); --i >= 0; ) {
            if (!Character.isDigit(money.charAt(i))) {
                return "fail";
            }
        }

        Integer money1 = Integer.valueOf(money);

        if (money1 <= 0) {
            return "fail";
        }

        try {

            //取款业务操作
            studentService.draw(loginName, money1);

        } catch (Exception e) {
            e.printStackTrace();

            //将取款业务传递上来的所有异常信息，放入request中，传入异常页面，做出个性化展示
//            request.setAttribute("errorMsg", e.getMessage());
//            modelMap.addAttribute("errorMsg", e.getMessage());
//          return "exception";
            throw e;
        }

        //操作成功，跳转页面
//        request.setAttribute("msg", "取款操作成功");
        modelMap.addAttribute("msg", "取款操作成功");
        return "success";

    }


    @GetMapping(value = "/toSave.do")
    public String toSave(HttpSession session,ModelMap modelMap) {


        String loginName = (String) session.getAttribute("loginName");
        //跳转存款页面，同时往该页面通过request传递从数据库查出的个性化动态信息
        Student student = studentService.selectStudentBySno(loginName);
//        request.setAttribute("student", student);
        modelMap.addAttribute("student", student);
        //生成令牌
        session.setAttribute("token", UUID.randomUUID().toString());
        return "save";

    }


    @PostMapping(value = "/doSave.do")
    public String doSave(HttpSession session,ModelMap modelMap,
                         String token,
                         String money
                         ) throws Exception{

        String loginName = (String) session.getAttribute("loginName");

        //客户端传过来的
//        String c_token = request.getParameter("token");
        Object s_token = session.getAttribute("token");
        //校验token是否有效
        if (s_token == null || token == null || !token.equals(String.valueOf(s_token))) {
//            request.setAttribute("errorMsg", "请不要重复提交");
//            modelMap.addAttribute("errorMsg", "请不要重复提交");
//            return "exception";
            throw new TransforException("请不要重复提交");
        }

        //令牌清除
        session.removeAttribute("token");

//        String money = request.getParameter("money");
        //后端验证
        if (money == null || "".equals(money)) {
            return "fail";
        }

        for (int i = money.length(); --i >= 0; ) {
            if (!Character.isDigit(money.charAt(i))) {
                return "fail";
            }
        }

        Integer money1 = Integer.valueOf(money);

        if (money1 <= 0) {
            return "fail";
        }

        try {
            //存款业务操作
            studentService.save(loginName, money1);

        } catch (Exception e) {
            e.printStackTrace();

            //将取款业务传递上来的所有异常信息，放入request中，传入异常页面，做出个性化展示
//            request.setAttribute("errorMsg", e.getMessage());
//            modelMap.addAttribute("errorMsg", e.getMessage());
//            return "exception";
            throw e;
        }

        //操作成功，跳转页面
//        request.setAttribute("msg", "存款操作成功");
        modelMap.addAttribute("msg", "存款操作成功");

        return "success";
    }



    @GetMapping(value = "/toTrans.do")
    public String toTrans(HttpSession session,ModelMap modelMap) {


        String loginName = (String) session.getAttribute("loginName");

        //跳转转账页面，同时往该页面通过request传递从数据库查出的个性化动态信息
        Student student = studentService.selectStudentBySno(loginName);
//        request.setAttribute("student", student);
        modelMap.addAttribute("student", student);
        //生成令牌
        session.setAttribute("token", UUID.randomUUID().toString());

        return "trans";
    }


    @PostMapping(value = "/doTrans.do")
    public String doTrans(HttpSession session,ModelMap modelMap,
                          String token,
                          String toNum,
                          String studentName,
                          String money
                          ) throws Exception{

        String loginName = (String) session.getAttribute("loginName");

        log.info("转出用户:{},转入用户:{},转账:{}",loginName,toNum,money);

        //客户端传过来的
//        String c_token = request.getParameter("token");
        Object s_token = session.getAttribute("token");
        //校验token是否有效
        if (s_token == null || token == null || !token.equals(String.valueOf(s_token))) {
//            request.setAttribute("errorMsg", "请不要重复提交");
            //modelMap.addAttribute("errorMsg", "请不要重复提交");
            //return "exception";
            throw new TransforException( "请不要重复提交");
        }

        //令牌清除
        session.removeAttribute("token");

//        String toNum = request.getParameter("toNum");
//        String toName = request.getParameter("studentName");
//        String money = request.getParameter("money");

        //后端验证
        if (toNum == null || "".equals(toNum)) {
            return "fail";
        }

        if (studentName == null || "".equals(studentName)) {
            return "fail";
        }

        if (money == null || "".equals(money)) {
            return "fail";
        }

        for (int i = toNum.length(); --i >= 0; ) {
            if (!Character.isDigit(toNum.charAt(i))) {
                return "fail";
            }
        }

        for (int i = money.length(); --i >= 0; ) {
            if (!Character.isDigit(money.charAt(i))) {
                return "fail";
            }
        }

        Integer money1 = Integer.valueOf(money);

        if (money1 <= 0) {
            return "fail";
        }
        //后端验证结束


        try {

            //转账业务操作
            studentService.transfor(loginName, toNum, studentName, money1);

        } catch (Exception e) {
            e.printStackTrace();

            //将转账业务传递上来的所有异常信息，放入request中，传入异常页面，做出个性化展示
//            request.setAttribute("errorMsg", e.getMessage());
//            modelMap.addAttribute("errorMsg", e.getMessage());
//            return "exception";
            throw e;

        }

        //操作成功，跳转页面
//        request.setAttribute("msg", "转账操作成功");
        modelMap.addAttribute("msg", "转账操作成功");
        log.info(loginName+"转账操作成功");
        return "success";
    }




    @RequestMapping(value = "/streams.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String streams(HttpSession session, ModelMap modelMap,
                          String sTime,
                          String eTime,
                          @RequestParam(value = "pageIndex",required = true, defaultValue = "1") Integer pageIndex
                          ){
        modelMap.addAttribute("sTime",sTime);
        modelMap.addAttribute("eTime",eTime);



        String startDate = null;
        String endDate =null;
        if(sTime !=null && sTime!=""){
            if (eTime==""){
                eTime = sTime;
            }
            startDate = sTime+" 00:00:00";
            endDate = eTime+" 23:59:59";
        }
        Integer pageRows = 8;

        String loginName = (String) session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);

        PageHelper.startPage(pageIndex,pageRows);//当前页码，每页行数
        List<Stream> streamList = streamService.pageByUserSnoForPluginWithDate(loginName,startDate,endDate);

        modelMap.addAttribute("streamList", streamList);//addAttribute 比 put 多了一个key值非null判断
        modelMap.put("student", student);                   //addAttribute是modelmap的方法，put是map的方法
        modelMap.put("pageIndex",pageIndex);

        PageInfo<Stream> pageInfo = new PageInfo<>(streamList,5);//分页数据，页码导航数

        int totalPages = pageInfo.getPages();   //总页数
        int[] navigatepageNums = pageInfo.getNavigatepageNums();//导航

        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("navigatepageNums",navigatepageNums);
        return "pagepluginlimit";
    }


    //————————————流水表，ajax,vue————————————

    @RequestMapping(value = "/listStreams_ajax.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult listStreams_ajax(HttpSession session,
                                       String sTime,
                                       String eTime,
                                       @RequestParam(value = "pageIndex",required = true, defaultValue = "1") Integer pageIndex
    ){


        try {
//        modelMap.addAttribute("sTime",sTime);
//        modelMap.addAttribute("eTime",eTime);

//            HashMap<Object, Object> params = new HashMap<>();

//            params.put("sTime",sTime);
//            params.put("eTime",eTime);


            String startDate = null;
            String endDate =null;
            if(sTime !=null && sTime!=""){
                if (eTime==""){
                    eTime = sTime;
                }
                startDate = sTime+" 00:00:00";
                endDate = eTime+" 23:59:59";
            }
            Integer pageRows = 8;

            String loginName = (String) session.getAttribute("loginName");
            Student student = studentService.selectStudentBySno(loginName);
            //分页处理，显示第一页的8条数据
            PageHelper.startPage(pageIndex,pageRows);//当前页码，每页行数
            List<Stream> streamList = streamService.pageByUserSnoForPluginWithDate(loginName,startDate,endDate);

//            modelMap.addAttribute("streamList", streamList);//addAttribute 比 put 多了一个key值非null判断
//            modelMap.put("student", student);                   //addAttribute是modelmap的方法，put是map的方法
//            modelMap.put("pageIndex",pageIndex);
            //分页数据
            PageInfo<Stream> pageInfo = new PageInfo<>(streamList,5);//分页数据，页码导航数
//            params.put("pageInfo",pageInfo);
//            int totalPages = pageInfo.getPages();   //总页数
//            int[] navigatepageNums = pageInfo.getNavigatepageNums();//导航

//            modelMap.addAttribute("totalPages", totalPages);
//            modelMap.addAttribute("navigatepageNums",navigatepageNums);
            return AjaxResult.success(pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return AjaxResult.fail("系统异常");
        }
    }








//——————————————————————————————支付宝——————————————————————

    /**
     * 支付宝相关练习如下
     * @return
     */

    @RequestMapping(value = "toRecharge.do",method = {RequestMethod.GET,RequestMethod.POST})
    public String toRecharge(){
        return "recharge";
    }


    @RequestMapping(value = "doRecharge.do",method = {RequestMethod.GET,RequestMethod.POST})
    public void doRecharge(HttpSession session,HttpServletResponse response, String money){

        //获取当前登陆用户
        String loginName = (String)session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);
        //用当前时间生成订单号，订单号数据库中需要加唯一索引
        String orderId = System.currentTimeMillis() + "";

        //下单;数据库生成状态为2的待处理的订单，并且还没有trade_id
        RechargeInfo rechargeInfo = new RechargeInfo();
        rechargeInfo.setMoney(money);
        rechargeInfo.setOrderId(orderId);
        rechargeInfo.setUserId(student.getId());
        studentService.insertRecharge(rechargeInfo);


        //调用支付宝支付接口
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderId;//上面有提前设定，用的是当前时间
        //付款金额，必填
        String total_amount = money;//用户传过来的
        //订单名称，必填
        String subject = "大猿ItAtm";//在这里现写，随便
        //商品描述，可空
        String body = "充值";//在这里现写，随便

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        try {
            //result:一个自动提交的form表单
            String result = alipayClient.pageExecute(alipayRequest).getBody();
//            log.debug(result);
            //输出
//        out.println(result);

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(result);//将支付宝传来的result写到浏览器上，就生成了支付页面

        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @RequestMapping(value = "notifyUrl.do",method = {RequestMethod.GET,RequestMethod.POST})
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("支付宝异步通知");


        //接收报文
        /* *
         * 功能：支付宝服务器异步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


         *************************页面功能说明*************************
         * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
         * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
         * 如果没有收到该页面返回的 success
         * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
         */

        //获取支付宝POST过来的反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if (signVerified) {//验证成功
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");//我们给支付宝的orderId,被支付宝拿到简单处理再传回来

            //支付宝交易号，用于对账
            String trade_no = request.getParameter("trade_no");//支付宝自动生成的

            //交易状态
            String trade_status = request.getParameter("trade_status");//这个状态是支付宝的，表现的是支付宝是否操作成功

            String app_id = request.getParameter("app_id");

            String total_amount = request.getParameter("total_amount");

            RechargeInfo rechargeInfo = rechargeMapper.selectRechargeForLock(out_trade_no);

            //验证appid是否正确
            if (!(app_id.equals(AlipayConfig.app_id) && out_trade_no.equals(rechargeInfo.getOrderId()) && total_amount.equals(rechargeInfo.getMoney()))) {
                try {
                    response.getWriter().println("fail");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {

                log.info("支付异步通知：out_trade_no：{}，交易号：{},状态：{}", out_trade_no, trade_no, trade_status);

                //异步通知是支付宝告诉商家的，只有收到异步通知，才进行数据库数据状态的更新
                studentService.updateRechargeStatus(out_trade_no, trade_no);

            }

            try {
                //给支付宝返回一个success，如果不返回，支付宝就会持续发送异步通知
                response.getWriter().println("success");
            } catch (IOException e) {
                e.printStackTrace();
            }

//            out.println("success");

        } else {//验证失败
//            out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);

            try {
                response.getWriter().println("fail");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //——请在这里编写您的程序（以上代码仅作参考）——


    }

}





