package ru.vsu.csf.g7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.csf.g7.services.TestService;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    @Autowired
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @GetMapping(value = "/connectToDB")
    public ResponseEntity<String> testDB() {
        return new ResponseEntity<String>(testService.getCurrDateTime().toString(), HttpStatus.OK);
    }
}
