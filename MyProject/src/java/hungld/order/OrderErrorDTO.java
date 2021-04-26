/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungld.order;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class OrderErrorDTO implements Serializable {
    private String usernameLengthError;
    private String addressLengthError;
    private String phoneLengthError;
    private String phoneFormatError;

    public OrderErrorDTO() {
    }

    public OrderErrorDTO(String usernameLengthError, String addressLengthError, String phoneLengthError, String phoneFormatError) {
        this.usernameLengthError = usernameLengthError;
        this.addressLengthError = addressLengthError;
        this.phoneLengthError = phoneLengthError;
        this.phoneFormatError = phoneFormatError;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getPhoneFormatError() {
        return phoneFormatError;
    }

    public void setPhoneFormatError(String phoneFormatError) {
        this.phoneFormatError = phoneFormatError;
    }

}
