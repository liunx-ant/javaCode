package com.liunx.createcode.util;  
  
import java.io.File;

import org.apache.tools.ant.Project;    
import org.apache.tools.ant.taskdefs.Zip;    
import org.apache.tools.ant.types.FileSet;    
    
/** 
 * @author : liub 
 * @Description: 压缩文件的通用工具类-采用ant中的org.apache.tools.ant.taskdefs.Zip来实现，更加简单。 
 */  
public class FileToZip {    
    
    private File zipFile;    
    
    /** 
     * 压缩文件构造函数 
     * @param pathName 最终压缩生成的压缩文件：目录+压缩文件名.zip 
     */  
    public FileToZip(String finalFile) {    
        zipFile = new File(finalFile);    
    }    
        
    /** 
     * 执行压缩操作 
     * @param srcPathName 需要被压缩的文件/文件夹 
     */  
    public void compressExe(String srcPathName,Boolean isFirst) {    
        Project prj = new Project();    
        Zip zip = new Zip();    
        zip.setProject(prj);    
        zip.setDestFile(zipFile);  
        if(isFirst){
            FileSet fileSet = new FileSet();    
            fileSet.setProject(prj);    
            String rootPath=srcPathName.substring(0,srcPathName.indexOf("created"));
            fileSet.setDir(new File(rootPath+File.separator+"project_frame"));  
            //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");    
            //fileSet.setExcludes(...); //排除哪些文件或文件夹    
            zip.addFileset(fileSet); 
        }
        FileSet fileSet1 = new FileSet();
        fileSet1.setProject(prj);    
        fileSet1.setDir(new File(srcPathName));   
        zip.addFileset(fileSet1);    
        zip.execute();   
    }    
    
    public static void main(String[] args) {  
//        FileToZip zca = new FileToZip("d:\\testliub.zip");  
//        zca.compressExe("E:\\yihe\\whou\\maven.1470272550494\\trunk\\src\\main\\webapp\\HM_dUmVfUp2v3\\code");  
    }  
}   