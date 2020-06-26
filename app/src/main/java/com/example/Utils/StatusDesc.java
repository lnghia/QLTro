package com.example.Utils;

import java.util.HashMap;

public class StatusDesc {
    private HashMap<Integer, String> errs;
    private static StatusDesc instance;

    public StatusDesc(){
        errs=new HashMap<>();

        errs.put(200, "Thao tác thành công");
        errs.put(201, "Thêm thành công");
        errs.put(409, "Đã tồn tại dữ liệu này trong hệ thống");
    }

    public static StatusDesc getInstance(){
        if(instance==null){
            instance=new StatusDesc();
        }

        return instance;
    }

    public String getErrDesc(int code){
        return errs.get(code);
    }
}
