import React, {useEffect, useState} from 'react';
import { Col, Pagination, Row } from 'antd';
import TherapistCard from '../components/Card/TherapistCard';
import {fetchAllTherapistsInfo} from '../api/therapistApi';
import '../assets/styles.css'

const itemsPerPage = 8;

const TherapistListing = () => {
    const therapistListingDivStyle = {
        position: 'relative',
        minHeight: '100vh',
        margin: '60px 0px 0px 150px'
    }

    const paginationStyle = {
        position: 'absolute',
        bottom: '70px',
        left: '80%'
    }

    const [currentPage, setCurrentPage] = useState(1);
    const [therapists, setTherapists] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getTherapists = async () => {
            try {
                const data = await fetchAllTherapistsInfo();
                setTherapists(data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };
        getTherapists();
    }, []);

    const handleChangePage = (page) => {
        setCurrentPage(page);
    };

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentTherapistsInfo = therapists.slice(startIndex, endIndex);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error fetching therapists: {error.message}</div>;
    }

    return (
        <div style={therapistListingDivStyle}>
            <Row gutter={[16, 16]}>
                {currentTherapistsInfo.map((therapistInfo, index) =>
                    <Col key={therapistInfo.id} span={6} style={index >= 4 ? { marginTop: '40px' } : {}}>
                        <TherapistCard therapistInfo={therapistInfo}/>
                    </Col>
                )}
            </Row>
            {therapists.length > itemsPerPage && (
                <Pagination
                    current={currentPage}
                    pageSize={itemsPerPage}
                    total={therapists.length}
                    onChange={handleChangePage}
                    simple={true}
                    style={paginationStyle}
                />
            )}
        </div>
    );
};

export default TherapistListing;
