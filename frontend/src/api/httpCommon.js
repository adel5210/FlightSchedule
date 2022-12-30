import axios from "axios";

const OKTETO_URL = window.location.href;

export default axios.create({
    baseURL: OKTETO_URL,
    headers: {
        "Content-type": "application/json"
    }
});