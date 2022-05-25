package com.gmail.juliarusakevich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.juliarusakevich.core.dto.student.CreateStudentDto;
import com.gmail.juliarusakevich.core.dto.student.ReadStudentDto;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.StudentService;
import com.gmail.juliarusakevich.service.validator.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private final StudentService service = StudentService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        List<ReadStudentDto> list = null;
        try {
            list = service.find();
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
        var jsonData = objectMapper.writeValueAsString(list);
        try (var writer = resp.getWriter()) {
            writer.write(jsonData);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var student = objectMapper.readValue(req.getInputStream(), CreateStudentDto.class);
        try {
            service.save(student);
        } catch (ValidationException e) {
            resp.setContentType("application/json"); //передаем пользователю ошибки о невалидных введенных данных
            var validationResult = e.getErrors();
            var jsonData = objectMapper.writeValueAsString(validationResult);
            try (var writer = resp.getWriter()) {
                writer.write(jsonData);
            }
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var student = objectMapper.readValue(req.getInputStream(), ReadStudentDto.class);
        try {
            service.delete(student.getId());
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var dto = objectMapper.readValue(req.getInputStream(), ReadStudentDto.class);
        try {
            service.updateStudentInfo(dto.getId(), dto);
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }
}

