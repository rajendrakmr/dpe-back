package in.gov.vocport.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import in.gov.vocport.dto.SaveUserAccessRequestDto;
import in.gov.vocport.dto.UserAccessAddMenuDto;
import in.gov.vocport.service.UserAccessAddMenuService;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserAccessAddController {   // earlier service that fetches users
    private final UserAccessAddMenuService menuService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public UserAccessAddController(UserAccessAddMenuService menuService) {
        this.menuService = menuService;
    }
    
    @GetMapping("/userAccessAddMenus")
    @ResponseBody
    public List<UserAccessAddMenuDto> getMenus(@RequestParam(name = "userId", required = false) String userId) {
        try {
            if (userId != null && userId.isBlank()) userId = null;
            return menuService.getMenusForUser(userId);
        } catch (SQLException ex) {
            log.error("Error fetching menus for user {}", userId, ex);
            return Collections.emptyList();
        }
    }
    
    @PostMapping("/saveUserAccess")
    public ResponseEntity<Map<String,Object>> saveUserAccess(@RequestBody SaveUserAccessRequestDto req) {
        Map<String,Object> resp = new HashMap<>();
        if (req.getUserId() == null || req.getUserId().isBlank()) {
            resp.put("success", false);
            resp.put("message", "User must be selected.");
            return ResponseEntity.badRequest().body(resp);
        }
        if (req.getSelections() == null || req.getSelections().isEmpty()) {
            resp.put("success", false);
            resp.put("message", "At least one menu must be selected.");
            return ResponseEntity.badRequest().body(resp);
        }

        try {
            int inserted = menuService.saveUserAccess(req.getUserId(), req.getSelections());
            log.info("Menu Inserted for user"+req.getUserId()+" is ", inserted);
            resp.put("success", true);
            resp.put("message", "User Access Add Operation Successfully Done.");
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            log.error("Error saving user access", ex);
            resp.put("success", false);
            resp.put("message", "Error saving user access: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }
}