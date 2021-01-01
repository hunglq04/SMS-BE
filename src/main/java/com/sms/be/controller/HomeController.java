package com.sms.be.controller;

import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.AccountDto;
import com.sms.be.dto.MailDto;
import com.sms.be.dto.request.RegisterRequest;
import com.sms.be.dto.response.DistrictResponse;
import com.sms.be.dto.response.LoginResponse;
import com.sms.be.dto.response.ProvinceResponse;
import com.sms.be.exception.ProvinceNotFoundException;
import com.sms.be.model.Account;
import com.sms.be.model.Booking;
import com.sms.be.model.Customer;
import com.sms.be.model.Employee;
import com.sms.be.model.Setting;
import com.sms.be.repository.AccountRepository;
import com.sms.be.repository.CustomerRepository;
import com.sms.be.repository.EmployeeRepository;
import com.sms.be.security.CustomUserDetails;
import com.sms.be.security.JwtTokenProvider;
import com.sms.be.service.ClientService;
import com.sms.be.service.core.AccountService;
import com.sms.be.service.core.DistrictService;
import com.sms.be.service.core.ProvinceService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody AccountDto accountDto) {
        return authenticateAccount(accountDto);
    }

    @PostMapping("/login/social")
    public ResponseEntity<LoginResponse> loginSocial(@Valid @RequestBody RegisterRequest request) {
        return authenticateAccount(accountService.loginSocial(request));
    }

    @GetMapping("/provinces")
    public List<ProvinceResponse> getAllProvinces() {
        return provinceService.getAllProvince();
    }

    @GetMapping("/provinces/{provinceId}/districts")
    public List<DistrictResponse> getDistrictAndWards(@Valid @PathVariable(name = "provinceId") Long provinceId) throws ProvinceNotFoundException {
        return districtService.getDistrictsAndWardsByProvinceId(provinceId);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        accountService.createNewAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/send-mail")
    public ResponseEntity<Void> sendMail(@Valid @RequestBody MailDto request) {
        clientService.sendMail(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/send-sms")
    public ResponseEntity<Void> sendSMS(String toNumber, String content) {
            Twilio.init(CommonConstants.ACCOUNT_SID,CommonConstants. AUTH_TOKEN);
            Message.creator(new PhoneNumber(toNumber), // to
                    new PhoneNumber(CommonConstants.FROM_PHONE_NUMBER), // from
                    content).create();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private ResponseEntity<LoginResponse> authenticateAccount(@RequestBody @Valid AccountDto accountDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountDto.getUsername(),
                        accountDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Account account = accountRepository.findByUsername(accountDto.getUsername())
                .orElse(new Account());
        String name = StringUtils.EMPTY;
        String avatar = StringUtils.EMPTY;
        String email = StringUtils.EMPTY;
        String phone = StringUtils.EMPTY;
        String address = StringUtils.EMPTY;
        Optional<Customer> customer = customerRepository.findByAccount(account);
        if (customer.isPresent()) {
            name = customer.get().getName();
            email = customer.get().getEmail();
            phone = customer.get().getPhoneNumber();
            address = customer.get().getAddress();
        } else {
            Optional<Employee> employee = employeeRepository.findByAccount(account);
            if (employee.isPresent()) {
                name = employee.get().getName();
                avatar = employee.get().getAvatar();
                email = employee.get().getIdCard();
                phone = employee.get().getPhoneNumber();
                address = employee.get().getAddress();
            }
        }

        return ResponseEntity.ok(new LoginResponse(jwt, roles, name, avatar, phone , email, address));
    }
}
