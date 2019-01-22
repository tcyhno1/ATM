package com.yuhao.service;



import com.yuhao.entity.Stream;
import com.yuhao.mapper.StreamMapper;
import com.yuhao.util.DbUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StreamService {
    private static Logger log = LoggerFactory.getLogger(StreamService.class);


    @Autowired
    private StreamMapper streamMapper;



    //用插件的方法根据日期查询的分页流水
    @Transactional(rollbackFor = Exception.class)
    public List<Stream> pageByUserSnoForPluginWithDate(String sno,String startDate,String endDate) {

            List<Stream> streamPageList = streamMapper.pageByUserSnoForPluginWithDate(sno,startDate,endDate);

            return streamPageList;


    }


    //查询某个号码的流水的总数
    public Integer streamCount(String sno) {

        SqlSession sqlSession = null;
        try {
            sqlSession = DbUtil.getSqlSession();
            StreamMapper streamMapper = sqlSession.getMapper(StreamMapper.class);
            Integer streamCount = streamMapper.streamCount(sno);

            return streamCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DbUtil.closeAll(sqlSession);
        }

    }




}
