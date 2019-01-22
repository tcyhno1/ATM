package com.yuhao.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuhao.entity.Stream;
import com.yuhao.entity.Student;
import com.yuhao.service.StudentService;
import com.yuhao.util.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 跟用户信息相关
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);


    @Resource  //先写下面那行再写这行注解
    private StudentService studentService;  //这里报错，是因为StudentService那边没有@Service

    @PostMapping("login.do")
    public String login(HttpSession session,ModelMap modelMap,
                        String loginName,
                        String password
                        ){
//        log.debug("sTime:{},eTime:{},pageIndex:{}",sTime,eTime,pageIndex);


        log.info("登陆账号:{},用户密码:{}",loginName,password);


        //从登陆form中拿到用户账号和密码
//        String loginName = request.getParameter("loginName");
//        String password = request.getParameter("password");

        //后端验证，账号和密码是否符合规范
        if (loginName == null || "".equals(loginName) || password == null || "".equals(password)) {
            return "redirect:/login.jsp";
        }
        //账号是否是纯数字
        for (int i = loginName.length(); --i >= 0; ) {
            if (!Character.isDigit(loginName.charAt(i))) {
                return "redirect:/login.jsp";
            }
        }
        //密码是否是纯数字
        for (int i = password.length(); --i >= 0; ) {
            if (!Character.isDigit(password.charAt(i))) {
                return "redirect:/login.jsp";
            }
        }

        //后端验证过后，数据可用，再调用数据库。
        // 通过账号从数据库调出用户信息封装在student对象中
        Student student = studentService.selectStudentBySno(loginName);

        //查无此人，登陆失败
        if (student == null) {
            return "redirect:/login.jsp";
        }

        //账号对应的数据库对象找到了，取出数据库中的密码，校核验证用户输入密码
        String pwd1 = student.getPwd();

        //密码不匹配，登陆失败
        if (!(pwd1 != null && password.equals(pwd1))) {
            //modelMap.addAttribute("loginWrong","用户名或密码错误");
            return "redirect:/login.jsp";
        }

        //上面的验证都通过了，就可以建立session会话，进行接下来系列页面的操作
//        HttpSession session = request.getSession();
        //cookie
        session.setAttribute("loginName", loginName);

        //这是测试<jsp:include page="student.jsp"/>，传入两种数据，一个是当前页面查出姓名，传过去，一个是传过去带有所有信息的对象，在那个页面进一步操作
        session.setAttribute("studentName", student.getSname());//给student.jsp传递一个属性
        session.setAttribute("student", student);//给student.jsp传递一个对象（内含查询数据库出来的所有属性）

        log.info(student.getSno()+"登陆成功");

        //重定向
        return "redirect:/user/home.do";
    }


    @GetMapping("home.do")
    public String home(HttpSession session, ModelMap modelMap){
//        HttpSession session = request.getSession(false);


        String loginName = (String) session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);


        //设置request作用域
//        request.setAttribute("studentName", student.getSname());
        modelMap.addAttribute("studentName", student.getSname());

        return "home";
    }


    @RequestMapping(value = "/students.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String students(HttpSession session, ModelMap modelMap,
                          @RequestParam(value = "pageIndex",required = true, defaultValue = "1") Integer pageIndex
    ){



        Integer pageRows = 3;

        String loginName = (String) session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);

        PageHelper.startPage(pageIndex,pageRows);//当前页码，每页行数
        List<Student> studenstList = studentService.listStudent();

        modelMap.addAttribute("studenstList", studenstList);//addAttribute 比 put 多了一个key值非null判断
        modelMap.put("student", student);                   //addAttribute是modelmap的方法，put是map的方法
        modelMap.put("pageIndex",pageIndex);

        PageInfo<Student> pageInfo = new PageInfo<>(studenstList,3);//分页数据，页码导航数

        int totalPages = pageInfo.getPages();   //总页数
        int[] navigatepageNums = pageInfo.getNavigatepageNums();//导航

        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("navigatepageNums",navigatepageNums);

        log.info(student.getSno()+"查询了会员列表");

        return "studentList";
    }


    @RequestMapping(value = "/listStudents_ajax.do", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult listStudents_ajax(HttpSession session,
                           @RequestParam(value = "pageIndex",required = true, defaultValue = "1") Integer pageIndex
    ){


        try {
            Integer pageRows = 3;

            String loginName = (String) session.getAttribute("loginName");
            Student student = studentService.selectStudentBySno(loginName);

            PageHelper.startPage(pageIndex,pageRows);//当前页码，每页行数
            List<Student> studenstList = studentService.listStudent();


            PageInfo<Student> pageInfo = new PageInfo<>(studenstList,3);//分页数据，页码导航数


            return AjaxResult.success(pageInfo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return AjaxResult.fail("系统异常");
        }
    }



    @RequestMapping("loginOut.do")
    public String loginOut(HttpSession session,ModelMap modelMap){


        log.info("loginOut.do");
//        HttpSession session = request.getSession(false);
        String loginName = (String) session.getAttribute("loginName");
        Student student = studentService.selectStudentBySno(loginName);
//        request.setAttribute("student", student);
        modelMap.addAttribute("student", student);

        if (session != null) {
            session.invalidate();
            System.out.println("销毁session");
        }
        log.info(student.getSno()+"登出成功");
        return "redirect:/login.jsp";

    }


    /**
     * 接口 api
     * @param session
     * @return
     */
    @GetMapping(value = "getUserForJson.do")
    @ResponseBody//使用springmvc消息转换器，进行序列化
    public AjaxResult getUserForJson(HttpSession session){


        try {
            String loginName = (String) session.getAttribute("loginName");

            Student student = studentService.selectStudentBySno(loginName);

            return AjaxResult.success(student);
        } catch (Exception e) {
            log.error(e.getMessage(),e);

            return AjaxResult.fail(666,"系统异常");
        }

    }




}
