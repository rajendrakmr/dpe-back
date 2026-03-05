package in.gov.vocport.service;

import in.gov.vocport.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserMenuService {
    private final MenuService menuService;
    private final UserAccessAddMenuService userAccessAddMenuService;
    private final UserAccessEditMenuService userAccessEditMenuService;

    public void getMenuAccessForAddEdit(String userId, boolean isAdd, Map<String, Object> result) throws SQLException {
        List<AllMenuDto> allMenuRegardlessUserAccess = menuService.getAllMenus();
//        Map<String, AddEditMenuAccessDTO> leafMenus = new HashMap<>();

        if (isAdd) {
            List<AddEditMenuAccessDTO> finalResult = new ArrayList<>();
            prepareLeafMenus(allMenuRegardlessUserAccess, finalResult);
            finalResult.sort(Comparator.comparing(AddEditMenuAccessDTO::getPath));
//            List<UserAccessAddMenuDto> getMenusForAddUser = userAccessAddMenuService.getMenusForUser(userId);
            result.put("success", finalResult);
        } else {
            List<AddEditMenuAccessDTO> finalResult = new ArrayList<>();
            prepareLeafMenus(allMenuRegardlessUserAccess, finalResult);
            List<UserAccessEditMenuDto> menusByUser = userAccessEditMenuService.getMenusForUser(userId);
            Map<String, UserAccessEditMenuDto> userAccessEditMenuDtoMap = new HashMap<>();
            prepareMenusAlredyGivenForUser(menusByUser, userAccessEditMenuDtoMap);
            finalResult.forEach(finalMenu -> {
                if (userAccessEditMenuDtoMap.containsKey(finalMenu.getMenuId())) {
                    finalMenu.setChecked(1);
                }
            });
            finalResult.sort(Comparator.comparing(AddEditMenuAccessDTO::getPath));
            result.put("success", finalResult);
        }
    }

    private void prepareMenusAlredyGivenForUser(List<UserAccessEditMenuDto> menusByUser, Map<String, UserAccessEditMenuDto> userAccessEditMenuDtoMap) {
        menusByUser.forEach(menu -> {
            if (menu.getChkField() != null && menu.getChkField().equalsIgnoreCase("Checked")) {
                userAccessEditMenuDtoMap.put(menu.getMenuId(), menu);
            }
        });
    }

    private void prepareLeafMenus(List<AllMenuDto> allMenuRegardlessUserAccess, List<AddEditMenuAccessDTO> leafMenus) {
        allMenuRegardlessUserAccess.forEach(menu -> {
            AddEditMenuAccessDTO addEditMenuAccessDTO = new AddEditMenuAccessDTO();
            BeanUtils.copyProperties(menu, addEditMenuAccessDTO);
            leafMenus.add(addEditMenuAccessDTO);
        });
    }
}
