package com.snake.web.boot.module.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.config.ApiResultHandler;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.system.model.Attachment;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.AttachmentRepository;
import com.snake.web.boot.module.system.service.UserService;
import com.snake.web.boot.utils.HashMaps;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by HP on 2018/11/16.
 */
@WebServlet(urlPatterns = "/upload/" ,name = "UploadServlet")
public class UploadServlet extends HttpServlet{

    private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    @Value("${system.attachment.basePath}")
    private String basePath;
    @Value("${system.attachment.baseLink}")
    private String baseLink;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UserService userService;
    private CommonsMultipartResolver commonsMultipartResolver;

    public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CommonsMultipartResolver getCommonsMultipartResolver(){
        if( null == commonsMultipartResolver) {
            commonsMultipartResolver = new CommonsMultipartResolver();
            commonsMultipartResolver.setMaxUploadSize(1024 * 1024 * 100);
            commonsMultipartResolver.setMaxInMemorySize(1024 * 4);
            commonsMultipartResolver.setDefaultEncoding("UTF-8");
            commonsMultipartResolver.setResolveLazily(false);
        }
        return commonsMultipartResolver;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContextUtils.getWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Content-Type", "application/json");
        ObjectMapper mapper = mapperThreadLocal.get();
        Object result = null;
        try {
//        super.doPost(req, resp);
            result = upload(req, resp);
            result = ApiResultHandler.encode(result);
        }catch (Exception e){
            resp.setStatus(500);
            result = HashMaps.build().as("message", ExceptionUtils.getRootCauseMessage(e)).as("details", ExceptionUtils.getStackTrace(e));
        }

        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(mapper.writeValueAsString(result));
    }

    protected User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserByUsername(authentication.getName());
    }

    protected Collection<GrantedAuthority> loginUserConfigs() {
        return loginUser().getAuthorities();
    }

    protected Long loginUserId() {
        return loginUser().getId();
    }

    public Object upload(/*MultipartFile file,*/ HttpServletRequest request, HttpServletResponse response) throws IOException {
//        commonsMultipartResolver = getCommonsMultipartResolver();
        //检查form中是否有enctype="multipart/form-data"
        if (getCommonsMultipartResolver().isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = getCommonsMultipartResolver().resolveMultipart(request);
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());

                Long size = file.getSize();//文件大小
                String fileName = file.getOriginalFilename();//原文件名称
                String extName = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());//文件后缀名

                // 统一linux和windows的路径分隔符
                String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
                String dateTimeName = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());

                String newFileName = dateTimeName + "." + extName;
                String link = baseLink + dateName + "/" + newFileName;
                String newFilePath = basePath + dateName;

                File path = new File(newFilePath);
                if (!path.exists()) {
                    path.mkdirs();
                }
                /** 将文件存储在系统中 **/
                File newFile = new File(newFilePath, newFileName);
        /*if (!newFile.exists()) {
            newFile.createNewFile();
        }*/
                file.transferTo(newFile);//写入文件

                /** 将文件存储信息写入数据库 **/
                Attachment attachment = new Attachment();
                attachment.setOldName(fileName);
                attachment.setName(newFileName);
                attachment.setExt(extName);
                attachment.setSize(size);
                attachment.setLink(link);// 存入原文件名
                attachment.setPath(newFile.getAbsolutePath());
//                attachment.setCreateId(loginUserId());
                attachmentRepository.save(attachment);
                return attachment;
            }
        }
        return ApiResultType.SERVER_ERROR;
    }
}
