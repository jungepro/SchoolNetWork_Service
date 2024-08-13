package cn.edu.hhstu.utils;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

public class WordPoiUtil {

    public static void downloadReportFile(String fileName, String tpPath, Map<String, String> replaceMap, List<String[]> tableList, HttpServletResponse response) throws IOException{
        InputStream in = null;
        XWPFDocument document = null;
        response.reset();
        response.setContentType("application/x-msdownload");
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        OutputStream os = null;
        try {
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //找到项目中模板文件的位置
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(tpPath);
            //in = new FileInputStream("E:\\DevelopDocument\\SafetyOperationSystem\\ManaProgram\\target\\classes\\tp\\app_audit_tp.docx");
            //获取docx解析对象
            document = new XWPFDocument(in);
            //document = new XWPFDocument(POIXMLDocument.openPackage("E:\\DevelopDocument\\SafetyOperationSystem\\ManaProgram\\target\\classes\\tp\\app_audit_tp.docx"));
            changeText(document, replaceMap);
            changeTable(document,replaceMap,tableList);
            os = response.getOutputStream();
            document.write(os);
            os.write(ostream.toByteArray());os.close();ostream.close();
        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try {
                if (in != null) { in.close(); }
                if (document != null) { document.close(); }
                if (os != null) { os.close(); }
                if (ostream != null) { ostream.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据模板生成新word文档
     * 对表格是进行替换以及插入
     * 模板中标记样式：${department}，最好从记事本中粘贴
     * @param inputUrl 模板存放地址
     * @param outputUrl 新文档存放地址
     * @param textMap 需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     * @return 成功返回true,失败返回false
     */
    public static boolean changWord(String inputUrl, String outputUrl,
                                    Map<String, String> textMap, List<String[]> tableList) {
        //模板转换默认成功
        boolean changeFlag = true;
        try {
            //获取docx解析对象
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
            //解析替换文本段落对象
            changeText(document, textMap);
            //解析替换表格对象
            changeTable(document, textMap, tableList);

            //生成新的word
            File file = new File(outputUrl);
            FileOutputStream stream = new FileOutputStream(file);
            document.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            changeFlag = false;
        }
        return changeFlag;

//        //模板文件地址
//        String inputUrl = "E:\\beian\\demo1.docx";
//        //新生产的模板文件
//        String outputUrl = "E:\\beian\\demo3.docx";
//
//        Map<String, String> testMap = new HashMap<String, String>();
//        testMap.put("type0", "注销");
//        testMap.put("application", "票据系统");
//        testMap.put("dept", "信息中心");
//        testMap.put("unit", "111111");
//        testMap.put("type1", "2");
//        testMap.put("type2", "3");
//        testMap.put("type3", "4");
//        testMap.put("type4", "5");
//
//        List<String[]> testList = new ArrayList<String[]>();
//        testList.add(new String[]{"1","Q1","W1","E1","R1","T1"});
//        testList.add(new String[]{"2","Q2","W2","E2","R2","T2"});
//        testList.add(new String[]{"3","Q3","W3","E3","R3","T3"});
//        testList.add(new String[]{"4","Q4","W4","E4","R4","T4"});
//        testList.add(new String[]{"5","Q5","W5","E5","R5","T5"});
//        testList.add(new String[]{"6","Q6","W6","E6","R6","T6"});
//        testList.add(new String[]{"7","Q7","W7","E7","R7","T7"});
//
//        WordPoiUtil.changWord(inputUrl, outputUrl, testMap, testList);
    }

    /**
     * 替换段落文本
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap){
        //获取段落集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if(checkText(text)){
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    run.setText(changeValue(run.toString(), textMap),0);
                }
            }
        }

    }

    /**
     * 替换表格对象方法
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     * @param tableList 需要插入的表格信息集合
     * @param tableIndex 指定word中的第几个表格，索引从0开始
     * @param startrowIndex 插入数据起始行的索引
     * @param newrowIndex 表格中插入新行位置的索引
     * @param datarowCount 原始预留数据行的位置
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap,
                                   List<String[]> tableList,int tableIndex, int startrowIndex,int newrowIndex, int datarowCount){
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        //获取第一个表格   根据实际模板情况 决定去第几个word中的表格
        XWPFTable table = tables.get(tableIndex);

        //表格中的内容替换
        List<XWPFTableRow> rows = table.getRows();
        eachTable(rows, textMap);//遍历表格,并替换模板

        //向特定位置 行插入数据
        insertTable(table, tableList,startrowIndex,newrowIndex,datarowCount);
    }
    public static void changeTable(XWPFDocument document, Map<String, String> textMap, List<String[]> tableList){
        changeTable(document,textMap,tableList,0,1,2,1);
    }
    /**
     * 遍历表格
     * @param rows 表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(List<XWPFTableRow> rows ,Map<String, String> textMap){
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if(checkText(cell.getText())){
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            run.setText(changeValue(run.toString(), textMap),0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     * @param table 需要插入数据的表格
     * @param tableList 插入数据集合
     * @param startrowIndex 插入数据起始行的索引
     * @param newrowIndex 表格中插入新行位置的索引
     * @param datarowCount 原始预留数据行的位置
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList, int startrowIndex,int newrowIndex, int datarowCount){
        //创建行,根据需要插入的数据添加新行，不处理表头
        //判断需要插入的数量
//        if (tableList.size() > 5) {//我的模板预留了五行
//            for (int i=0;i<tableList.size()-4;i++) {
//                insertRow(table,10,14);
//            }
//        }
//        //遍历表格插入数据
//        List<XWPFTableRow> rows = table.getRows();
//        for(int i = 9; i < tableList.size() + 9; i++){
//            XWPFTableRow newRow = table.getRow(i);
//            List<XWPFTableCell> cells = newRow.getTableCells();
//            for(int j = 0; j < cells.size(); j++){
//                XWPFTableCell cell = cells.get(j);
//                cell.setText(tableList.get(i-9)[j]);
//            }
//        }
        if (tableList.size() > datarowCount) {//模板预留了1行空行
            for (int i=0;i<tableList.size()-datarowCount;i++) {
                insertRow(table,startrowIndex,newrowIndex);
            }
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for(int i = startrowIndex; i < tableList.size() + startrowIndex; i++){
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for(int j = 0; j < cells.size(); j++){
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i-startrowIndex)[j]);
            }
        }
    }
    public  static  void insertTable(XWPFTable table, List<String[]> tableList){
        insertTable(table,tableList,1,2,1);
    }

    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    public static boolean checkText(String text){
        boolean check  =  false;
        if(text.indexOf("$")!= -1){
            check = true;
        }
        return check;

    }
    /**
     * insertRow 在word表格中指定位置插入一行，并将某一行的样式复制到新增行
     * @param copyrowIndex 需要复制的行位置
     * @param newrowIndex 需要新增一行的位置
     * */
    public static void insertRow(XWPFTable table, int copyrowIndex, int newrowIndex) {
        // 在表格中指定的位置新增一行
        XWPFTableRow targetRow = table.insertNewTableRow(newrowIndex);
        // 获取需要复制行对象
        XWPFTableRow copyRow = table.getRow(copyrowIndex);
        //复制行对象
        targetRow.getCtRow().setTrPr(copyRow.getCtRow().getTrPr());
        //获取需要复制的行的列
        List<XWPFTableCell> copyCells = copyRow.getTableCells();
        //复制列对象
        XWPFTableCell targetCell = null;
        for (int i = 0; i < copyCells.size(); i++) {
            XWPFTableCell copyCell = copyCells.get(i);
            targetCell = targetRow.addNewTableCell();
            targetCell.getCTTc().setTcPr(copyCell.getCTTc().getTcPr());
            if (copyCell.getParagraphs() != null && copyCell.getParagraphs().size() > 0) {
                targetCell.getParagraphs().get(0).getCTP().setPPr(copyCell.getParagraphs().get(0).getCTP().getPPr());
                if (copyCell.getParagraphs().get(0).getRuns() != null
                        && copyCell.getParagraphs().get(0).getRuns().size() > 0) {
                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                    cellR.setBold(copyCell.getParagraphs().get(0).getRuns().get(0).isBold());
                }
            }
        }

    }
    /**
     * 匹配传入信息集合与模板
     * @param value 模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap){
        Set<Entry<String, String>> textSets = textMap.entrySet();
        for (Entry<String, String> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${"+textSet.getKey()+"}";
            if(value.indexOf(key)!= -1){
                value = textSet.getValue();
            }
        }
        //模板未匹配到区域替换为空
        if(checkText(value)){
            value = "";
        }
        return value;
    }
}
