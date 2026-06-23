package class6.springbootconversion.controller;

import class6.springbootconversion.domain.Assignment;
import class6.springbootconversion.dto.AssignmentCreateRequest;
import class6.springbootconversion.dto.AssignmentResponse;
import class6.springbootconversion.dto.AssignmentUpdateRequest;
import class6.springbootconversion.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 과제(Assignment) CRUD API를 제공하는 REST 컨트롤러입니다.
 */
@RestController
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    /**
     * 특정 회원에게 과제를 할당/등록합니다.
     * 
     * 요청: POST /members/{memberId}/assignments
     * 응답: 성공 시 201 Created (AssignmentResponse), 회원 미존재 시 404 Not Found
     */
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> createAssignment(
            @PathVariable Long memberId,
            @RequestBody AssignmentCreateRequest request) {
        
        Assignment assignment = assignmentService.createAssignment(memberId, request);
        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(assignment));
    }

    /**
     * 특정 회원이 제출한 과제 목록을 구합니다.
     * 
     * 요청: GET /members/{memberId}/assignments
     * 응답: 200 OK (List of AssignmentResponse)
     */
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByMember(@PathVariable Long memberId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByMemberId(memberId);
        List<AssignmentResponse> response = assignments.stream()
                .map(AssignmentResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * 과제를 개별 단건으로 조회합니다.
     * 
     * 요청: GET /assignments/{id}
     * 응답: 성공 시 200 OK (AssignmentResponse), 없을 시 404 Not Found
     */
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignment(id);
        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    /**
     * 과제 내용을 수정합니다.
     * 
     * 요청: PUT /assignments/{id}
     * 응답: 성공 시 200 OK (AssignmentResponse), 없을 시 404 Not Found
     */
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> updateAssignment(
            @PathVariable Long id,
            @RequestBody AssignmentUpdateRequest request) {
        
        Assignment updated = assignmentService.updateAssignment(id, request);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AssignmentResponse.from(updated));
    }

    /**
     * 과제를 삭제합니다.
     * 
     * 요청: DELETE /assignments/{id}
     * 응답: 성공 시 204 No Content, 삭제할 대상 과제가 없으면 404 Not Found
     */
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        boolean isDeleted = assignmentService.deleteAssignment(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
