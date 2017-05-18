package com.youndevice.rest.filters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.youndevice.rest.constants.RestConstants;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirebaseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String token = request.getHeader(RestConstants.AUTH_TOKEN_HEADER);
        String uid = request.getHeader(RestConstants.AUTH_UID);


        Task<FirebaseToken> task = auth.verifyIdToken(token);

        //TODO: Remove looping. This used for waiting for task to finish
        while (!task.isComplete()){}

        if(task.isSuccessful()) {
            FirebaseToken firebaseToken = task.getResult();
            if(firebaseToken.getUid().equals(uid)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }
}
