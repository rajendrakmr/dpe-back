package in.gov.vocport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import in.gov.vocport.dto.AuthUserDto;
import in.gov.vocport.dto.MenuItemDto;
import in.gov.vocport.service.MenuService;

@ControllerAdvice
public class GlobalModelAttributes {
	
	@Autowired
	private MenuService menuService;
	
	private List<MenuItemDto> getMenu() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof AuthUserDto) {
				userId = ((AuthUserDto) principal).getUserId();
			} else if (principal instanceof String) {
				userId = (String) principal;
			} else {
				userId = authentication.getName();
			}
		}
		List<MenuItemDto> tree = menuService.getMenuForUser(userId);
		return tree;
	}
	
	@ModelAttribute
    public void addUser(Model model) {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth != null
                && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("username", auth.getName());
        }
        
        List<MenuItemDto> tree = getMenu();
		model.addAttribute("menu", tree);
    }

	@ModelAttribute
	public void addUserDisplayName(Model model) {
		// First try Security principal
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof AuthUserDto) {
			AuthUserDto u = (AuthUserDto) auth.getPrincipal();
			model.addAttribute("displayName", u.getDisplayName());
			return;
		}

		// Fallback: try session attribute (set earlier)
		try {
			Object disp = RequestContextHolder.currentRequestAttributes().getAttribute("displayName",
					RequestAttributes.SCOPE_SESSION);
			if (disp != null) {
				model.addAttribute("displayName", disp.toString());
				return;
			}
		} catch (Exception ignore) {
		}

		// Default
		model.addAttribute("displayName", "Guest");
	}
}
