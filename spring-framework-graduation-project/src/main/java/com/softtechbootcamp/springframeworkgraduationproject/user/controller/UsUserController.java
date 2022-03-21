package com.softtechbootcamp.springframeworkgraduationproject.user.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.UsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsUserController {
    private final UsUserService usUserService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UsUserSaveDto usUserSaveDto){
        UsUserDto usUserDtoSave = usUserService.saveUser(usUserSaveDto);
        return ResponseEntity.ok(RestResponse.of(usUserDtoSave));
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UsUserDto usUserDto){
       UsUserDto usUserDtoUpdate = usUserService.updateUser(usUserDto);
       return ResponseEntity.ok(RestResponse.of(usUserDtoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
       usUserService.deleteUser(id);
       return ResponseEntity.ok(RestResponse.empty());
    }

}
