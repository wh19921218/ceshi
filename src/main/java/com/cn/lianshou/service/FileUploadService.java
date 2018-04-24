package com.cn.lianshou.service;

import java.util.List;

/**
 * FileName: com.cn.lianshou.service.FileUploadService.java
 * Author: Wanghh
 * Date: 2018/3/26 17:28
 */
public interface FileUploadService {

    public List<String[]> readExcel(String path);
    public void save(Object o);
    public void list(List<String[]> excellist);
}
