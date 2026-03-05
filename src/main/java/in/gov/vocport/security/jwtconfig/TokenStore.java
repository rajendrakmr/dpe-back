package in.gov.vocport.security.jwtconfig;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenStore {
    private final Map<String, String> userTokenMap = new ConcurrentHashMap<>();

    public void storeToken(String loginId, String token) {
        userTokenMap.put(loginId, token);
    }

    public boolean isPresentToken(String loginId) {
        return userTokenMap.containsKey(loginId);
    }

    public String getToken(String loginId) {
        return userTokenMap.get(loginId);
    }

    public void removeToken(String username) {
        userTokenMap.remove(username);
    }
}
