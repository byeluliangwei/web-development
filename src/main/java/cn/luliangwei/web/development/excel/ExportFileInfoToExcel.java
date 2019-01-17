package cn.luliangwei.web.development.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportFileInfoToExcel {

    public static void main(String[] args) {
        
        export(getInfos("d://助教//实验三"),"d://助教//实验三.xls");
    }
    
    //将需要导出的信息导出到目标文件中
    public static void export(List<FileInfo> infos,String destPath) {
        @SuppressWarnings("resource")
        //对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //对应文件中的一个sheet表
        HSSFSheet sheet = workbook.createSheet("实验一成绩汇总");
        //sheet表中的第0行
        HSSFRow row = sheet.createRow(0);
        //创建单元格，第0列，第1列...
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("实验一");
        
        //数据写入excel表中
        for(int i = 0 ; i< infos.size();i++) {
            HSSFRow row_i = sheet.createRow(i+1);
            row_i.createCell(0).setCellValue(infos.get(i).num);
            row_i.createCell(1).setCellValue(infos.get(i).name);
            row_i.createCell(2).setCellValue(infos.get(i).course);
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(destPath);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //组装需要存储的数据
    public static List<FileInfo> getInfos(String dirPath) {
        File file = new File(dirPath);
        File[] files = file.listFiles();
        List<FileInfo> infos = new ArrayList<>();
        for(File f: files) {
            if(f.exists()) {
                String fileName = f.getName().replaceAll("\\.doc[x]?", "");
                String[] info = fileName.split("_");
                FileInfo fi = new FileInfo(info[0], info[1], info[2]);
                infos.add(fi);
            }
        }
        return infos;
    }
    
    
    public static class FileInfo{
        private String num;
        private String name;
        private String course;
        
        public FileInfo(String num, String name, String course) {
            super();
            this.num = num;
            this.name = name;
            this.course = course;
        }
        public String getNum() {
            return num;
        }
        public void setNum(String num) {
            this.num = num;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getCourse() {
            return course;
        }
        public void setCourse(String course) {
            this.course = course;
        }
        
        
    }
}
