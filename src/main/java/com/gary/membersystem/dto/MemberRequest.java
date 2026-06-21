package com.gary.membersystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//為什麼業界通常不用 entity 來接 request 或直接 response?
//原因：Entity 是資料庫模型，DTO 是 API 模型。
//如果未來資料庫增加欄位，但 request 或 response 並不需要新增的欄位

@Getter   // Lombok：自動產生所有欄位的 getter 方法，省去手動撰寫
@Setter   // Lombok：自動產生所有欄位的 setter 方法，省去手動撰寫
public class MemberRequest {

    @NotBlank(message = "Name cannot be blank")   // 驗證欄位不可為空白（null 或空字串），不符合時回傳指定訊息
    private String name;

    @NotBlank(message = "Email cannot be blank")  // 驗證欄位不可為空白（null 或空字串），不符合時回傳指定訊息
    @Email(message = "Invalid email format")      // 驗證欄位格式必須符合 Email 規則（含 @ 與網域），不符合時回傳指定訊息
    private String email;

}