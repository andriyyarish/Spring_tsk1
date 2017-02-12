package com.epamUniversity.soapWS;

import javax.xml.bind.annotation.*;

/**
 * Created by Andriy_Yarish on 1/31/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"email"})
@XmlRootElement
public class GetUserRequest {

    @XmlElement(required = true)
    protected String email;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
