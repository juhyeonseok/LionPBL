package class6.springbootconversion.controller;

import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Staff;
import class6.springbootconversion.dto.*;
import class6.springbootconversion.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 멋사 멤버 관리 시스템의 CRUD REST API를 제공하는 컨트롤러 클래스입니다.
 * 
 * `@RestController`을 사용하여 JSON 형식으로 응답 데이터를 반환하고,
 * `@RequestMapping("/members")`을 설정하여 공통 URI 접두사를 정의합니다.
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 의존성 주입을 위한 생성자입니다.
     * 생성자가 단 하나이므로 스프링이 자동으로 `@Autowired`를 처리하여 MemberService 빈을 주입합니다.
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 아기사자(Lion)를 등록하는 API입니다.
     * 
     * 요청: POST /members/lions
     * 응답: 성공 시 201 Created (LionResponse), 이름 중복 시 409 Conflict
     */
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Lion lion = memberService.createLion(request);
        if (lion == null) {
            // 이름 중복 시 409 Conflict 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // 생성 성공 시 201 Created와 함께 생성된 DTO 데이터 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from(lion));
    }

    /**
     * 운영진(Staff)을 등록하는 API입니다.
     * 
     * 요청: POST /members/staffs
     * 응답: 성공 시 201 Created (StaffResponse), 이름 중복 시 409 Conflict
     */
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Staff staff = memberService.createStaff(request);
        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from(staff));
    }

    /**
     * 이름으로 단일 멤버 정보를 조회하는 API입니다.
     * 다형성을 기반으로 반환 타입을 런타임에 판별하여 알맞은 DTO로 변환하여 응답합니다.
     * 
     * 요청: GET /members/{name}
     * 응답: 성공 시 200 OK (LionResponse 또는 StaffResponse), 대상을 찾지 못하면 404 Not Found
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Member member = memberService.findMember(name);
        if (member == null) {
            // 회원이 존재하지 않는 경우 404 Not Found 반환
            return ResponseEntity.notFound().build();
        }

        // 다형성을 확인하여 하위 클래스 타입에 맞춰 응답을 분기합니다.
        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else if (member instanceof Staff) {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }

        return ResponseEntity.ok(member); // 기본 처리
    }

    /**
     * 아기사자(Lion) 정보를 수정하는 API입니다.
     * 
     * 요청: PUT /members/lions/{name}
     * 응답: 성공 시 200 OK (LionResponse), 대상 아기사자가 없으면 404 Not Found
     */
    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable String name,
            @RequestBody LionUpdateRequest request) {
        Lion updated = memberService.updateLion(name, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LionResponse.from(updated));
    }

    /**
     * 운영진(Staff) 정보를 수정하는 API입니다.
     * 
     * 요청: PUT /members/staffs/{name}
     * 응답: 성공 시 200 OK (StaffResponse), 대상 운영진이 없으면 404 Not Found
     */
    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable String name,
            @RequestBody StaffUpdateRequest request) {
        Staff updated = memberService.updateStaff(name, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StaffResponse.from(updated));
    }

    /**
     * 멤버 정보를 삭제하는 API입니다.
     * 
     * 요청: DELETE /members/{name}
     * 응답: 성공 시 204 No Content, 삭제할 대상 회원이 없으면 404 Not Found
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        boolean isDeleted = memberService.deleteMember(name);
        if (!isDeleted) {
            // 삭제할 회원이 없으면 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // 삭제 성공 시 204 No Content 반환
        return ResponseEntity.noContent().build();
    }
}
