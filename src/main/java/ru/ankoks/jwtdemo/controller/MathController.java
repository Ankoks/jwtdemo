package ru.ankoks.jwtdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User: ankoks
 * Date: 28/01/2019
 */
@RestController
@RequestMapping(value = "/api/v1/arithmetic/")
public class MathController {

    @GetMapping(value = "{n}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public String getSumm(@PathVariable("n") Integer n) {
        long before = System.nanoTime();
        int summ = arithmeticProgression(n);
        System.out.println("evaluate summ = " + (System.nanoTime() - before) + " ms");

        List<? extends GrantedAuthority> collect = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .collect(Collectors.toList());

        return String.format("summ is %s. Current user has roles {%s}", summ, collect);
    }

    private int arithmeticProgression(int n) {
        System.out.println("Executing evaluate progression with n = " + n);
        int summ = 0;
        for (int i = 0; i < n; i++) {
            summ += i;
        }

        return summ;
    }
}
