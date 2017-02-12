package com.epamUniversity.soapWS;

import com.epamUniversity.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Andriy_Yarish on 1/31/2017.
 */
@Endpoint
public class UserEndPoint {
    private static final String NAMESPACE_URI = "http://epamUniversity.com/soapWS";

    private UserRepository userRepository;

    @Autowired
    public UserEndPoint(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request){
        GetUserResponse response = new GetUserResponse();
        response.setUser(userRepository.findByEmail(request.getEmail()));
        return response;
    }
}
