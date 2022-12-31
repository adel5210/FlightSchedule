import axios from "axios";

// const OKTETO_URL = window.location.href;
const TEST_URL = 'http://localhost:8081';

export default axios.create({
    baseURL: TEST_URL,
    headers: {
        "Content-type": "application/json"
    }
});