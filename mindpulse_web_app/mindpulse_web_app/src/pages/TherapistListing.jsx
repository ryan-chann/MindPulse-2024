import React, { useState } from 'react';
import { Col, Pagination, Row } from 'antd';
import TherapistCard from '../components/Card/TherapistCard';

const itemsPerPage = 8;

const therapists = [
    {isAtFullCapacity: true, name: 'John Doe', type: 'Clinical Psychologist' },
    {isAtFullCapacity: false, name: 'Jane Doe', type: 'Trainee' },
    {isAtFullCapacity: true, name: 'Alice Smith', type: 'Counseling Psychologist' },
    {isAtFullCapacity: false, name: 'Bob Johnson', type: 'Behavioral Therapist' },
    {isAtFullCapacity: true, name: 'Carol Williams', type: 'Clinical Psychologist' },
    {isAtFullCapacity: false, name: 'David Brown', type: 'Trainee' },
    {isAtFullCapacity: true, name: 'Eve Davis', type: 'Counseling Psychologist' },
    {isAtFullCapacity: false, name: 'Frank Moore', type: 'Behavioral Therapist' },
    {isAtFullCapacity: true, name: 'Grace Wilson', type: 'Clinical Psychologist' },
    {isAtFullCapacity: false, name: 'Henry Lee', type: 'Trainee' },
];

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

    const handleChangePage = (page) => {
        setCurrentPage(page);
    };

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentTherapists = therapists.slice(startIndex, endIndex);

    return (
        <div style={therapistListingDivStyle}>
            <Row gutter={[16, 16]}>
                {currentTherapists.map((therapist, index) =>
                    <Col key={index} span={6} style={index >= 4 ? { marginTop: '40px' } : {}}>
                        <TherapistCard
                            isAtFullCapacity={therapist.isAtFullCapacity}
                            therapistName={therapist.name}
                            therapistType={therapist.type}
                        />
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
