package org.fwx.d02_servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName D10FormUpload
 * @Description TODO
 * @Author Fwx
 * @Date 2024/3/11 19:19
 * @Version 1.0
 */
public class D10FormUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // enctype="multipart/form-data" 表单设置为此类型时，普通的获取参数值为 null
//        String username = req.getParameter("username");
//        System.out.println("username = " + username);

        DiskFileItemFactory itemFactory = new DiskFileItemFactory();

        ServletFileUpload servletFileUpload = new ServletFileUpload(itemFactory);

        try {
            List<FileItem> list = servletFileUpload.parseRequest(req);

            for (FileItem fileItem : list) {
                if(fileItem.isFormField()){
                    // 普通表单
                    String fieldName = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");

                    System.out.println("---- 普通表单");
                    System.out.println("fieldName = " + fieldName);
                    System.out.println("value = " + value);
                } else {
                    // 文件上传
                    String fieldName = fileItem.getFieldName();
                    // 获取文件名
                    String name = fileItem.getName();

                    System.out.println("---- 文件上传");
                    System.out.println("fieldName = " + fieldName);
                    System.out.println("name = " + name);
                    System.out.println("filePath:" + req.getServletContext().getRealPath("/") + File.separator + name);
                    fileItem.write(new File(req.getServletContext().getRealPath("/") + File.separator,name));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
