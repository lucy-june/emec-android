package com.adc.huijin.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class StorageUtils {
    //需要保存当前调用对象的Context
    private Context context;

    public StorageUtils(Context context) {
        this.context = context;
    }
    /**
     * 保存内容到内部存储器中
     * @param filename 文件名
     * @param content 内容
     */
    public void save(String filename, String content) throws IOException {
        // FileOutputStream fos=context.openFileOutput(filename,
        // Context.MODE_PRIVATE);
        File file = new File(context.getFilesDir(), filename);
        FileOutputStream fos = new FileOutputStream(file);

        fos.write(content.getBytes());
        fos.close();
    }
    /**
     *  通过文件名获取内容
     * @param filename 文件名
     * @return 文件内容
     */
    public String get(String filename) throws IOException {
        FileInputStream fis = context.openFileInput(filename);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = -1;
        while ((len = fis.read(data)) != -1) {
            baos.write(data, 0, len);
        }
        return new String(baos.toByteArray());
    }
    /**
     * 以追加的方式在文件的末尾添加内容
     * @param filename 文件名
     * @param content 追加的内容
     */
    public void append(String filename, String content) throws IOException {
        FileOutputStream fos = context.openFileOutput(filename,
                Context.MODE_APPEND);
        fos.write(content.getBytes());
        fos.close();
    }
    /**
     * 删除文件
     * @param filename 文件名
     * @return 是否成功
     */
    public boolean delete(String filename) {
        return context.deleteFile(filename);
    }
    /**
     * 获取内部存储路径下的所有文件名
     * @return 文件名数组
     */
    public String[] queryAllFile() {
        return context.fileList();
    }
    
    public boolean isExist(String filename){
    	File data = context.getFileStreamPath(filename);
    	return data.exists();  	
    }
    
    /**
	 * 保存对象
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(file, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 读取对象
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file) {
		if (!isExist(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = context.openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// 反序列化失败 - 删除缓存文件
			if (e instanceof InvalidClassException) {
				File data = context.getFileStreamPath(file);
				data.delete();
			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

}