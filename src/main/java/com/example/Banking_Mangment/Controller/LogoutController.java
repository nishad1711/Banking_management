package com.example.Banking_Mangment.Controller;

import com.example.Banking_Mangment.Service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")){
            return ResponseEntity.badRequest().body("Token missing");
        }

        String token = header.substring(7);

        logoutService.logout(token);

        return ResponseEntity.ok("Logged out successfully");
    }
}