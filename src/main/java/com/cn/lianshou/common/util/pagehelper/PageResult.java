package com.cn.lianshou.common.util.pagehelper;

import java.util.List;

/**
 * FileName: com.cn.lianshou.common.util.pagehelper.PageResult.java
 * Author: Wanghh
 * Date: 2018/3/17 15:37
 */
public class PageResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
