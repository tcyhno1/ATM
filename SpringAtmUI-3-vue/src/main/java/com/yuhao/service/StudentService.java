package com.yuhao.service;



import com.yuhao.entity.RechargeInfo;
import com.yuhao.entity.Stream;
import com.yuhao.entity.Student;
import com.yuhao.exception.TransforException;
import com.yuhao.mapper.RechargeMapper;
import com.yuhao.mapper.StreamMapper;
import com.yuhao.mapper.StudentMapper;
import com.yuhao.util.DbUtil;
import com.yuhao.util.DecimalCalculate;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    private static Logger log = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StreamMapper streamMapper;
    @Resource
    private RechargeMapper rechargeMapper;





    //查询一个studnet对象，通过对象拿到他的余额
    public Student selectStudentBySno(String sno) {
       return studentMapper.getBySno(sno);

    }


    /**
     * 取款
     * @param sno
     * @param money
     * @throws Exception
     */

    @Transactional(rollbackFor = Exception.class)
    public void draw(String sno, Integer money) throws Exception {


            Student student = studentMapper.getBySnoWithLock(sno);
            if (student == null) {
                throw new TransforException("卡号有误");
            }

            String balance = student.getBalance();

            if (money % 100 != 0) {
                throw new TransforException("取款金额必须为100的整数倍");
            }

            if (money > 2000) {
                throw new TransforException("每次取款金额上限为2000");

            }



            if (Double.parseDouble(balance) < money) {
                throw new TransforException("金额不足");
            }


            Double _balance = DecimalCalculate.sub(Double.parseDouble(balance), money);

            studentMapper.updateBalanceBySno(_balance.toString(), sno);

            //-------------------流水------------------------//
            Integer Id = student.getId();
            String userSno = student.getSno();


            Stream stream = new Stream();
            stream.setUserId(Id);
            stream.setUserSno(userSno);
            stream.setType(1);
            stream.setMoney("-" + money);
            streamMapper.insertStream(stream);



    }


    /**
     * 转账 悲观锁
     * @param sno
     * @param inSno
     * @param inUserName
     * @param money
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void transfor(String sno, String inSno, String inUserName, Integer money) throws Exception {


            if (sno.equals(inSno)) {
                throw new TransforException("转入账户不能是自己");
            }

            Student outStudent = studentMapper.getBySnoWithLock(sno);
            Student inStudent = studentMapper.getBySnoWithLock(inSno);

            if (outStudent == null) {
                throw new TransforException("转出卡号有误");
            }

            if (inStudent == null) {
                throw new TransforException("转入的账号查无此人");
            }

            String outBalance = outStudent.getBalance();
            Integer outId = outStudent.getId();

            String inBalance = inStudent.getBalance();
            Integer inId = inStudent.getId();

            String inName = inStudent.getSname();
            if (!inName.equals(inUserName)) {
                throw new TransforException("转入的账号查无此人");
            }

            if (money > 10000) {
                throw new TransforException("单次转账的最大额度为10000");
            }

            if (money > Double.parseDouble(outBalance)) {
                throw new TransforException("余额不足");
            }

            Double _balance = DecimalCalculate.sub(Double.parseDouble(outBalance), money);
            Double _in_balance = DecimalCalculate.add(Double.parseDouble(inBalance), money);

            studentMapper.updateBalanceBySno(_balance.toString(), sno);
            studentMapper.updateBalanceBySno(_in_balance.toString(), inSno);

            //-------------------流水------------------------//
            Stream stream = new Stream();
            stream.setUserId(outId);
            stream.setUserSno(sno);
            stream.setType(3);
            stream.setMoney("-" + money);
            streamMapper.insertStream(stream);

            stream = new Stream();
            stream.setUserId(inId);
            stream.setUserSno(inSno);
            stream.setType(4);
            stream.setMoney(money.toString());
            streamMapper.insertStream(stream);

    }


    /**
     * 存款
     * @param sno
     * @param money
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(String sno, Integer money) throws Exception {

            Student student = studentMapper.getBySnoWithLock(sno);
            if (student == null) {
                throw new TransforException("卡号有误");
            }

            String balance = student.getBalance();

            if (money > 10000) {
                throw new TransforException("该ATM机一次存款最大金额为10000");
            }

            if (money % 50 != 0) {
                throw new TransforException("存款金额必须为50的倍数");
            }

            Double _balance = DecimalCalculate.add(Double.parseDouble(balance), money);

            studentMapper.updateBalanceBySno(_balance.toString(), sno);



            //-------------------流水------------------------//

            Integer Id = student.getId();
            String userSno = student.getSno();


            Stream stream = new Stream();
            stream.setUserId(Id);
            stream.setUserSno(userSno);
            stream.setType(2);
            stream.setMoney("" + money);
            streamMapper.insertStream(stream);


    }


    /**
     * 查询student列表
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Student> listStudent() {

        List<Student> studentPageList = studentMapper.listStudent();

        return studentPageList;
    }


    /**
     * 充值时，第一步先给数据库建立一条状态为2的数据
     * @param rechargeInfo
     */
    public void insertRecharge(RechargeInfo rechargeInfo){
        rechargeMapper.insertRecharge(rechargeInfo);
    }


    /**
     * 用于异步通知时，更新数据库里订单状态并且加上trade_id。
     * @param out_trade_no
     * @param trade_no
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateRechargeStatus(String out_trade_no,String trade_no) throws Exception {

        //锁订单
        RechargeInfo rechargeInfo = rechargeMapper.selectRechargeForLock(out_trade_no);
        if (rechargeInfo == null){
            throw new Exception("订单有误");
        }

        //幂等，同一件事情只能执行一次
        if(rechargeInfo.getStatus()==1){
            return;
        }

        //更新充值数据
        rechargeMapper.updateRechargeStatus(out_trade_no,trade_no);

        //锁账号
        Student student = studentMapper.getByIdWithLock(rechargeInfo.getUserId());
        if(student==null){
            throw new Exception("卡号有误");
        }
        //查询余额
        String balance = student.getBalance();

        //计算取款后余额
        Double _balance = DecimalCalculate.add(Double.parseDouble(balance), Double.parseDouble(rechargeInfo.getMoney()));

        //更新余额
        studentMapper.updateBalanceById(_balance.toString(), rechargeInfo.getUserId());



    }

}
