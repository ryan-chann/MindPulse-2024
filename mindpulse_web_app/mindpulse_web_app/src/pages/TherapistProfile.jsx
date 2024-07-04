import {Button, Col, Divider, message, Row, Steps} from "antd";
import {DoubleRightOutlined} from "@ant-design/icons";
import React, {useEffect, useState} from "react";
import {useParams, useNavigate} from "react-router-dom";
import {ConfirmationCard, TherapistProfileCard, TherapistProfileDetailsCard} from "../components/Card";
import { YourInformationForm, ChooseAppointmentForm } from "../components/Forms";

import '../assets/styles.css'
import {fetchServicePriceByIdandTherapistType, fetchTaxRate, fetchTherapistProfileById} from "../api/api";

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

const TherapistProfile = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const [therapistProfile, setTherapistProfile] = useState([]);
    const [s1, setS1] = useState(null);
    const [s2, setS2] = useState(null);
    const [taxRate, setTaxRate] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [current, setCurrent] = useState(0);
    const [isNextDisabled, setIsNextDisabled] = useState(true);
    const [fileUploaded, setFileUploaded] = useState(false);

    const next = () => {
        if (current === 0) {
            if (!chooseAppointmentDetails.selectedDate || !chooseAppointmentDetails.selectedTime) {
                message.error("Please select a date and time.");
                return;
            }
        } else if (current === 1) {
            if (yourInformationDetails.fullNameError || yourInformationDetails.phoneNumberError || yourInformationDetails.emailAddressError || yourInformationDetails.nricNumberError) {
                message.error("Please fix the errors in the form.");
                return;
            }
        }
        setCurrent(current + 1);
    };

    const cancel = () => {
        setCurrent(0);
    };

    const submit = async () => {
        const finalDetails = {
            fullName: yourInformationDetails.fullName,
            nric: yourInformationDetails.nricNumber,
            phoneNumber: yourInformationDetails.phoneNumber,
            emailAddress: yourInformationDetails.emailAddress,
            mode: chooseAppointmentDetails.mode,
            session: chooseAppointmentDetails.session,
            therapistId: therapistProfile.therapistInfo.id,
            therapistName: therapistProfile.therapistInfo.name,
            therapistType: therapistProfile.therapistInfo.type,
            totalPrice: chooseAppointmentDetails.totalPrice,
            selectedDate: chooseAppointmentDetails.selectedDate,
            selectedTime: chooseAppointmentDetails.selectedTime,
        };

        try {
            const response = await fetch('http://localhost:8081/api/appointment/submit', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(finalDetails),
            });

            if (response.ok) {
                message.success('Appointment booked successfully!');
                navigate('/');
            } else {
                throw new Error('Failed to book appointment');
            }
        } catch (error) {
            message.error(error.message);
        }
    }


    useEffect(() => {
        const fetchData = async () => {
            try {

                const therapistData = await fetchTherapistProfileById(id);
                setTherapistProfile(therapistData);

                const taxRateData = await fetchTaxRate();
                setTaxRate(taxRateData);

                const [s1, s2] = await Promise.all([
                    fetchServicePriceByIdandTherapistType('S1', therapistData.therapistInfo.type),
                    fetchServicePriceByIdandTherapistType('S2', therapistData.therapistInfo.type)
                ]);

                setS1(s1)
                setS2(s2)
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [id]);

    const [chooseAppointmentDetails, setChooseAppointmentDetails] = useState({});
    const [yourInformationDetails, setYourInformationDetails] = useState({});

    useEffect(() => {
        if (current === 0 && (!chooseAppointmentDetails.selectedDate || !chooseAppointmentDetails.selectedTime)) {
            setIsNextDisabled(true);
        } else if (current === 1 && (!yourInformationDetails.fullName || !yourInformationDetails.phoneNumber || !yourInformationDetails.emailAddress || !yourInformationDetails.nricNumber ||
            yourInformationDetails.fullNameError || yourInformationDetails.phoneNumberError || yourInformationDetails.emailAddressError || yourInformationDetails.nricNumberError)) {
            setIsNextDisabled(true);
        } else {
            setIsNextDisabled(false);
        }
    }, [current, chooseAppointmentDetails, yourInformationDetails, fileUploaded]);

    const chooseAppointmentFormDetails = (details) => {
        setChooseAppointmentDetails(details);
    }

    const yourInformationFormDetails = (details) => {
        setYourInformationDetails(details);
    }

    const handleFileUploadStatus = (status) => {
        setFileUploaded(status);
    };

    const steps = [
        {
            title: 'Choose Appointment',
            content: therapistProfile.therapistInfo && (<ChooseAppointmentForm s1={s1}
                                                                               s2={s2}
                                                                               therapistType={therapistProfile.therapistInfo.type}
                                                                               taxRate={taxRate}
                                                                               offDates={therapistProfile.therapistAvailability.offDates}
                                                                               workingDays={therapistProfile.therapistAvailability.workingDays}
                                                                               workingStartTime={therapistProfile.therapistAvailability.workingStartTime}
                                                                               workingEndTime={therapistProfile.therapistAvailability.workingEndTime}
                                                                               unavailableSlots={therapistProfile.therapistAvailability.unavailableSlots}
                                                                               chooseAppointmentFormChange={chooseAppointmentFormDetails}
            />)
        },
        {
            title: 'Your Information',
            content: <YourInformationForm yourInformationFormChange={yourInformationFormDetails}/>
        },
        {
            title: 'Confirmation',
            content: therapistProfile.therapistInfo && (
                <>
                    <div>
                        <ConfirmationCard chooseAppointmentDetails={chooseAppointmentDetails}
                                          yourInformationDetails={yourInformationDetails}
                                          therapistName={therapistProfile.therapistInfo.name}
                                          therapistType={therapistProfile.therapistInfo.type}
                                          handleFileUploadStatus={handleFileUploadStatus}
                        />
                    </div>
                </>
            )
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

            {therapistProfile.therapistInfo.available && (
                <div style={bottomDivStyle}>
                    <span style={{fontSize: '32px', fontWeight: 'bold'}}>Book a session</span>

                    <Steps progressDot items={items} current={current} style={{margin: '50px 0px 50px 70px'}}/>
                    <div>{steps[current].content}</div>
                    <Row justify="end">
                        {current > 0 && (
                            <Button style={{margin: "0px 7px 0px 0px"}} onClick={() => cancel()}> Cancel </Button>
                        )}

                        {current < steps.length - 1 && (
                            <Col span={1}>
                                <Button type="primary" onClick={() => next()} icon={<DoubleRightOutlined/>} disabled={isNextDisabled}>Next Step</Button>
                            </Col>
                        )}

                        {current === steps.length - 1 && (
                            <Col span={1}>
                                <Button type="primary" icon={<DoubleRightOutlined/>} onClick={submit} disabled={!fileUploaded}>Submit</Button>
                            </Col>
                        )}
                    </Row>
                </div>
            )}
        </div>
    );
}

export default TherapistProfile;