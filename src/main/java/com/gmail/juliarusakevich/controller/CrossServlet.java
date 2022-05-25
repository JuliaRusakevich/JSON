package com.gmail.juliarusakevich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.juliarusakevich.dao.entity.info.InfoGroup;
import com.gmail.juliarusakevich.dao.entity.info.StudentInGroup;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.CrossService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CrossServlet", urlPatterns = "/cross")
public class CrossServlet extends HttpServlet {

    private final CrossService service = CrossService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var group = objectMapper.readValue(req.getInputStream(), InfoGroup.class);
        try {
            service.addStudentSToGroup(group);
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var group = objectMapper.readValue(req.getInputStream(), InfoGroup.class);
        try {
            service.deleteStudentSFromGroup(group);
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        List<StudentInGroup> list = null;
        try {
            list = service.returnStudentsAndGroup();
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
        var jsonData = objectMapper.writeValueAsString(list);
        try (var writer = resp.getWriter()) {
            writer.write(jsonData);
        }
    }
}
