package com.gary.membersystem.repository;

import com.gary.membersystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository<T, ID> 說明：
 *   T  = 操作的 Entity 類別，這裡是 Member
 *   ID = 該 Entity 主鍵的型別，這裡是 Long（對應 Member.id）
 *
 * 只要 extends JpaRepository，Spring Data JPA 會在啟動時自動產生實作，
 * 不需要自己寫任何 SQL 或 JDBC 程式碼，即可直接使用以下內建方法：
 *
 * ── 新增 / 修改 ──────────────────────────────────────────────
 *   save(member)            → 新增或更新一筆（有 id 就 UPDATE，沒有就 INSERT）
 *   saveAll(list)           → 批次新增或更新多筆
 *
 * ── 查詢 ────────────────────────────────────────────────────
 *   findById(id)            → 依主鍵查詢，回傳 Optional<Member>
 *   findAll()               → 查詢全部，回傳 List<Member>
 *   findAll(Sort.by("name"))→ 查詢全部並排序
 *   findAll(PageRequest)    → 分頁查詢，回傳 Page<Member>
 *   findAllById(ids)        → 依多個主鍵查詢
 *   existsById(id)          → 判斷某筆資料是否存在，回傳 boolean
 *   count()                 → 取得總筆數
 *
 * ── 刪除 ────────────────────────────────────────────────────
 *   deleteById(id)          → 依主鍵刪除一筆
 *   delete(member)          → 刪除傳入的 Entity 物件
 *   deleteAll()             → 刪除全部
 *
 * ── 自訂查詢（在此 interface 內直接新增方法即可）─────────────
 *   findByName(String name)           → Spring 依方法名稱自動產生 WHERE name = ?
 *   findByEmail(String email)         → 同上，依 email 查詢
 *   findByNameContaining(String keyword) → 模糊查詢 LIKE %keyword%
 */
public interface MemberRepository
        extends JpaRepository<Member, Long> {

    // 範例：若需要依 name 查詢，只要在這裡宣告方法簽名，不用寫實作
    // List<Member> findByName(String name);

    //依 email 查詢，JPA 會自己組 SQL 語法，不用寫實作
    Optional<Member> findByEmail(String email);
}