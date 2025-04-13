package com.broker.stockorders.dto.response;

public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private Long customerId;

    public LoginResponse(String token, String username, String role, Long customerId) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.customerId = customerId;
    }

    public LoginResponse() {
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public String getToken() {
        return this.token;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LoginResponse)) return false;
        final LoginResponse other = (LoginResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$token = this.getToken();
        final Object other$token = other.getToken();
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$customerId = this.getCustomerId();
        final Object other$customerId = other.getCustomerId();
        if (this$customerId == null ? other$customerId != null : !this$customerId.equals(other$customerId))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LoginResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $token = this.getToken();
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $customerId = this.getCustomerId();
        result = result * PRIME + ($customerId == null ? 43 : $customerId.hashCode());
        return result;
    }

    public String toString() {
        return "LoginResponse(token=" + this.getToken() + ", username=" + this.getUsername() + ", role=" + this.getRole() + ", customerId=" + this.getCustomerId() + ")";
    }

    public static class LoginResponseBuilder {
        private String token;
        private String username;
        private String role;
        private Long customerId;

        LoginResponseBuilder() {
        }

        public LoginResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public LoginResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginResponseBuilder role(String role) {
            this.role = role;
            return this;
        }

        public LoginResponseBuilder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(this.token, this.username, this.role, this.customerId);
        }

        public String toString() {
            return "LoginResponse.LoginResponseBuilder(token=" + this.token + ", username=" + this.username + ", role=" + this.role + ", customerId=" + this.customerId + ")";
        }
    }
}
