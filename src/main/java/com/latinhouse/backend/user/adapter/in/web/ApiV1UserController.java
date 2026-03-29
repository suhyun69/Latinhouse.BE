package com.latinhouse.backend.user.adapter.in.web;

import com.latinhouse.backend.user.adapter.in.web.request.UpdateUserWebRequest;
import com.latinhouse.backend.user.adapter.in.web.response.UserWebResponse;
import com.latinhouse.backend.user.port.in.FindUserUseCase;
import com.latinhouse.backend.user.port.in.UpdateUserUseCase;
import com.latinhouse.backend.user.port.in.request.UpdateUserAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API Document")
@RequiredArgsConstructor
public class ApiV1UserController {

    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @GetMapping("/{email}")
    @Operation(summary = "Find User", description = "by Email")
    public ResponseEntity<UserWebResponse> findByEmail(@RequestParam("email") String email) {

        UserWebResponse response = new UserWebResponse(findUserUseCase.findByEmail(email));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping()
    @Operation(summary = "Find Users", description = "Find Users")
    public ResponseEntity<List<UserWebResponse>> findAll() {

        List<UserWebResponse> response = findUserUseCase.findAll().stream()
                .map(UserWebResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Update User")
    public ResponseEntity<UserWebResponse> update(@RequestParam("id") String id, @Valid @RequestBody UpdateUserWebRequest webReq) {

        UpdateUserAppRequest appReq = UpdateUserAppRequest.builder()
                .password(webReq.getPassword())
                .build();
        UserWebResponse response = new UserWebResponse(updateUserUseCase.update(id, appReq));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
