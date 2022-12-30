import httpCommon from "@/api/httpCommon";

const API_VER = '/api/v1';

class UserProfileHttp {
    signupSubmit(data){
        return httpCommon.post(API_VER+"/signup", JSON.stringify(data));
    }

    otpSubmit(data){
        return httpCommon.post(API_VER+"/otp", JSON.stringify(data));
    }

    loginSubmit(data){
        return httpCommon.post(API_VER+"/login", JSON.stringify(data));
    }

    forgetPassSubmit(data){
        return httpCommon.post(API_VER+"/forget-password", JSON.stringify(data));
    }
}

export default new UserProfileHttp();