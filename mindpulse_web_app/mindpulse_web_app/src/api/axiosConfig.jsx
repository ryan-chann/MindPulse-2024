import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://ec2-108-136-165-13.ap-southeast-3.compute.amazonaws.com/api/',
    headers: {
        'Content-Type': 'application/json',
    },
});

export default axiosInstance;