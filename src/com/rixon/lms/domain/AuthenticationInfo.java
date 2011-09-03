package com.rixon.lms.domain;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/3/11 - 11:56 AM
 */
public class AuthenticationInfo {

    private String username;
    private String password;
    private String role;

    public AuthenticationInfo(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public static class AuthenticationInfoBuilder {
        private String username;
        private String password;
        private String role;

        public AuthenticationInfoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationInfoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationInfoBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public AuthenticationInfo createAuthenticationInfo() {
            return new AuthenticationInfo(username, password, role);
        }
    }
}
