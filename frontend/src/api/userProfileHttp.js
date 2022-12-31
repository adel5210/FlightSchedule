import httpCommon from "@/api/httpCommon";

const AUTH_API = '/api/v1/auth';
const KEY_TOKEN_PROPERTY = 'data';

class UserProfileHttp {
    authHeader() {
        const data = JSON.parse(localStorage.getItem(KEY_TOKEN_PROPERTY));
        if (data && data.accessToken) {
            return {
                Authorization: 'Bearer ' + data.accessToken,
                "Content-type": "application/json"
            }
        } else {
            return {}
        }
    }

    signup(data) {
        return httpCommon.post(AUTH_API + "/signup", JSON.stringify(data));
    }

    validateOtp(data) {
        return httpCommon.post(AUTH_API + "/validate-otp", JSON.stringify(data));
    }

    resendOtp(data) {
        return httpCommon.post(AUTH_API + "/resend-otp", JSON.stringify(data));
    }

    signIn(data) {
        return httpCommon.post(AUTH_API + "/signin", JSON.stringify(data));
    }

    signOut(data) {
        return httpCommon.post(AUTH_API + "/signout", JSON.stringify(data));
    }

    refreshToken(data) {
        return httpCommon.post(AUTH_API + "/refresh-token", JSON.stringify(data));
    }

    resetPassword(data) {
        return httpCommon.post(AUTH_API + "/reset-password", JSON.stringify(data));
    }
}

export default new UserProfileHttp();