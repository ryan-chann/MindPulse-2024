import axiosInstance from './axiosConfig';

export const fetchAllTherapistsInfo = async () => {
    try {
        const response = await axiosInstance.get('/api/therapist/getAllInfo');
        return response.data;
    } catch (error) {
        console.error('Error fetching therapists:', error);
        throw error;
    }
};

export const fetchTherapistProfileById = async (id) => {
    try {
        const response = await axiosInstance.get(`/api/therapist/getProfile/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching therapist profile with ID ${id}:`, error);
        throw error;
    }
};