import axios from "axios";

const OKTETO_URL = window.location.href;
// const TEST_URL = 'http://localhost:8081';

export default axios.create({
    baseURL: OKTETO_URL,
    headers: {
        "Content-type": "application/json"
    }
});