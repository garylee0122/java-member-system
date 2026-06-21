package com.gary.membersystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity                       // 宣告此類別為 JPA 實體，對應資料庫中的一張資料表
@Table(name = "members")      // 指定對應的資料表名稱為 "members"
@Getter                       // Lombok：自動產生所有欄位的 getter 方法，省去手動撰寫
@Setter                       // Lombok：自動產生所有欄位的 setter 方法，省去手動撰寫
@NoArgsConstructor
public class Member {

    @Id                                                    // 標示此欄位為資料表的主鍵 (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 主鍵由資料庫自動遞增產生 (AUTO_INCREMENT)
    private Long id;

    private String name;

    @Column(unique = true)   // 指定此欄位在資料表中需有唯一性約束 (UNIQUE)，相當於 SQL 的 UNIQUE constraint
    private String email;

}