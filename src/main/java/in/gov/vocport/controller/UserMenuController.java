package in.gov.vocport.controller;

import in.gov.vocport.service.UserMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/add-edit")
@RequiredArgsConstructor
public class UserMenuController {
    private final UserMenuService userMenuService;

    @GetMapping("/menu")
    public ResponseEntity getMenusForAddEdit(@RequestParam String userId, @RequestParam boolean isAdd) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        userMenuService.getMenuAccessForAddEdit(userId, isAdd, result);
        return ResponseEntity.ok(result);
    }
}
