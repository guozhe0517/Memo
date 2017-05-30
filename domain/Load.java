package com.guozhe.android.memo.domain;

import android.content.Context;

import com.guozhe.android.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by guozhe on 2017. 5. 30..
 */

public class Load {
    public static final String DIR ="/data/data/com.guozhe.android.memo/files/";
    //메모에 저장한 디렉토리를 리스팅해서
    //해당 파일의 내용 첫줄
    //해당 파일의 마지막 수정일자를 리턴한다
    static  ArrayList<Memo> datas =new ArrayList<>();
    public static ArrayList<Memo> getData(Context context){

        datas.clear();
        File dir = new File(DIR);
        File files[] = dir.listFiles();
        if(files == null)
            return datas;
        for(File file :  files){
            if(file.isFile()) {
                Memo memo = new Memo();
                memo.setId(file.getName());
                String formatted = convertLongToDate(file.lastModified());
                memo.setDate(formatted);
                String firstLine = FileUtil.readFirstLine(context, file.getName());
                memo.setContent(firstLine);


                datas.add(memo);
            }
        }

        return datas;
    }

    public static String convertLongToDate(long value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM//dd HH:mm:ss");
        return sdf.format(value);


    }


}
