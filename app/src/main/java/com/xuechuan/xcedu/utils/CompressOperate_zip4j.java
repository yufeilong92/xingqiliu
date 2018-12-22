package com.xuechuan.xcedu.utils;

import android.util.Log;

import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.utils
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.12.05 下午 3:53
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */

/**
 * Created by WangChaowei on 2017/12/27.
 * <p>
 * 此类是用第三方开源的zip4j操作文件（目录）的压缩、解压、加解密
 */
public class CompressOperate_zip4j {

    private volatile static CompressOperate_zip4j _instance;

    private CompressOperate_zip4j() {
    }

    public static CompressOperate_zip4j get_Instance() {
        if (_instance == null) {
            synchronized (CompressOperate_zip4j.class) {
                if (_instance == null) {
                    _instance = new CompressOperate_zip4j();
                }
            }
        }
        return _instance;
    }

    private ZipFile zipFile;
    private ZipParameters zipParameters;
    private int result = 0; //状态返回值

    private static final String TAG = "CompressOperate_zip4j";

    /**
     * zip4j压缩
     *
     * @param filePath    要压缩的文件路径（可文件，可目录）
     * @param zipFilePath zip生成的文件路径
     * @param password    密码
     * @return 状态返回值
     */
    public int compressZip4j(String filePath, String zipFilePath, String password) {
        File sourceFile = new File(filePath);
        File zipFile_ = new File(zipFilePath);
        try {
            zipFile = new ZipFile(zipFile_);
            zipFile.setFileNameCharset("GBK"); //设置编码格式（支持中文）

            zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); //压缩方式
            zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
            if (password != null && password != "") {  //是否要加密(加密会影响压缩速度)
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
                zipParameters.setPassword(password.toCharArray());
            }

            if (zipFile_.isDirectory()) {
                String sourceFileName = checkString(sourceFile.getName()); //文件校验
                zipFilePath = zipFilePath + "/" + sourceFileName + ".zip";
//                Log.i(TAG, "保存压缩文件的路径(zipFilePath)：" + zipFilePath);
                compressZip4j(filePath, zipFilePath, password);
            }
            if (sourceFile.isDirectory()) {
                // File[] files = sourceFile.listFiles();
                // ArrayList<File> arrayList = new ArrayList<File>();
                // Collections.addAll(arrayList, files);
                zipFile.addFolder(sourceFile, zipParameters);
            } else {
                zipFile.addFile(sourceFile, zipParameters);
            }
//                Log.i(TAG, "compressZip4j: 压缩成功");
        } catch (ZipException e) {
//                Log.e(TAG, "compressZip4j: 异常：" + e);
            result = -1;
            return result;
        }
        return result;
    }

    /**
     * 校验提取出的原文件名字是否带格式
     *
     * @param sourceFileName 要压缩的文件名
     * @return
     */
    private String checkString(String sourceFileName) {
        if (sourceFileName.indexOf(".") > 0) {
            sourceFileName = sourceFileName.substring(0, sourceFileName.length() - 4);
//                Log.i(TAG, "checkString: 校验过的sourceFileName是：" + sourceFileName);
        }
        return sourceFileName;
    }

    public interface InfaceZip {
        public void UnZipNameSuccess(String path,String name);

        public void UnZipNameError(String path);
    }

    /**
     * zip4j解压
     *
     * @param zipFilePath 待解压的zip文件（目录）路径
     * @param filePath    解压到的保存路径
     * @param password    密码
     * @return 状态返回值
     */
    public int uncompressZip4j(String zipFilePath, String filePath, String password, InfaceZip infaceZip) {
        File zipFile_ = new File(zipFilePath);
        File sourceFile = new File(filePath);

        try {
            zipFile = new ZipFile(zipFile_);
            zipFile.setFileNameCharset("GBK"); //设置编码格式（支持中文）
            if (!zipFile.isValidZipFile()) {   //检查输入的zip文件是否是有效的zip文件
                throw new ZipException("压缩文件不合法,可能被损坏.");
            }
            if (sourceFile.isDirectory() && !sourceFile.exists()) {
                sourceFile.mkdir();
            }
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(filePath); //解压
//                Log.i(TAG, "uncompressZip4j: 解压成功");
            Log.e(TAG, "uncompressZip4j: " + zipFile.getComment());
            infaceZip.UnZipNameSuccess(filePath.concat(zipFile.getProgressMonitor().getFileName()),zipFile.getProgressMonitor().getFileName());
        } catch (ZipException e) {
//                Log.e(TAG, "uncompressZip4j: 异常：" + e);
            result = -1;
            infaceZip.UnZipNameError(zipFilePath);
            return result;
        }
        return result;
    }
}
