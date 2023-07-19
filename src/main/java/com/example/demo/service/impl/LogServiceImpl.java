package com.example.demo.service.impl;

import com.example.demo.bean.Log;
import com.example.demo.mapper.LogMapper;
import com.example.demo.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    final LogMapper logMapper;

    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void add(Log log) {
        logMapper.addLog(log);
    }

    @Override
    public void clear() {
        logMapper.clear();
    }
}
