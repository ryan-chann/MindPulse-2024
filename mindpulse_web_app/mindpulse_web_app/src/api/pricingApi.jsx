import axiosInstance from './axiosConfig';

export const fetchAllServiceOffered = async () => {
    try {
        const response = await axiosInstance.get('/api/appointment/getAllServiceOffered');
        return response.data;
    } catch (error) {
        console.error('Error fetching service offered:', error);
        throw error;
    }
}