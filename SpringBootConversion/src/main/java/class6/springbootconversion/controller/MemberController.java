package class6.springbootconversion.controller;

import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Staff;
import class6.springbootconversion.dto.*;
import class6.springbootconversion.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 멋사 멤버 관리 시스템의 CRUD REST API를 제공하는 컨트롤러 클래스입니다.
 * 8주차 요구사항에 맞춰 모든 식별 수단을 이름(name)에서 데이터베이스 PK인 ID(Long) 기반으로 전환하였습니다.
 */
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

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
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
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
     * [8주차 추가] 모든 멤버를 조회하는 API입니다.
     * 다형성을 고려하여 각 회원 타입에 맞는 DTO(LionResponse/StaffResponse)로 변환해 리스트로 응답합니다.
     * 
     * 요청: GET /members
     * 응답: 200 OK (List of Responses)
     */
    @GetMapping
    public ResponseEntity<List<Object>> getAllMembers() {
        List<Member> members = memberService.findAllMembers();
        List<Object> responses = members.stream()
                .map(member -> {
                    if (member instanceof Lion) {
                        return LionResponse.from((Lion) member);
                    } else if (member instanceof Staff) {
                        return StaffResponse.from((Staff) member);
                    }
                    return member;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    /**
     * ID로 단일 멤버 정보를 조회하는 API입니다.
     * 
     * 요청: GET /members/{id}
     * 응답: 성공 시 200 OK (LionResponse 또는 StaffResponse), 대상을 찾지 못하면 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        if (member instanceof Lion) {
            return ResponseEntity.ok(LionResponse.from((Lion) member));
        } else if (member instanceof Staff) {
            return ResponseEntity.ok(StaffResponse.from((Staff) member));
        }

        return ResponseEntity.ok(member);
    }

    /**
     * 아기사자(Lion) 정보를 수정하는 API입니다.
     * 
     * 요청: PUT /members/lions/{id}
     * 응답: 성공 시 200 OK (LionResponse), 대상 아기사자가 없으면 404 Not Found
     */
    @PutMapping("/lions/{id}")
    public ResponseEntity<LionResponse> updateLion(
            @PathVariable Long id,
            @RequestBody LionUpdateRequest request) {
        Lion updated = memberService.updateLion(id, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LionResponse.from(updated));
    }

    /**
     * 운영진(Staff) 정보를 수정하는 API입니다.
     * 
     * 요청: PUT /members/staffs/{id}
     * 응답: 성공 시 200 OK (StaffResponse), 대상 운영진이 없으면 404 Not Found
     */
    @PutMapping("/staffs/{id}")
    public ResponseEntity<StaffResponse> updateStaff(
            @PathVariable Long id,
            @RequestBody StaffUpdateRequest request) {
        Staff updated = memberService.updateStaff(id, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StaffResponse.from(updated));
    }

    /**
     * 멤버 정보를 삭제하는 API입니다.
     * 
     * 요청: DELETE /members/{id}
     * 응답: 성공 시 204 No Content, 삭제할 대상 회원이 없으면 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean isDeleted = memberService.deleteMember(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
