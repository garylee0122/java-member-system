package com.gary.membersystem.exception;

import com.gary.membersystem.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// 全域例外處理器，相當於 ASP.NET Core 的 Exception Filter 或 PHP 的全域 try/catch 包裝層，攔截所有 Controller 拋出的例外
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 攔截 MethodArgumentNotValidException（@Valid 驗證失敗時拋出），類似 C# 的 ModelState 驗證失敗處理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // 指定回應的 HTTP 狀態碼為 400 Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );

                });

        return new ApiErrorResponse(
                false,
                "Validation Failed",
                errors
        );
    }


    // 攔截 DuplicateEmailException（建立會員時 Email 已存在），由 MemberService 拋出
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmail(
            DuplicateEmailException ex) {

        // badRequest() 等同回應 400 狀態碼，body 帶入例外訊息（"Email 已存在"），
        // 寫法上類似 C# 的 return BadRequest(ex.Message)
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }



    // 攔截 MemberNotFoundException（依 id 查無此會員時拋出），由 Service 層拋出
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFound(
            MemberNotFoundException ex) {

        // status(404) 回應 404 Not Found，body 帶入例外訊息，
        // 寫法上類似 C# 的 return NotFound(ex.Message)
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}