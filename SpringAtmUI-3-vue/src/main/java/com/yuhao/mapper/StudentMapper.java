package com.yuhao.mapper;


import com.yuhao.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    Student getBySno(@Param("sno") String sno);

    Student getBySnoWithLock(@Param("sno") String sno);

    Student getByIdWithLock(@Param("id") Integer id);

    void updateBalanceBySno(@Param("balance") String balance,
                            @Param("sno") String sno);

    void updateBalanceById(@Param("balance") String balance,
                            @Param("id") Integer id);


    List<Student> listStudent();
}
