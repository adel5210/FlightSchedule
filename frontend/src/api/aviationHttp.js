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

    fetchCountries(param) {
        return httpCommon.get(AVIA_API + "/countries", {
            headers: this.authHeader(),
            params: {
                additionalPath: param
            }
        });
    }

    fetchCities(param) {
        return httpCommon.get(AVIA_API + "/cities", {
            headers: this.authHeader(),
            params: {
                additionalPath: param
            }
        });
    }

    fetchRoutes(param) {
        return httpCommon.get(AVIA_API + "/routes", {
            headers: this.authHeader(),
            params: {
                additionalPath: param
            }
        });
    }

    fetchAirports(param) {
        return httpCommon.get(AVIA_API + "/airports", {
            headers: this.authHeader(),
            params: {
                additionalPath: param
            }
        });
    }

}

export default new AviationHttp();