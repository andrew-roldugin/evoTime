package ru.csf.vsu.g7.evotime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.csf.vsu.g7.evotime.services.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping(value = "/test-db")
    public String testDB() {
        return testService.getCurrDate().toString();
    }
}
