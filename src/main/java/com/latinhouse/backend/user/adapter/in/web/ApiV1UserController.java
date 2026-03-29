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
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/whoami")
    @Operation(summary = "Get current user", description = "Returns the currently authenticated user's info")
    public ResponseEntity<UserWebResponse> whoami() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(new UserWebResponse(findUserUseCase.findByEmail(email)));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Find User", description = "by Email")
    public ResponseEntity<UserWebResponse> findByEmail(@PathVariable("email") String email) {

        UserWebResponse response = new UserWebResponse(findUserUseCase.findByEmail(email));

        return ResponseEntity.ok(response);
    }

    @GetMapping()
    @Operation(summary = "Find Users", description = "Find Users")
    public ResponseEntity<List<UserWebResponse>> findAll() {

        List<UserWebResponse> response = findUserUseCase.findAll().stream()
                .map(UserWebResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Update User")
    public ResponseEntity<UserWebResponse> update(@PathVariable("id") String id, @Valid @RequestBody UpdateUserWebRequest webReq) {

        UpdateUserAppRequest appReq = UpdateUserAppRequest.builder()
                .password(webReq.getPassword())
                .build();
        UserWebResponse response = new UserWebResponse(updateUserUseCase.update(id, appReq));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
