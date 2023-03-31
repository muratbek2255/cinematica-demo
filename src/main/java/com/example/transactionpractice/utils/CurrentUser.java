package com.example.transactionpractice.utils;


import java.lang.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Target({ElementType.PARAMETER, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface CurrentUser {
}
