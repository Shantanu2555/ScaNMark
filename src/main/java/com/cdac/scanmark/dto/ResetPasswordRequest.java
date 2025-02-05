// package com.cdac.scanmark.dto;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// public class ResetPasswordRequest {
//     private String otp;
//     private String newPassword;
//     private String role ;
// }

package com.cdac.scanmark.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    private String email ;
    private String otp ;
    private String newPassword ;
}


