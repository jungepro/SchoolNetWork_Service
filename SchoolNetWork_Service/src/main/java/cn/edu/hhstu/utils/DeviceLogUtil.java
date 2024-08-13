package cn.edu.hhstu.utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

public class DeviceLogUtil {
    public static String getDesc(int type){
        String result;
        switch (type){
            case 10:
                result = "应用创建";
                break;
            case 11:
                result = "应用下架";
                break;
            case 12:
                result = "应用上架";
                break;
            case 13:
                result = "应用设备变更";
                break;
            case 14:
                result = "应用删除";
                break;
            case 15:
                result = "二级应用创建";
                break;
            case 16:
                result = "二级应用删除";
                break;
            case 30:
                result = "虚机创建";
                break;
            case 31:
                result = "虚机下架";
                break;
            case 32:
                result = "虚机删除";
                break;
            default:
                result = "未知";
        }
        return result;
    }
}
