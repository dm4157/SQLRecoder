package org.syy.sqlrecoder.util;

import java.io.*;
import java.util.Properties;

/** 
 * @ClassName: PropertiesHelper 
 * @PackageName: com.chinasou.mrp.java.util
 * @ProjectName: MediaResourcePlatform
 *
 * @Description: 配置文件操作工具
 *
 * @author syy
 * @date 2013-11-27 上午11:17:32 
 * @version V1.0  
 */ 
public class PropertiesHelper {
    
	/** 
	 * @Fields pros : 配置文件
	 */ 
	private Properties pros;
	
	/** 
	 * @Fields file : 配置文件名
	 */ 
	private String file;
	
	public PropertiesHelper(String property){
	    this.file = property;
		pros = new Properties();
		try {
		    InputStream in = getClass().getResourceAsStream(property);
			pros.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /** 
	 * @Title: getVale
	 * @Description: 获得配置文件值
	 * @param key
	 * @return
	 */ 
	public String getValue(String key){
		return pros.getProperty(key,"");
	}
	
	public int getIntValue(String key) {
	    return Integer.parseInt(getValue(key));
	}

	public float getFloatValue(String key) {
	    return Float.parseFloat(getValue(key));
	}

	public double getDoubleValue(String key) {
	    return Double.parseDouble(getValue(key));
	}
	
	public long getLongValue(String key) {
	    return Long.parseLong(getValue(key));
	}
	
	public byte getByteValue(String key) {
	    return Byte.parseByte(getValue(key));
	}
	
	public char getCharValue(String key) {
	    return getValue(key).charAt(0);
	}
	
	public boolean getBooleanValue(String key) {
	    return Boolean.parseBoolean(getValue(key));
	}
	
	/** 
	 * @Title: setValue
	 * @Description: 设置配置文件
	 * @param key
	 * @param value
	 */ 
	public void setValue(String key, String value) {
	    pros.setProperty(key, value);
	}
	
	/** 
	 * @Title: save
	 * @Description: 保存修改的配置到配置文件中去
	 */ 
	public void save() {
	    try {
            FileOutputStream out = new FileOutputStream(file);
            pros.store(out, "Copyright (c) ChinaSousuo");
            out.close();
	    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
