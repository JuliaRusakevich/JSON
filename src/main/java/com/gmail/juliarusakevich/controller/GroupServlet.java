package com.gmail.juliarusakevich.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.juliarusakevich.core.dto.group.CreateGroupDto;
import com.gmail.juliarusakevich.core.dto.group.ReadGroupDto;
import com.gmail.juliarusakevich.service.exception.ServiceException;
import com.gmail.juliarusakevich.service.GroupService;
import com.gmail.juliarusakevich.service.validator.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
doGet: обрабатывает запросы GET (получение данных)
doPost: обрабатывает запросы POST (отправка данных)
doPut: обрабатывает запросы PUT (отправка данных для изменения)
doDelete: обрабатывает запросы DELETE (удаление данных)
 */
@WebServlet(name = "GroupServlet", urlPatterns = "/groups")
public class GroupServlet extends HttpServlet {

    private final GroupService service = GroupService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        List<ReadGroupDto> list = null;
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
        var group = objectMapper.readValue(req.getInputStream(), CreateGroupDto.class);
        try {
            service.save(group);
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        } catch (ValidationException e) {
            resp.setContentType("application/json");
            var validationResult = e.getErrors();
            var jsonData = objectMapper.writeValueAsString(validationResult);
            try (var writer = resp.getWriter()) {
                writer.write(jsonData);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        try {
            var group = objectMapper.readValue(req.getInputStream(), ReadGroupDto.class);
            service.delete(group.getId());
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        var group = objectMapper.readValue(req.getInputStream(), ReadGroupDto.class);
        try {
            service.updateGroupName(group.getId(), group.getGroupName());
        } catch (ServiceException e) {
            //logger or redirect
            System.out.println(e.getMessage());
        }
    }
}
/*


 */
