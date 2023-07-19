package com.example.demo.mapper;

import com.example.demo.bean.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void addLog(Log log);
    void clear();
}
