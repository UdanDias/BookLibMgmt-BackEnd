package lk.uok.cs.bodimsangayo.LibMgmt2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthCheck {
    @GetMapping
    public String healthTest(){
        return"Health check is running";
    }
}
