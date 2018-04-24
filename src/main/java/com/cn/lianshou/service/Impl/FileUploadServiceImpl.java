package com.cn.lianshou.service.Impl;

import com.cn.lianshou.mapper.FileUploadDao;
import com.cn.lianshou.entity.BorrowUserInfo;
import com.cn.lianshou.entity.UrgeOrderInfo;
import com.cn.lianshou.service.BorrowUserInfoService;
import com.cn.lianshou.service.FileUploadService;
import com.cn.lianshou.service.UrgeOrderInfoService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.service.Impl.FileUploadServiceImpl.java
 * Author: Wanghh
 * Date: 2018/3/26 17:29
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadDao fileUploadDao;
    @Autowired
    private BorrowUserInfoService borrowUserInfoService;
    @Autowired
    private UrgeOrderInfoService urgeOrderInfoService;



    public List<String[]> readExcel(String path) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<String[]> list = null;
        try {
            //同时支持Excel 2003、2007
            File excelFile = new File(path); //创建文件对象
            FileInputStream is = new FileInputStream(excelFile); //文件流
            Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets(); //Sheet的数量
            //存储数据容器
            list = new ArrayList<String[]>();
            //遍历每个Sheet
            for (int s = 0; s < sheetCount; s++) {
                Sheet sheet = workbook.getSheetAt(s);
                int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
                //遍历每一行
                for (int r = 0; r < rowCount; r++) {
                    Row row = sheet.getRow(r);
                    int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                    //用来存储每行数据的容器
                    String[] model = new String[cellCount-1];
                    //遍历每一列
                    for (int c = 0; c < cellCount; c++) {
                        Cell cell = row.getCell(c);
                        int cellType = cell.getCellType();

                        if(c == 0) continue;//第一列ID为标志列，不解析

                        String cellValue = null;
                        switch(cellType) {
                            case Cell.CELL_TYPE_STRING: //文本
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC: //数字、日期
                                DecimalFormat df = new DecimalFormat("#");

                                if(DateUtil.isCellDateFormatted(cell)) {
                                    cellValue = fmt.format(cell.getDateCellValue()); //日期型
                                }
                                else {//数字
                                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){// 数字
                                        cellValue = df.format(cell.getNumericCellValue());
                                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING){// 字符串
                                        cellValue = df.format(Double.parseDouble(cell.toString()));
                                    } else {
                                        cellValue = cell.toString();
                                    }
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN: //布尔型
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_BLANK: //空白
                                cellValue = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_ERROR: //错误
                                cellValue = "错误";
                                break;
                            case Cell.CELL_TYPE_FORMULA: //公式
                                cellValue = "错误";
                                break;
                            default:
                                cellValue = "错误";

                        }
                        model[c-1] = cellValue;
                    }
                    //model放入list容器中
                    list.add(model);
                }
            }
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void list(List<String[]> excellist){
        int len = excellist.size();
        System.out.println("集合的长度为：" + len);
        for (int i = 1; i < len; i++) {
            String[] fields = excellist.get(i);

            String channel = fields[0];
            String orderNo = fields[1];
            String amount = fields[2];
            String realAmount = fields[3];
            String borrowTime = fields[4];
            String timeLimit = fields[5];
            String unit = fields[6];
            String repayTime = fields[7];
            String penaltyDay = fields[8];
            String penaltyAmout = fields[9];
            String state = fields[10];
            String level = fields[11];
            String realName = fields[12];
            String phone = fields[13];
            String sex = fields[14];
            String age = fields[15];
            String idNo = fields[16];
            String urgeNameOne = fields[17];
            String urgeRelationOne = fields[18];
            String urgePhoneOne = fields[19];
            String urgeNameTow = fields[20];
            String urgeRelationTow = fields[21];
            String urgePhoneTow = fields[22];

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("channel", channel);
            map.put("orderNo", orderNo);
            map.put("phone", phone);

            //查询该订单是否已存在
            UrgeOrderInfo urgeOrderInfo1 = urgeOrderInfoService.queryUrgeOrder(map);
            //不存在  插入
            if (StringUtils.isEmpty(urgeOrderInfo1)){

                BorrowUserInfo borrowUserInfo = new BorrowUserInfo();
                borrowUserInfo.setChannel(channel);
                borrowUserInfo.setRealName(realName);
                borrowUserInfo.setPhone(phone);
                borrowUserInfo.setSex(sex);
                borrowUserInfo.setAge(age);
                borrowUserInfo.setIdNo(idNo);
                borrowUserInfo.setUrgeNameOne(urgeNameOne);
                borrowUserInfo.setUrgeRelationOne(urgeRelationOne);
                borrowUserInfo.setUrgePhoneOne(urgePhoneOne);
                borrowUserInfo.setUrgeNameTow(urgeNameTow);
                borrowUserInfo.setUrgeRelationTow(urgeRelationTow);
                borrowUserInfo.setUrgePhoneTow(urgePhoneTow);

                UrgeOrderInfo urgeOrderInfo = new UrgeOrderInfo();
                urgeOrderInfo.setBorrowName(realName);
                urgeOrderInfo.setChannel(channel);
                urgeOrderInfo.setOrderNo(orderNo);
                urgeOrderInfo.setPhone(phone);
                urgeOrderInfo.setAmount(amount);
                urgeOrderInfo.setRealAmount(realAmount);
                urgeOrderInfo.setBorrowTime(borrowTime);
                urgeOrderInfo.setTimeLimit(timeLimit);
                urgeOrderInfo.setUnit(Integer.parseInt(unit));
                urgeOrderInfo.setRepayTime(repayTime);
                urgeOrderInfo.setPenaltyDay(penaltyDay);
                urgeOrderInfo.setPenaltyAmout(penaltyAmout);
                urgeOrderInfo.setState(Integer.parseInt(state));
                urgeOrderInfo.setLevel(level);


                int ii = urgeOrderInfoService.insert(urgeOrderInfo);
                int jj = borrowUserInfoService.insert(borrowUserInfo);
             //存在更新订单
            } else {
                map.clear();
                map.put("id", urgeOrderInfo1.getId());
                map.put("borrowName", realName);
                map.put("amount", amount);
                map.put("realAmount", realAmount);
                map.put("borrowTime", borrowTime);
                map.put("timeLimit", timeLimit);
                map.put("unit", Integer.parseInt(unit));
                map.put("repayTime", repayTime);
                map.put("penaltyDay", penaltyDay);
                map.put("penaltyAmout", penaltyAmout);
                map.put("timeLimit", timeLimit);
                map.put("state", Integer.parseInt(state));
                map.put("level", level);

                int iii = urgeOrderInfoService.updateSelective(map);
            }
        }

    }


    public void save(Object o) {
        fileUploadDao.save(o);
    }
}
