import httpCommon from "@/api/httpCommon";

const AVIA_API = '/api/v1/aviation';
const KEY_TOKEN_PROPERTY = 'data';

class AviationHttp {
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

    fetchFlights(param) {
        return httpCommon.get(AVIA_API + "/flights", {
            headers: this.authHeader(),
            params: {
                additionalPath: param
            }
        });
    }
}

export default new AviationHttp();