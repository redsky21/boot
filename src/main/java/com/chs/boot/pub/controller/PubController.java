package com.chs.boot.pub.controller;

import com.chs.boot.pub.model.PubBaseDTO;
import com.chs.boot.pub.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class PubController {
    @Autowired
    PubService pubService;

    @PostMapping(path = "/generateHtml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void generateHtml(@RequestBody PubBaseDTO pubBaseDTO,HttpServletResponse response) throws IOException {
        String htmlFileString = pubService.getHtmlString(pubBaseDTO);
        byte[] fileByte = htmlFileString.getBytes(StandardCharsets.UTF_8);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("tmp.html", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }
}
