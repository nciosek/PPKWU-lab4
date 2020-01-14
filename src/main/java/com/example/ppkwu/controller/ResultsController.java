package com.example.ppkwu.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.example.ppkwu.model.Employee;
import com.example.ppkwu.services.Base64Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ResultsController {

    @Value("${vcard.baseApiUrl}")
    private String baseApiUrl;

    @Autowired
    private Base64Service base64Service;

    private String urlEncode(String value) {

        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/results")
    public String searchEmployees(@RequestParam("query") String query, final Model model) {

        try {
            Document employeesDocument = Jsoup.connect(baseApiUrl + urlEncode(query)).get();
            Elements employeeList = employeesDocument.select(".user-info");
            List<Employee> result = new ArrayList<>();

            employeeList.forEach(employee -> {
                Employee modelEmployee = new Employee();

                modelEmployee.setFullName(employee.selectFirst("h3").text());
                modelEmployee.setOrganizationUnit(employee.selectFirst(".item-content").text());
                modelEmployee.setVcardLink(
                        "/vcard/" + base64Service.base64Encode(modelEmployee.getFullName()+"\n"+modelEmployee.getOrganizationUnit())
                );

                result.add(modelEmployee);
            });

            model.addAttribute("employees", result);
            return "results";
        } catch (Exception e) {
            model.addAttribute("exceptionType", e.getClass().getName());
            model.addAttribute("exception", e);
            return "error";
        }
    }


}
