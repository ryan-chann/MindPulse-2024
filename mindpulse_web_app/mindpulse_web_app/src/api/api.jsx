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

export const fetchTaxRate = async () => {
    try {
        const response = await axiosInstance.get(`/api/appointment/getTaxRate`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching therapist profile with ID ${id}:`, error);
        throw error;
    }
};

export const fetchServicePriceByIdandTherapistType = async (serviceId, therapistType) => {
    try {
        const response = await axiosInstance.get(`/api/appointment/getServiceOffered/${serviceId}/${therapistType}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching therapist profile with ID ${id}:`, error);
        throw error;
    }
};