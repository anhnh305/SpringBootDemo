package com.savvycom.springjwt.controller;

import com.savvycom.springjwt.data.LoverDTO;
import com.savvycom.springjwt.data.Response;
import com.savvycom.springjwt.data.UserDTO;
import com.savvycom.springjwt.entity.CustomUserDetails;
import com.savvycom.springjwt.entity.Lover;
import com.savvycom.springjwt.entity.User;
import com.savvycom.springjwt.entity.service.LoverRepository;
import com.savvycom.springjwt.entity.service.LoverService;
import com.savvycom.springjwt.entity.service.UserRepository;
import com.savvycom.springjwt.jwt.JwtTokenProvider;
import com.savvycom.springjwt.payload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private LoverService loverService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoverRepository loverRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/logout")
    public RandomStuff logoutUser() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new RandomStuff("You've logged out successfully!");
    }

    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/random")
    public RandomStuff randomStuff() {
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }

    @GetMapping("/lover")
    public ResponseEntity<Response<List<LoverDTO>>> getAllLover() {
        List<LoverDTO> loverDTOList = loverService.getAlllover();
        Response<List<LoverDTO>> response = new Response<>();
        response.setMessage("Success!");
        response.setStatus(HttpStatus.OK.value());
        response.setData(loverDTOList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/wlover")
    public ResponseEntity confirmWLover(@Valid @RequestBody LoverDTO loverDTO) {
        Lover lover = new Lover();
        Response response = new Response();
        lover.setId(loverDTO.getId());
        lover.setName(loverDTO.getName());
        lover.setLover(loverDTO.getLover());
        lover.setMark(loverDTO.getMark());
        loverRepository.save(lover);
        response.setMessage("The data has been recorded");
        response.setStatus(200);
        response.setData(lover);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/dlover")
    public ResponseEntity confirmDLover(@RequestParam int id) {
        Response response = new Response();
        loverRepository.deleteById(id);
        response.setMessage("The data has been deleted");
        response.setStatus(200);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/wlover")
    public ResponseEntity confirmULover(@Valid @RequestBody LoverDTO loverDTO) {
        Lover lover = new Lover();
        Response response = new Response();
        lover.setId(loverDTO.getId());
        lover.setName(loverDTO.getName());
        lover.setLover(loverDTO.getLover());
        lover.setMark(loverDTO.getMark());
        loverRepository.save(lover);
        response.setMessage("The data has been updated");
        response.setStatus(200);
        response.setData(lover);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/reguser")
    public ResponseEntity confirmWUser(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        Response response = new Response();
        user.setId(userDTO.getId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
        response.setMessage("The data has been recorded");
        response.setStatus(200);
        response.setData(user);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/loverordname")
    public ResponseEntity<Response<List<Lover>>> getLoverOrdByName(@RequestBody LoverRequest loverRequest) {
        List<Lover> loverList = loverRepository.findLoversByLoverOrderByName(loverRequest.getCategory());
        Response<List<Lover>> response = new Response<>();
        response.setMessage("Success!");
        response.setStatus(HttpStatus.OK.value());
        response.setData(loverList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/loverpage")
    public ResponseEntity<Response<List<Lover>>> getLoverMarkOrdByName(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                       @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                                                       @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
        Sort sortable = null;
        if (sort.equals("ASC")) {
            sortable = Sort.by("name").ascending();
        }
        if (sort.equals("DESC")) {
            sortable = Sort.by("name").descending();
        }
        Pageable pageable = PageRequest.of(page, size, sortable);
        List<Lover> loverList = loverRepository.getLovers(pageable);
        Response<List<Lover>> response = new Response<>();
        response.setMessage("Success!");
        response.setStatus(HttpStatus.OK.value());
        response.setData(loverList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchlover")
    public ResponseEntity<Response<List<Lover>>> searchLoverOrd(@RequestBody SearchLoverRequest searchLoverRequest) {
        List<Lover> loverList;
        if (searchLoverRequest.getField() == "mark") {
            loverList = loverRepository.searchLoverByMark(Integer.parseInt(searchLoverRequest.getValue()), searchLoverRequest.getSort());
        } else if (searchLoverRequest.getField() == "lover") {
            loverList = loverRepository.searchLoverByLover(searchLoverRequest.getValue(), searchLoverRequest.getSort());
        } else {
            loverList = loverRepository.searchLoverByName(searchLoverRequest.getValue(), searchLoverRequest.getSort());
        }
        Response<List<Lover>> response = new Response<>();
        response.setMessage("Success!");
        response.setStatus(HttpStatus.OK.value());
        response.setData(loverList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
