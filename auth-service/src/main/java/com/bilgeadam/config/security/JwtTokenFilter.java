package com.bilgeadam.config.security;

import com.bilgeadam.utility.JwtEncodeDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetail jwtUserDetail;

    @Autowired
    JwtEncodeDecode jwtEncodeDecode;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * We'll check the Authorization header for all incoming requests with tokens
         * If there's a header like "Bearer [Your token].
         */
        final String authorizationHeader = request.getHeader("Authorization"); //Bearer Token

        /**
         * 1. Is the authorization exists?
         * 2. Is the Bearer exists?
         * 3. Is user already logged in?
         */
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication()==null){
            /**
             *    01234567
             *    Bearer [Token]
             *    As you can see above, the token starts at the 7. th character.
             *    This means we'll look after the 7. th character for token.
             */
            String token = authorizationHeader.substring(7);
            boolean isValid = jwtTokenManager.validateToken(token);
            if (isValid){
                Optional<String> encodedProfileId = jwtTokenManager.getProfileId(token);
                if (encodedProfileId.isPresent()){
                    String decodedProfileId = jwtEncodeDecode.getDecodeUUID(encodedProfileId.get());
                    UserDetails user = jwtUserDetail.loadUserProfileId(decodedProfileId);
                    if (user != null){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
