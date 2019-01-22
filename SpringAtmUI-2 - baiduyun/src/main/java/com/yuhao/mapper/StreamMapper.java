package com.yuhao.mapper;


import com.yuhao.entity.Stream;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StreamMapper {

    void insertStream(Stream stream);

    List<Stream> listStreamByUserSno(@Param("sno") String sno);

    List<Stream> pageByUserSno(@Param("sno") String sno,
                               @Param("offset") Integer offset,
                               @Param("limit") Integer limit);

    List<Stream> pageByUserSnoForPlugin(@Param("sno") String sno);

    List<Stream> pageByUserSnoForPluginWithDate(@Param("sno") String sno,
                                                @Param("startDate") String startDate,
                                                @Param("endDate") String endDate);



    Integer streamCount(@Param("sno") String sno);
}
