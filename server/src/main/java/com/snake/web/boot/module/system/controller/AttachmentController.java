package com.snake.web.boot.module.system.controller;

import com.snake.web.boot.module.controller.BasicController;
import com.snake.web.boot.module.system.model.Attachment;
import com.snake.web.boot.module.system.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.NoSuchFileException;

/**
 * Created by HP on 2018/11/16.
 */
@Controller
@RequestMapping(value = "/attachment/")
public class AttachmentController extends BasicController {

    @Autowired
    private AttachmentRepository attachmentRepository;

    //    @ResponseBody
    @RequestMapping(value = "download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {
        Attachment attachment = attachmentRepository.getOne(id);
        if (null != attachment) {
            InputStream in = new FileInputStream(new File(attachment.getPath()));//将该文件加入到输入流之中
            byte[] body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
            in.read(body);//读入到输入流里面
            in.close();
            HttpHeaders headers = new HttpHeaders();//设置响应头
            headers.add("Content-Disposition", "attachment;filename=" + attachment.getName());
            headers.add("Content-Type", "application/octet-stream");
            headers.add("File-Name", attachment.getName());
            return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
        }
        throw new NoSuchFileException("not found this file with id : " + id);
    }

    @ResponseBody
    @RequestMapping(value = "delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        Attachment attachment = attachmentRepository.getOne(id);
        if (null != attachment) {
            File file = new File(attachment.getPath());
            file.delete();
            attachmentRepository.deleteById(id);
        }
    }

}
