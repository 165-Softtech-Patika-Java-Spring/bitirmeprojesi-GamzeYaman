package com.softtechbootcamp.springframeworkgraduationproject.user.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.user.dto.UsUserDto;
import com.softtechbootcamp.springframeworkgraduationproject.user.service.UsUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsUserController {
    private final UsUserService usUserService;

    @Operation(
            tags = "User Controller",
            description = "Update user informations",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsUserDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update user",
                                                    description = "Complete request with all available fields for user",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"name\": \"Levi\",\n" +
                                                            "  \"surname\": \"Ackerman\",\n" +
                                                            "  \"username\": \"LeviHeichou\",\n" +
                                                            "  \"userPassword\": \"levi84\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping
    public ResponseEntity updateUser(@RequestBody UsUserDto usUserDto){
       UsUserDto usUserDtoUpdate = usUserService.updateUser(usUserDto);
       return ResponseEntity.ok(RestResponse.of(usUserDtoUpdate));
    }

    @Operation(
            tags = "User Controller",
            description = "Delete user."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
       usUserService.deleteUser(id);
       return ResponseEntity.ok(RestResponse.empty());
    }

}
