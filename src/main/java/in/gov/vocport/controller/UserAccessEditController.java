package in.gov.vocport.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import in.gov.vocport.dto.SaveUserAccessRequestDto;
import in.gov.vocport.dto.UserAccessEditMenuDto;
import in.gov.vocport.service.UserAccessEditMenuService;

@RestController
public class UserAccessEditController {
	
	private final UserAccessEditMenuService menuService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public UserAccessEditController(UserAccessEditMenuService menuService) {
        this.menuService = menuService;
    }
    
    @GetMapping("/userAccessEditMenus")
    @ResponseBody
    public List<UserAccessEditMenuDto> getMenus(@RequestParam(name = "userId", required = false) String userId) {
        try {
            if (userId != null && userId.isBlank()) userId = null;
            return menuService.getMenusForUser(userId);
        } catch (SQLException ex) {
            log.error("Error fetching menus for user {}", userId, ex);
            return Collections.emptyList();
        }
    }

    @PostMapping("/editUserAccess")
    public ResponseEntity<Map<String,Object>> editUserAccess(@RequestBody SaveUserAccessRequestDto req) {
        Map<String,Object> resp = new HashMap<>();
        if (req.getUserId() == null || req.getUserId().isBlank()) {
            resp.put("success", false);
            resp.put("message", "User must be selected.");
            return ResponseEntity.badRequest().body(resp);
        }
        try {
            int inserted = menuService.editUserAccess(req.getUserId(), req.getSelections());
            log.info("Menu Inserted for user"+req.getUserId()+" is ", inserted);
            resp.put("success", true);
            resp.put("message", "User Access Edit Operation Successfully Done.");
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            log.error("Error saving user access", ex);
            resp.put("success", false);
            resp.put("message", "Error saving user access: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

}
