package io.github.jhipster.application.web.rest;

//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
import io.github.jhipster.application.service.FarmService;
import io.github.jhipster.application.service.dto.FarmDTO;
import jxl.read.biff.BiffException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.github.jhipster.application.service.FarmService;

/**
 * FarmDataController controller
 */
@RestController
@RequestMapping("/api/farm-data-controller")
public class FarmDataControllerResource {

    private final Logger log = LoggerFactory.getLogger(FarmDataControllerResource.class);


    @Autowired
    FarmService farmService;

    /**
    * POST create
    */
    @PostMapping("/create")
    public String create() {
        return "create";
    }

    /**
    * GET get
    */
    @GetMapping("/get")
    public String get() throws JSONException {
        List<JSONObject> list = handleExcel();
        for (int i=0; i< list.size(); i++){
            FarmDTO farmDTO = new FarmDTO();
            JSONObject item  = list.get(i);
            farmDTO.setCompanyLocation(item.getString("地址"));
            farmDTO.setCompanyLegalPerson(item.getString("法定代表人"));
            farmDTO.setCompanyName(item.getString("公司名称"));
            farmDTO.setRegisteredCapital(item.getString("注册资本"));
            farmDTO.setCompanyCreateDate(item.getString("成立日期"));
            farmDTO.setPhoneNumber(item.getString("联系电话"));
            farmDTO.setWebAddress(item.getString("企业网址"));
            farmDTO.setEmail(item.getString("邮箱"));
            farmDTO.setScope(item.getString("经营范围"));
            farmDTO.setStatus(new Long(0));

//            farmService.save(farmDTO);
        }


        return "get";

    }

    /**
    * POST update
    */
    @PostMapping("/update")
    public String update() {
        return "update";
    }




    public  List<JSONObject>  handleExcel() throws JSONException {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<JSONObject> list = null;
        String cellData = null;
        String filePath =  "/Users/zhuenqing/workspace/xiaochengxudir/farmMap/farm/farm.xlsx" ; //"D:\\test.xlsx";
        String columns[] = {"name","age","score"};
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();

            List<String> namelist = new  ArrayList<>();
            for (int j=0;j<colnum;j++){
                cellData = (String) getCellFormatValue(row.getCell(j));
                namelist.add(cellData);
            }

            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                JSONObject object = new JSONObject();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
//                        map.put(namelist.get(j), cellData);
                        object.put(namelist.get(j),cellData);

                    }
                }else{
                    break;
                }
                list.add(object);
            }
        }
        //遍历解析出来的list
        return list;

    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }


}
