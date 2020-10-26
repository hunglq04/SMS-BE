package com.sms.be.controller;

import com.sms.be.dto.request.LoginRequest;
import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.dto.response.LoginResponse;
import com.sms.be.dto.response.ProvinceResponse;
import com.sms.be.exception.ProvinceNotFoundException;
import com.sms.be.model.District;
import com.sms.be.security.CustomUserDetails;
import com.sms.be.security.JwtTokenProvider;
import com.sms.be.service.core.DistrictService;
import com.sms.be.service.core.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(jwt, roles));
    }

    @GetMapping("/provinces")
    public List<ProvinceResponse> getAllProvinces() {
        return provinceService.getAllProvince();
    }

    @GetMapping("/provinces/{provinceId}/districts")
    public List<DistrictResponse> getDistrictAndWards(@Valid @PathVariable(name = "provinceId") Long provinceId) throws ProvinceNotFoundException {
        return districtService.getDistrictsAndWardsByProvinceId(provinceId);
    }
}
