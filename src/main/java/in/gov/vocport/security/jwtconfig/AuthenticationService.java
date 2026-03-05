package in.gov.vocport.security.jwtconfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

@Service
public class  AuthenticationService {
    private String SECRET_KEY;

    public AuthenticationService(@Value("${jwt.secretKey}") String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    private String encodedKey;
    public String generateToken(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 43200000; // Token expires in 12 hour
        encodedKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, encodedKey)
                .compact();
    }

}
