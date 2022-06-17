package th.co.mfec.api.security.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.exception.BaseException;

@Component
public class JwtUtil {

    @Value("${api.jwt.secrete}")
    private String secrete;
    @Value("${api.jwt.expired}")
    private String expired;

    public String generateToken(String username){
        Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * Integer.parseInt(expired));
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, secrete)
            .compact();
    }

    public void validateToken(String token){
        try{
            Jwts.parser()
                .setSigningKey(secrete)
                .parseClaimsJws(token);
        }catch (ExpiredJwtException ex){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }catch (Exception ex) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }
    }

    public String getUsernameFromJwt(String token){
        return Jwts.parser()
                .setSigningKey(secrete)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}
