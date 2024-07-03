import {Button, Col, Divider, Row, Steps} from "antd";
import {DoubleRightOutlined} from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import TherapistProfileCard from "../components/Card/TherapistProfileCard";
import TherapistProfileDetailsCard from "../components/Card/TherapistProfileDetailsCard";
import YourInformationForm from "../components/Forms/YourInformationForm";
import ChooseAppointmentForm from "../components/Forms/ChooseAppointmentForm";
import {fetchTherapistProfileById} from "../api/therapistApi";
import {fetchAllServiceOffered} from "../api/pricingApi";
import '../assets/styles.css'

const therapistProfileDivStyle = {
    width: '1170px'
}

const topDivStyle = {
    display: 'flex',
    flexDirection: 'row',
}

const bottomDivStyle ={
    display: 'flex',
    flexDirection: 'column',
    margin: '0px 0px 0px 149px'
}

const therapistProfileCardDivStyle = {
    margin: '82px 0px 0px 150px'
}

const therapistProfileDetailCardDivStyle = {
    margin: '119px 0px 0px 50px'
}

const dividerStyle = {
    margin: '50px 0px 15px 149px'
}

const taxRate = 0.08;

const offDates = [
    '2024-07-01',
    '2024-07-05',
    '2024-07-10'
];

const workingDays = [1, 2, 3, 4, 5];

const workingStartTime = '09:00';
const workingEndTime = '18:00';

const unavailableSlots = [
    "2024-07-08T09:00:00",
    "2024-07-08T11:00:00",
    "2024-07-08T01:00:00"
];

const TherapistProfile = () => {
    const {id} = useParams();
    const [therapistProfile, setTherapistProfile] = useState([]);
    const [serviceOffered, setServiceOffered] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [current, setCurrent] = useState(0);

    const next = () => {
        setCurrent(current + 1);
    };
    const prev = () => {
        setCurrent(current - 1);
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [therapistData, serviceData] = await Promise.all([
                    fetchTherapistProfileById(id),
                    fetchAllServiceOffered()
                ]);
                setTherapistProfile(therapistData);
                setServiceOffered(serviceData);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [id]);

    const steps = [
        {
            title: 'Choose Appointment',
            content: therapistProfile.therapistInfo && (<ChooseAppointmentForm serviceOffered={serviceOffered}
                                                                               therapistType={therapistProfile.therapistInfo.type}
                                                                               taxRate={taxRate}
                                                                               offDates={offDates}
                                                                               workingDays={workingDays}
                                                                               workingStartTime={workingStartTime}
                                                                               workingEndTime={workingEndTime}
                                                                               unavailableSlots={unavailableSlots}
            />)
        },
        {
            title: 'Your Information',
            content: <YourInformationForm />
        },
        {
            title: 'Confirmation',
            content: <p>Hi</p>
        },
    ];

    const items = steps.map((item) => ({
        key: item.title,
        title: item.title,
    }));


    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
        <div style={therapistProfileDivStyle}>
            <div style={topDivStyle}>
                <div style={therapistProfileCardDivStyle}>
                    <TherapistProfileCard imageUrl={therapistProfile.therapistInfo.imageUrl}
                                          modeOfConduct={therapistProfile.therapistModeOfConduct}
                                          available={therapistProfile.therapistInfo.available}
                                          education={therapistProfile.therapistEducation}/>
                </div>

                <div style={therapistProfileDetailCardDivStyle}>
                    <TherapistProfileDetailsCard name={therapistProfile.therapistInfo.name}
                                                 therapistType={therapistProfile.therapistInfo.type}
                                                 languages={therapistProfile.therapistLanguage.languages}
                                                 approaches={therapistProfile.therapistApproach.approaches}
                                                 issues={therapistProfile.therapistAssistance.issues}/>
                </div>
            </div>

            <Divider style={dividerStyle}/>

            <div style={bottomDivStyle}>
                <span style={{fontSize: '32px', fontWeight: 'bold'}}>Book a session</span>

                <Steps progressDot items={items} current={current} style={{margin: '50px 0px 50px 70px'}}/>
                <div>{steps[current].content}</div>
                <Row justify="end">
                    {current > 0 && (
                        <Button style={{ margin: "0px 7px 0px 0px"}} onClick={() => prev()}> Previous </Button>
                    )}

                    {current < steps.length - 1 && (
                        <Col span={1}>
                            <Button type="primary" onClick={() => next()} icon={<DoubleRightOutlined />}>Next Step</Button>
                        </Col>
                    )}
                </Row>
            </div>
        </div>
    );
}

export default TherapistProfile;